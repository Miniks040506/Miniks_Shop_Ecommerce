package com.miniks.shop.service.implement;

import com.miniks.shop.entity.Cart;
import com.miniks.shop.entity.Coupon;
import com.miniks.shop.entity.User;
import com.miniks.shop.repository.CartRepository;
import com.miniks.shop.repository.CouponRepository;
import com.miniks.shop.repository.UserRepository;
import com.miniks.shop.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    public Cart applyCoupon(String code, double orderValue, User user) throws Exception {

        Coupon coupon = couponRepository.findByCode(code);

        Cart cart = cartRepository.findByUserId(user.getId());

        if (coupon == null) {
            throw new Exception("Coupon not valid");
        }

        if (user.getUsedCoupons().contains(coupon)) {
            throw new Exception("Coupon already used");
        }

        if (orderValue < coupon.getMinimumOrderValue()) {
            throw new Exception("Only valid for minimum order value " + coupon.getMinimumOrderValue());
        }

        if (coupon.isActive() &&
                LocalDate.now().isAfter(coupon.getValidityStartDate()) &&
                LocalDate.now().isBefore(coupon.getValidityEndDate())
        ) {

            user.getUsedCoupons().add(coupon);
            userRepository.save(user);

            long discountedPrice =
                    Math.round(cart.getTotalSellingPrice() * (coupon.getDiscountPercentage() / 100));

            cart.setTotalSellingPrice(cart.getTotalSellingPrice() - discountedPrice);
            cart.setCouponCode(code);

            cartRepository.save(cart);

            return cart;
        }

        throw new Exception("Coupon not valid");
    }

    @Override
    public Cart removeCoupon(String code, User user) throws Exception {

        Coupon coupon = couponRepository.findByCode(code);

        if  (coupon == null) {
            throw new Exception("coupon not found...");
        }

        Cart cart = cartRepository.findByUserId(user.getId());

        long discountedPrice =
                Math.round(cart.getTotalSellingPrice() * (coupon.getDiscountPercentage() / 100));

        cart.setTotalSellingPrice(cart.getTotalSellingPrice() + discountedPrice);
        cart.setCouponCode(null);

        return cartRepository.save(cart);
    }

    @Override
    public Coupon findCouponById(Long id) throws Exception {

        return couponRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Coupon not found..."));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Coupon createCoupon(Coupon coupon) {

        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> findAllCoupons() {

        return couponRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCoupon(Long couponId) throws Exception {

        findCouponById(couponId);

        couponRepository.deleteById(couponId);
    }
}
