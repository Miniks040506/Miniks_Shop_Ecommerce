package com.miniks.shop.repository;

import com.miniks.shop.entity.Cart;
import com.miniks.shop.entity.CartItem;
import com.miniks.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);

}
