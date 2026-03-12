package com.miniks.shop.service.implement;

import com.miniks.shop.domain.OrderStatus;
import com.miniks.shop.entity.Address;
import com.miniks.shop.entity.Cart;
import com.miniks.shop.entity.Order;
import com.miniks.shop.entity.User;
import com.miniks.shop.repository.AddressRepository;
import com.miniks.shop.repository.OrderRepository;
import com.miniks.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {
        return Set.of();
    }

    @Override
    @Transactional(readOnly = true)
    public Order findOrderById(long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> userOrderHistory(Long userId) {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> sellersOrder(Long sellerId) {
        return List.of();
    }

    @Override
    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        return null;
    }

    @Override
    @Transactional
    public Order cancelOrder(Long orderId, User user) {
        return null;
    }
}
