package com.miniks.shop.response;

import com.miniks.shop.domain.USER_ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String jwtToken;
    private String message;
    private USER_ROLE role;

}
