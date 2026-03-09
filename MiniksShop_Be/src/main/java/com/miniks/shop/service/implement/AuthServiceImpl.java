package com.miniks.shop.service.implement;

import com.miniks.shop.config.JwtProvider;
import com.miniks.shop.domain.USER_ROLE;
import com.miniks.shop.entity.Cart;
import com.miniks.shop.entity.Seller;
import com.miniks.shop.entity.User;
import com.miniks.shop.entity.VerificationCode;
import com.miniks.shop.repository.CartRepository;
import com.miniks.shop.repository.SellerRepository;
import com.miniks.shop.repository.UserRepository;
import com.miniks.shop.repository.VerificationCodeRepository;
import com.miniks.shop.request.LoginRequest;
import com.miniks.shop.request.SignupRequest;
import com.miniks.shop.response.AuthResponse;
import com.miniks.shop.service.AuthService;
import com.miniks.shop.service.EmailService;
import com.miniks.shop.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final CustomUserServiceImpl customUserService;
    private final SellerRepository sellerRepository;

    @Override
    public void sentLoginOtp(String email, USER_ROLE role) throws Exception {
        String SIGNING_PREFIX = "signing_";

        if (email.startsWith(SIGNING_PREFIX)) {
            email = email.substring(SIGNING_PREFIX.length());

            if (role.equals(USER_ROLE.ROLE_SELLER)) {

                Seller seller = sellerRepository.findByEmail(email);
                if (seller == null) {
                    throw new Exception("Seller not exist with provided email");
                }

            } else {

                User user = userRepository.findByEmail(email);
                if (user == null) {
                    throw new Exception("User not exist with provided email");
                }

            }

        }

        VerificationCode isExisted = verificationCodeRepository.findByEmail(email);

        if (isExisted != null) {
            verificationCodeRepository.delete(isExisted);
        }

        //String otp
        String otp = OtpUtil.generateOtpCode();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);
        verificationCodeRepository.save(verificationCode);

        String subject = "Miniks Shop login/signup otp";
        String text = "Your login/signup otp is - " + otp;

        emailService.sendVerificationOtpEmail(email, otp, subject, text);

    }

    @Override
    public String createUser(SignupRequest request) throws Exception {

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(request.getEmail());

        if (verificationCode == null || !verificationCode.getOtp().equals(request.getOtp())) {
            throw new Exception("wrong otp...");
        }

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            User createdUser = new User();
            createdUser.setEmail(request.getEmail());
            createdUser.setFullName(request.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setMobile("08298 3437");
            createdUser.setPassword(passwordEncoder.encode(request.getOtp()));
            user = userRepository.save(createdUser);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()))
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }

    @Override
    public AuthResponse SigningAccount(LoginRequest request) {

        String username = request.getEmail();
        String otp = request.getOtp();

        Authentication authentication = authenticate(username, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwtToken(token);
        authResponse.setMessage("Login successful");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(role));

        return authResponse;
    }

    private Authentication authenticate(String username, String otp) {

        UserDetails userDetails = customUserService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(username);

        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new BadCredentialsException("Wrong otp");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }


}
