package com.miniks.shop.embedded;


import com.miniks.shop.domain.PaymentStatus;
import lombok.Data;


@Data
public class PaymentDetails {

    private String paymentId;
    private String paymentUrl; //razorpayPaymentLinkId
    private String transactionReference; //razorpayPaymentLinkReferenceId
    private String transactionStatus; //razorpayPaymentLinkStatus
    private String bankTransactionNo; //razorpayPaymentId
    private PaymentStatus status;

}