package com.miniks.shop.controller;

import com.miniks.shop.domain.PaymentMethod;
import com.miniks.shop.entity.Address;
import com.miniks.shop.response.PaymentLinkResponse;
import com.miniks.shop.service.OrderService;
import com.miniks.shop.service.SellerService;
import com.miniks.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final SellerService sellerService;

    @PostMapping
    public ResponseEntity<PaymentLinkResponse> createOrderHandler(
            @RequestBody Address shippingAddress,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {

        return null;
    }

}
