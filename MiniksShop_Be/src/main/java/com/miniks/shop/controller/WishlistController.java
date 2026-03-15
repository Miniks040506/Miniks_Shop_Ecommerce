package com.miniks.shop.controller;

import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.User;
import com.miniks.shop.entity.Wishlist;
import com.miniks.shop.exception.ProductException;
import com.miniks.shop.service.ProductService;
import com.miniks.shop.service.UserService;
import com.miniks.shop.service.WishlishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlishService wishlishService;
    private final ProductService productService;
    private final UserService userService;


//    @PostMapping("/create")
//    public ResponseEntity<Wishlist> createWishlist(@RequestBody User user) {
//
//        Wishlist wishlist = wishlishService.createWishList(user);
//
//        return ResponseEntity.ok(wishlist);
//    }

    @GetMapping
    ResponseEntity<Wishlist> getWishlistHandler(
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwtToken);

        Wishlist wishlist = wishlishService.getWishListByUserId(user);

        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<Wishlist> addProductToWishListHandler(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception, ProductException {

        Product product = productService.findProductById(productId);

        User user = userService.findUserByJwtToken(jwtToken);

        Wishlist updatedWishlist = wishlishService.addProductToWishList(user, product);

        return ResponseEntity.ok(updatedWishlist);
    }

}
