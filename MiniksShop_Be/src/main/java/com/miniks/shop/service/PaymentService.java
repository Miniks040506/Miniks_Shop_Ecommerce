package com.miniks.shop.service;

import com.miniks.shop.entity.Order;
import com.miniks.shop.entity.PaymentOrder;
import com.miniks.shop.entity.User;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;

import java.util.Set;

public interface PaymentService {

    PaymentOrder createOrder(User user, Set<Order> orders);

    PaymentOrder getPaymentOrderById(Long orderId) throws Exception;

    PaymentOrder getPaymentOrderByPaymentLinkId(String paymentLinkId) throws Exception;

    Boolean ProceedPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException;

    PaymentLink createRazorpayPaymentLink(User user, Long amount, Long orderId) throws RazorpayException;

}
