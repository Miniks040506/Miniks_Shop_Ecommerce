package com.miniks.shop.service;


import com.miniks.shop.request.LoginRequest;
import com.miniks.shop.response.AuthResponse;
import com.miniks.shop.request.SignupRequest;

public interface AuthService {

    void sentLoginOtp(String email) throws Exception;

    String createUser(SignupRequest request) throws Exception;

    AuthResponse SigningAccount(LoginRequest request);

}
