package com.miniks.shop.service.implement;

import com.miniks.shop.entity.CartItem;
import com.miniks.shop.entity.User;
import com.miniks.shop.repository.CartItemRepository;
import com.miniks.shop.service.CartItemService;
import com.miniks.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartService cartService;

    @Override
    @Transactional(readOnly = true)
    public CartItem findCartItemById(Long id) throws Exception {

        return cartItemRepository.findByIdWithProductAndCart(id)
                .orElseThrow(() ->
                        new Exception("Cart item not found with id - " + id));
    }

    @Override
    @Transactional
    public CartItem updateCartItem(Long userId, Long cartItemId, CartItem cartItem) throws Exception {

        CartItem item = findCartItemById(cartItemId);

        User cartItemUser = item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {

            item.setQuantity(cartItem.getQuantity());
            item.setMrpPrice(item.getQuantity() * item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity() * item.getProduct().getSellingPrice());

            cartService.updateCartInfo(item.getCart());

            return cartItemRepository.save(item);
        }

        throw new Exception("You can not update this cart item");
    }

    @Override
    @Transactional
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {

        CartItem item = findCartItemById(cartItemId);

        User cartItemUser = item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {

            cartItemRepository.delete(item);

            cartService.updateCartInfo(item.getCart());

        } else {
            throw new Exception("You can not remove this cart item");
        }

    }
}
