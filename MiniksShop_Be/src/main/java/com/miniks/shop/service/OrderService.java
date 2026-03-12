package com.miniks.shop.service;

import com.miniks.shop.domain.OrderStatus;
import com.miniks.shop.entity.*;

import java.util.List;
import java.util.Set;

public interface OrderService {

    Set<Order> createOrder(User user, Address shippingAddress, Cart cart);

    Order findOrderById(long id) throws Exception;

    List<Order> userOrderHistory(Long userId);

    List<Order> sellersOrder(Long sellerId);

    Order updateOrderStatus(Long orderId, OrderStatus status) throws Exception;

    Order cancelOrder(Long orderId, User user) throws Exception;

    OrderItem findById(Long id) throws Exception;

}
