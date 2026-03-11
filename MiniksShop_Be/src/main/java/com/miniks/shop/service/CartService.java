package com.miniks.shop.service;

import com.miniks.shop.entity.Cart;
import com.miniks.shop.entity.CartItem;
import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.User;

public interface CartService {

    CartItem addCartItem(
            User user,
            Product product,
            String size,
            int quantity
    );

    Cart findUserCart(User user);

}
