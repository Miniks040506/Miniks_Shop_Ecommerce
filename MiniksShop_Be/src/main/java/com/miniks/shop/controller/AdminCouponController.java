package com.miniks.shop.controller;

import com.miniks.shop.entity.Cart;
import com.miniks.shop.entity.Coupon;
import com.miniks.shop.entity.User;
import com.miniks.shop.response.ApiResponse;
import com.miniks.shop.service.CartService;
import com.miniks.shop.service.CouponService;
import com.miniks.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons")
public class AdminCouponController {

    private final CouponService couponService;
    private final UserService userService;
    private final CartService cartService;

    @PostMapping("/apply")
    public ResponseEntity<Cart> applyCouponHandler(
            @RequestParam String apply,
            @RequestParam String code,
            @RequestParam double orderValue,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwtToken);

        Cart cart;

        if (apply.equals("true")) {
            cart = couponService.applyCoupon(code, orderValue, user);
        } else {
            cart = couponService.removeCoupon(code, user);
        }

        return ResponseEntity.ok(cart);
    }

    // Admin operations
    @PostMapping("/admin/create")
    public ResponseEntity<Coupon> createCouponHandler(@RequestBody Coupon coupon) {

        Coupon createdCoupon = couponService.createCoupon(coupon);

        return ResponseEntity.ok(createdCoupon);
    }

    @DeleteMapping("/admin/delete/{couponId}")
    public ResponseEntity<ApiResponse> deleteCouponHandler(
            @PathVariable Long couponId) throws Exception {

        couponService.deleteCoupon(couponId);

        ApiResponse response = new ApiResponse();
        response.setMessage("Coupon deleted successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Coupon>> getAllCouponsHandler() {

        List<Coupon> coupons = couponService.findAllCoupons();

        return ResponseEntity.ok(coupons);
    }

}
