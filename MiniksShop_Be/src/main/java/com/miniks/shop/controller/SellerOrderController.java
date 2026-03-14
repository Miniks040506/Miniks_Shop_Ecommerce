package com.miniks.shop.controller;

import com.miniks.shop.domain.OrderStatus;
import com.miniks.shop.entity.Order;
import com.miniks.shop.entity.Seller;
import com.miniks.shop.exception.SellerException;
import com.miniks.shop.service.OrderService;
import com.miniks.shop.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller/orders")
public class SellerOrderController {

    private final OrderService orderService;
    private final SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Order>> getSellerOrdersHandler(
            @RequestHeader("Authorization") String jwtToken
    ) throws SellerException {

        Seller seller = sellerService.getSellerProfile(jwtToken);

        List<Order> orders = orderService.getSellerOrders(seller.getId());

        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{orderId}/status/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatusHandler(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long orderId,
            @PathVariable OrderStatus orderStatus
    ) throws Exception {

        Order order = orderService.updateOrderStatus(orderId, orderStatus);

        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }

}
