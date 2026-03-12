package com.miniks.shop.service.implement;

import com.miniks.shop.domain.USER_ROLE;
import com.miniks.shop.entity.Seller;
import com.miniks.shop.entity.User;
import com.miniks.shop.repository.SellerRepository;
import com.miniks.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private static final String SELLER_PREFIX = "seller_";

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (username.startsWith(SELLER_PREFIX)) {
            String actualUsername = username.substring(SELLER_PREFIX.length());
            Seller seller = sellerRepository.findByEmail(actualUsername);

            if (seller != null) {
                return buildUserDetail(seller.getEmail(), seller.getPassword(), seller.getRole());
            }

        } else {
            User user = userRepository.findByEmail(username);

            if (user != null) {
                return buildUserDetail(user.getEmail(), user.getPassword(), user.getRole());
            }

        }

        throw new UsernameNotFoundException("User or seller not found with email - " + username);
    }

    private UserDetails buildUserDetail(String email, String password, USER_ROLE role) {
        if (role == null) {
            role = USER_ROLE.ROLE_CUSTOMER;
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails
                .User(email, password, grantedAuthorities);
    }

}
