package com.miniks.shop.controller;

import com.miniks.shop.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeControllerHander(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Welcome to Miniks Shop Ecommerce System");
        return apiResponse;
    }

}
