package com.miniks.shop.repository;

import com.miniks.shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c " +
            "JOIN FETCH c.user u " +
            "LEFT JOIN FETCH c.cartItems ci " +
            "WHERE u.id = :id")
    Cart findByUserId(@Param("id") Long cartId);
}
