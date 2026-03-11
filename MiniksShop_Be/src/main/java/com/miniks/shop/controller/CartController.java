package com.miniks.shop.controller;

import com.miniks.shop.entity.Cart;
import com.miniks.shop.entity.CartItem;
import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.User;
import com.miniks.shop.exception.ProductException;
import com.miniks.shop.request.AddItemRequest;
import com.miniks.shop.response.ApiResponse;
import com.miniks.shop.service.CartItemService;
import com.miniks.shop.service.CartService;
import com.miniks.shop.service.ProductService;
import com.miniks.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(
            @RequestHeader("Authorization") String jwtToken) throws Exception {

        User user = userService.findUserByJwtToken(jwtToken);

        Cart cart = cartService.findUserCart(user);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItem> addCartItemHandler(
            @RequestBody AddItemRequest request,
            @RequestHeader("Authorization") String jwtToken
    ) throws ProductException, Exception {

        User user = userService.findUserByJwtToken(jwtToken);

        Product product = productService.findProductById(request.getProductId());

        CartItem item = cartService.addCartItem(user, product,
                request.getSize(), request.getQuantity());

        ApiResponse response = new ApiResponse();
        response.setMessage("Item Added To Cart Successfully");

        return new ResponseEntity<>(item, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwtToken);

        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse response = new ApiResponse();
        response.setMessage("Item Removed From Cart Successfully");

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestBody CartItem cartItem,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {

        User user = userService.findUserByJwtToken(jwtToken);

        CartItem updatedCartItem = null;

        if (cartItem.getQuantity() > 0) {
            updatedCartItem = cartItemService.updateCartItem(
                    user.getId(), cartItemId, cartItem);
        }

        return new ResponseEntity<>(updatedCartItem, HttpStatus.ACCEPTED);
    }

}
