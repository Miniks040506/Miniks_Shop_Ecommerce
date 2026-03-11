package com.miniks.shop.service;

import com.miniks.shop.entity.CartItem;

public interface CartItemService {

    CartItem findCartItemById(Long id) throws Exception;

    CartItem updateCartItem(Long userId, Long cartItemId, CartItem cartItem) throws Exception;

    void removeCartItem(Long userId, Long cartItemId) throws Exception;

}
