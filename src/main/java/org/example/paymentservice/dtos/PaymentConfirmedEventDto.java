package org.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * Event published to Kafka 'payment-confirmed' topic.
 * Consumed by OrderManagementService to update order status.
 * Consumed by EmailService to send payment receipt (FR-5.3).
 */
@Getter
@Setter
public class PaymentConfirmedEventDto {
    private String orderId;
    private Long amount;
    private String phoneNumber;
    private String paymentGateway; // "razorpay" or "stripe"
    private String paymentLinkUrl;
    private String status; // "INITIATED"
    private String userEmail;
}
