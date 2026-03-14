package com.miniks.shop.service.implement;

import com.miniks.shop.domain.PaymentOrderStatus;
import com.miniks.shop.domain.PaymentStatus;
import com.miniks.shop.entity.Order;
import com.miniks.shop.entity.PaymentOrder;
import com.miniks.shop.entity.User;
import com.miniks.shop.repository.OrderRepository;
import com.miniks.shop.repository.PaymentOrderRepository;
import com.miniks.shop.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentOrderRepository paymentOrderRepository;
    private final OrderRepository orderRepository;

    private String apiKey = "apiKey";
    private String apiSecret = "apiSecret";

    @Override
    public PaymentOrder createOrder(User user, Set<Order> orders) {

        Long amount = orders.stream().mapToLong(Order::getTotalSellingPrice).sum();

        PaymentOrder paymentOrder = new PaymentOrder();

        paymentOrder.setAmount(amount);
        paymentOrder.setUser(user);
        paymentOrder.setOrders(orders);

        return paymentOrderRepository.save(paymentOrder);
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long orderId) throws Exception {

        return paymentOrderRepository.findById(orderId)
                .orElseThrow(() ->
                        new Exception("Payment order not found"));
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentLinkId(String paymentLinkId) throws Exception {

        PaymentOrder paymentOrder = paymentOrderRepository.findByPaymentLinkId(paymentLinkId);

        if (paymentOrder == null) {
            throw new Exception("Payment order not found with provided payment link id");
        }

        return paymentOrder;
    }

    @Override
    public Boolean ProceedPaymentOrder(PaymentOrder paymentOrder,
                                       String paymentId, String paymentLinkId) throws RazorpayException {

        if (paymentOrder.getOrders().equals(PaymentOrderStatus.PENDING)) {

            RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);

            Payment payment = razorpay.payments.fetch(paymentId);

            String status = payment.get("status");

            if (status.equals("captured")) {

                Set<Order> orders = paymentOrder.getOrders();

                for (Order order : orders) {
                    order.setPaymentStatus(PaymentStatus.COMPLETED);
                    orderRepository.save(order);
                }

                paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                paymentOrderRepository.save(paymentOrder);
                return true;
            }

            paymentOrder.setStatus(PaymentOrderStatus.FAILED);
            paymentOrderRepository.save(paymentOrder);
            return false;
        }

        return false;
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(
            User user, Long amount, Long orderId) throws RazorpayException {

        amount = amount * 100;

        try {
            RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);

            JSONObject paymentLinkRequest = getJsonObject(user, amount, orderId);

            PaymentLink paymentLink = razorpay.paymentLink.create(paymentLinkRequest);
            
            String paymentLinkUrl = paymentLink.get("short_url");
            String paymentLinkId = paymentLink.get("id");

            return paymentLink;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RazorpayException(e.getMessage());
        }

    }

    private static @NonNull JSONObject getJsonObject(User user, Long amount, Long orderId) {

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", amount);
        paymentLinkRequest.put("currency", "USD");

        JSONObject customer = new JSONObject();
        customer.put("name", user.getFullName());
        customer.put("email", user.getEmail());

        JSONObject notify = new JSONObject();
        notify.put("email", true);

        paymentLinkRequest.put("customer", customer);
        paymentLinkRequest.put("notify", notify);

        paymentLinkRequest.put("callback_url",
                "http://localhost:3000/payment-success/" + orderId);
        paymentLinkRequest.put("callback_method", "get");

        return paymentLinkRequest;
    }
}
