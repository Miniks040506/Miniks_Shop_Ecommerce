package com.miniks.shop.service;

import com.miniks.shop.entity.User;

public interface UserService {

    User findUserByJwtToken(String jwtToken) throws Exception;

    User findUserByEmail(String email) throws Exception;

}
