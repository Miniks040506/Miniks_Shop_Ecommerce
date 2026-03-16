package com.miniks.shop.service;

import com.miniks.shop.entity.Cart;
import com.miniks.shop.entity.Coupon;
import com.miniks.shop.entity.User;

import java.util.List;

public interface CouponService {

    Cart applyCoupon(String code, double orderValue, User user) throws Exception;

    Cart removeCoupon(String code, User user) throws Exception;

    Coupon findCouponById(Long id) throws Exception;

    Coupon createCoupon(Coupon coupon);

    List<Coupon> findAllCoupons();

    void deleteCoupon(Long couponId) throws Exception;

}
