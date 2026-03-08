package com.miniks.shop.response;

import com.miniks.shop.domain.UserRole;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwtToken;
    private String message;
    private UserRole role;

}
