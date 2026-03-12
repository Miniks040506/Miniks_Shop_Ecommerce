package com.miniks.shop.embedded;


import com.miniks.shop.domain.PaymentStatus;
import jakarta.persistence.Embeddable;
import lombok.Data;


@Data
@Embeddable
public class PaymentDetails {

    private String paymentId; // Store the transaction ID from VNPay/MoMo.
    private String paymentUrl; // Store the payment link for the user to click.(razorpayPaymentLinkId)
    private String transactionReference; // Store the order ID that you send to the payment gateway.(razorpayPaymentLinkReferenceId)
    private String transactionStatus; // Store the response status (Success / Pending / Failed).(razorpayPaymentLinkStatus)
    private String bankTransactionNo; // Store the bank’s transaction number.(razorpayPaymentId)
    private PaymentStatus status;

}