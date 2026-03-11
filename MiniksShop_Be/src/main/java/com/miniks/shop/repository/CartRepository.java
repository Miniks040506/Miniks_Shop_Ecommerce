package com.miniks.shop.repository;

import com.miniks.shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByCartId(Long cartId);
}
