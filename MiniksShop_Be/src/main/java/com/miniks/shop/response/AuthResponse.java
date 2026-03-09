package com.miniks.shop.response;

import com.miniks.shop.domain.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwtToken;
    private String message;
    private USER_ROLE role;

}
