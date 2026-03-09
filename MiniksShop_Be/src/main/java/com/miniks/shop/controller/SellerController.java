package com.miniks.shop.controller;

import com.miniks.shop.entity.Seller;
import com.miniks.shop.entity.VerificationCode;
import com.miniks.shop.repository.VerificationCodeRepository;
import com.miniks.shop.request.LoginOtpRequest;
import com.miniks.shop.request.LoginRequest;
import com.miniks.shop.response.ApiResponse;
import com.miniks.shop.response.AuthResponse;
import com.miniks.shop.service.AuthService;
import com.miniks.shop.service.EmailService;
import com.miniks.shop.service.SellerService;
import com.miniks.shop.utils.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("sellers")
public class SellerController {

    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;
    private final EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody LoginRequest request) throws Exception {

//        String otp = request.getOtp();
        String email = request.getEmail();

//        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);
//
//        if (verificationCode == null
//                || !verificationCode.getOtp().equals(request.getOtp())) {
//            throw new Exception("Wrong otp...");
//        }

        request.setEmail("seller_" + email);

        AuthResponse authResponse = authService.SigningAccount(request);

        return ResponseEntity.ok(authResponse);
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmailHandler
            (@PathVariable String otp) throws Exception {

        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);

        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new Exception("Wrong otp...");
        }

        Seller seller = sellerService.verifyEmail(verificationCode.getEmail(), otp);

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller> createSellerHandler(
            @RequestBody Seller seller) throws Exception, MessagingException {

        Seller savedSeller = sellerService.createSeller(seller);

        String otp = OtpUtil.generateOtpCode();

//        VerificationCode verificationCode = verificationService;

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(seller.getEmail());
        verificationCodeRepository.save(verificationCode);

        String subject = "Miniks Shop Email verification code";
        String text = "Welcome to Miniks Shop, verify your account using this link ";
        String frontend_url = "http://localhost:3000/verify-seller/";

        emailService.sendVerificationOtpEmail(seller.getEmail(),
                verificationCode.getOtp(), subject, text + frontend_url);

        return new  ResponseEntity<>(savedSeller, HttpStatus.CREATED);
    }

}
