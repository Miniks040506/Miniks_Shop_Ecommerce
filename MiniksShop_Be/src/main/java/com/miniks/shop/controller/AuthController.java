package com.miniks.shop.controller;

import com.miniks.shop.domain.USER_ROLE;
import com.miniks.shop.entity.VerificationCode;
import com.miniks.shop.request.LoginOtpRequest;
import com.miniks.shop.request.LoginRequest;
import com.miniks.shop.response.ApiResponse;
import com.miniks.shop.response.AuthResponse;
import com.miniks.shop.request.SignupRequest;
import com.miniks.shop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

//    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest request) throws Exception {

        String jwtToken = authService.createUser(request);

        AuthResponse response = new AuthResponse();
        response.setJwtToken(jwtToken);
        response.setMessage("register successfully");
        response.setRole(USER_ROLE.ROLE_CUSTOMER);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(
            @RequestBody LoginOtpRequest request) throws Exception {

        authService.sentLoginOtp(request.getEmail(), request.getRole());

        ApiResponse response = new ApiResponse();
        response.setMessage("otp sent successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody LoginRequest request) throws Exception {

        AuthResponse authResponse = authService.SigningAccount(request);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}
