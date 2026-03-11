package com.miniks.shop.service.implement;

import com.miniks.shop.entity.CartItem;
import com.miniks.shop.entity.User;
import com.miniks.shop.repository.CartItemRepository;
import com.miniks.shop.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem findCartItemById(Long id) throws Exception {

        return cartItemRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Cart item not found with id - " + id));
    }

    @Override
    public CartItem updateCartItem(Long userId, Long cartItemId, CartItem cartItem) throws Exception {

        CartItem item = findCartItemById(cartItemId);

        User cartItemUser = item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {

            item.setQuantity(item.getQuantity() + cartItem.getQuantity());
            item.setMrpPrice(item.getQuantity() * item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity() * item.getProduct().getSellingPrice());

            return cartItemRepository.save(item);
        }

        throw new Exception("You can not update this cart item");
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {

        CartItem item = findCartItemById(cartItemId);

        User cartItemUser = item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {
            cartItemRepository.delete(item);
        } else {
            throw new Exception("You can not remove this cart item");
        }

    }
}
