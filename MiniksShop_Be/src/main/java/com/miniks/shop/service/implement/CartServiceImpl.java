package com.miniks.shop.service.implement;

import com.miniks.shop.entity.Cart;
import com.miniks.shop.entity.CartItem;
import com.miniks.shop.entity.Product;
import com.miniks.shop.entity.User;
import com.miniks.shop.repository.CartItemRepository;
import com.miniks.shop.repository.CartRepository;
import com.miniks.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public CartItem addCartItem(User user, Product product, String size, int quantity) {

        Cart cart = findUserCart(user);

        CartItem isPresent = cartItemRepository.findByCartAndProductAndSize(cart, product, size);

        CartItem cartItem = null;

        if (isPresent == null) {
            cartItem = new CartItem();

            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());
            cartItem.setSize(size);

            cart.getCartItems().add(cartItem);
            cartItem.setCart(cart);

        } else {
            cartItem = isPresent;

            cartItem.setQuantity(quantity + cartItem.getQuantity());
        }

        long totalSellingPrice = cartItem.getQuantity() * product.getSellingPrice();
        cartItem.setSellingPrice(totalSellingPrice);

        long totalMrpPrice = cartItem.getQuantity() * product.getMrpPrice();
        cartItem.setMrpPrice(totalMrpPrice);

        this.updateCartInfo(cart);

        return cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public Cart updateCartInfo(Cart cart) {

//        Cart cart = cartRepository.findByUserId(user.getId());

        long totalPrice = 0;
        long totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getMrpPrice();
            totalDiscountedPrice += cartItem.getSellingPrice();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setTotalItem(totalItem);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountedPrice));

        return cart;
    }

    @Override
    @Transactional(readOnly = true)
    public Cart findUserCart(User user) {

        Cart cart = cartRepository.findByUserId(user.getId());

        return cart;
    }

    private int calculateDiscountPercentage(double mrpPrice, double sellingPrice) {

        if (mrpPrice <= 0) {
            return 0;
        }

        double discount = mrpPrice - sellingPrice;
        double discountPercentage = (discount / mrpPrice) * 100;

        return (int) Math.round(discountPercentage);
    }

}
