package com.miniks.shop.service.implement;

import com.miniks.shop.config.JwtProvider;
import com.miniks.shop.entity.User;
import com.miniks.shop.repository.UserRepository;
import com.miniks.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional(readOnly = true)
    public User findUserByJwtToken(String jwtToken) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwtToken);

        return this.findUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new Exception("User not found with email - " + email);
        }

        return user;
    }
}
