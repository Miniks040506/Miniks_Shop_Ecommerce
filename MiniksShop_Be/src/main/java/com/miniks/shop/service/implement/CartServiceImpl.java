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

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem addCartItem(User user, Product product, String size, int quantity) {

        Cart cart = findUserCart(user);

        CartItem isPresent = cartItemRepository.findByCartAndProductAndSize(cart, product, size);

        if (isPresent == null) {
            CartItem cartItem = new CartItem();

            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());
            cartItem.setSize(size);

            double totalPrice = quantity * product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);

            cart.getCartItems().add(cartItem);
            cartItem.setCart(cart);

            return cartItemRepository.save(cartItem);
        }

        return isPresent;
    }

    @Override
    public Cart findUserCart(User user) {

        Cart cart = cartRepository.findByCartId(user.getId());

        int totalPrice = 0;
        int totalDiscountedPrice = 0;
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

    private int calculateDiscountPercentage(double mrpPrice, double sellingPrice) {

        if (mrpPrice <= 0) {
            throw new IllegalArgumentException("Actual price must be greater than 0.");
        }

        double discount = mrpPrice - sellingPrice;
        double discountPercentage = (discount / mrpPrice) * 100;

        return (int) discountPercentage;
    }

}
