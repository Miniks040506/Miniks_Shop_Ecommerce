package com.miniks.shop.service.implement;

import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.User;
import com.miniks.shop.entity.Wishlist;
import com.miniks.shop.repository.WishlistRepository;
import com.miniks.shop.service.WishlishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlishService {

    private final WishlistRepository wishlistRepository;

    @Override
    public Wishlist createWishList(User user) {

        Wishlist wishlist = new Wishlist();

        wishlist.setUser(user);

        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishListByUserId(User user) {

        Wishlist wishlist = wishlistRepository.findByUserId(user.getId());

        if (wishlist == null) {
            wishlist = createWishList(user);
        }

        return wishlist;
    }

    @Override
    public Wishlist addProductToWishList(User user, Product product) {

        Wishlist wishlist = getWishListByUserId(user);

        if (wishlist.getProducts().contains(product)) {
            wishlist.getProducts().remove(product);
        } else {
            wishlist.getProducts().add(product);
        }

        return wishlistRepository.save(wishlist);
    }
}
