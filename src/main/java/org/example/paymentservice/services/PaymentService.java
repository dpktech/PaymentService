package org.example.paymentservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.paymentservice.clients.KafkaProducerClient;
import org.example.paymentservice.dtos.PaymentConfirmedEventDto;
import org.example.paymentservice.paymentgateway.PaymentGateway;
import org.example.paymentservice.paymentgateway.PaymentGatewayChooserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Payment service - generates payment link and publishes Kafka event.
 *
 * FR-5.1: Generate payment link via Razorpay or Stripe.
 * FR-5.3: Publish 'payment-confirmed' Kafka event after link generation,
 *         enabling OrderManagementService to update order status and
 *         EmailService to send payment receipt email.
 */
@Service
public class PaymentService {

    @Autowired
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;

    @Autowired
    private KafkaProducerClient kafkaProducerClient;

    @Autowired
    private ObjectMapper objectMapper;

    public String getPaymentLink(Long amount, String orderId, String phoneNumber, String name) {
        PaymentGateway paymentGateway = paymentGatewayChooserStrategy.getPaymentGateway();
        String paymentLink = paymentGateway.getPaymentLink(amount, orderId, phoneNumber, name);

        // FR-5.3: Publish payment-confirmed event to Kafka
        // OrderManagementService consumes this to confirm the order
        // EmailService consumes this to send payment receipt
        publishPaymentConfirmedEvent(orderId, amount, phoneNumber, paymentLink,
                paymentGateway.getClass().getSimpleName());

        return paymentLink;
    }

    /**
     * Publishes payment-confirmed event to Kafka topic.
     * Consumed by: OrderManagementService (update status), EmailService (receipt email).
     */
    private void publishPaymentConfirmedEvent(String orderId, Long amount,
                                               String phoneNumber, String paymentLinkUrl,
                                               String gatewayName) {
        try {
            PaymentConfirmedEventDto event = new PaymentConfirmedEventDto();
            event.setOrderId(orderId);
            event.setAmount(amount);
            event.setPhoneNumber(phoneNumber);
            event.setPaymentLinkUrl(paymentLinkUrl);
            event.setPaymentGateway(gatewayName);
            event.setStatus("PAYMENT_LINK_GENERATED");

            String message = objectMapper.writeValueAsString(event);
            kafkaProducerClient.sendMessage("payment-confirmed", message);
            System.out.println("Published payment-confirmed event for orderId: " + orderId);
        } catch (Exception e) {
            System.err.println("Failed to publish payment-confirmed event: " + e.getMessage());
        }
    }
}
