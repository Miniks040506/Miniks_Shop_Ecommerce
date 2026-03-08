package com.miniks.shop.controller;

import com.miniks.shop.domain.UserRole;
import com.miniks.shop.entity.VerificationCode;
import com.miniks.shop.request.LoginRequest;
import com.miniks.shop.response.ApiResponse;
import com.miniks.shop.response.AuthResponse;
import com.miniks.shop.request.SignupRequest;
import com.miniks.shop.service.AuthService;
import lombok.RequiredArgsConstructor;
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
        response.setRole(UserRole.ROLE_CUSTOMER);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody VerificationCode request) throws Exception {

        authService.sentLoginOtp(request.getEmail());

        ApiResponse response = new ApiResponse();
        response.setMessage("otp sent successfully");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody LoginRequest request) throws Exception {

        AuthResponse authResponse = authService.SigningAccount(request);

        return ResponseEntity.ok(authResponse);
    }

}
