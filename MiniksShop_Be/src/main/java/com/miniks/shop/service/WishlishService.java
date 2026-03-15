package com.miniks.shop.service;

import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.User;
import com.miniks.shop.entity.Wishlist;

public interface WishlishService {

    Wishlist createWishList(User user);

    Wishlist getWishListByUserId(User user);

    Wishlist addProductToWishList(User user, Product product);

}
