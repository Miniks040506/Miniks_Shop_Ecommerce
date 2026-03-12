package com.miniks.shop.repository;

import com.miniks.shop.entity.Cart;
import com.miniks.shop.entity.CartItem;
import com.miniks.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);

    @Query("SELECT ci FROM CartItem ci " +
            "JOIN FETCH ci.product p " +
            "JOIN FETCH ci.cart c " +
            "WHERE ci.id = :id")
    Optional<CartItem> findByIdWithProductAndCart(@Param("id") Long id);

}
