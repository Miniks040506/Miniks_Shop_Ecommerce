package com.miniks.shop.service.implement;

import com.miniks.shop.domain.OrderStatus;
import com.miniks.shop.domain.PaymentStatus;
import com.miniks.shop.entity.*;
import com.miniks.shop.repository.AddressRepository;
import com.miniks.shop.repository.OrderItemRepository;
import com.miniks.shop.repository.OrderRepository;
import com.miniks.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {

        if (!user.getAddresses().contains(shippingAddress)) {
            user.getAddresses().add(shippingAddress);
        }

        Address address = addressRepository.save(shippingAddress);

        // brand 1 --> 4 shirts
        // brand 2 --> 1 watch
        // brand 3 --> 3 pants
        // ...

        // brand 1 sell by seller 1 (sellerId --> Long) | Sell List of cart item (ex: 4 shirts)
        Map<Long, List<CartItem>> itemsBySeller = cart.getCartItems().stream()
                .collect(Collectors.groupingBy(
                        item -> item.getProduct().getSeller().getId()
                ));

        Set<Order> orders = new HashSet<>();

        for (Map.Entry<Long, List<CartItem>> entry : itemsBySeller.entrySet()) {
            Long sellerId = entry.getKey();
            List<CartItem> items = entry.getValue();

            long totalMrpPrice = items.stream().mapToLong(
                    CartItem::getMrpPrice
            ).sum();

            long totalOrderPrice = items.stream().mapToLong(
                    CartItem::getSellingPrice
            ).sum();

            int totalItems = items.stream().mapToInt(
                    CartItem::getQuantity
            ).sum();

            Order createdOrder = new Order();

            createdOrder.setUser(user);
            createdOrder.setSellerId(sellerId);
            createdOrder.setTotalMrpPrice(totalMrpPrice);
            createdOrder.setTotalSellingPrice(totalOrderPrice);
            createdOrder.setTotalItem(totalItems);
            createdOrder.setShippingAddress(shippingAddress);
            createdOrder.setOrderStatus(OrderStatus.PENDING);
            createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);

            Order savedOrder = orderRepository.save(createdOrder);

            orders.add(savedOrder);

            List<OrderItem> orderItems = new ArrayList<>();

            for (CartItem item : items) {
                OrderItem orderItem = new OrderItem();

                orderItem.setMrpPrice(item.getMrpPrice());
                orderItem.setSellingPrice(item.getSellingPrice());
                orderItem.setProduct(item.getProduct());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setSize(item.getSize());
                orderItem.setUserId(item.getUserId());

                orderItem.setOrder(savedOrder);
                savedOrder.getOrderItems().add(orderItem);

                OrderItem savedOrderItem = orderItemRepository.save(orderItem);

                orderItems.add(savedOrderItem);
            }
        }

        return orders;
    }

    @Override
    @Transactional(readOnly = true)
    public Order findOrderById(long id) throws Exception {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Order not found..."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findUserOrderHistory(Long userId) {

        return orderRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getSellerOrders(Long sellerId) {

        return orderRepository.findBySellerId(sellerId);
    }

    @Override
    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus status) throws Exception {

        Order order = findOrderById(orderId);

        order.setOrderStatus(status);

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order cancelOrder(Long orderId, User user) throws Exception {

        Order order = findOrderById(orderId);

        order.setOrderStatus(OrderStatus.CANCELLED);

        if (!user.getId().equals(order.getUser().getId())) {
            throw new Exception("You don't have permission to cancel this order");
        }

        return orderRepository.save(order);
    }

}
