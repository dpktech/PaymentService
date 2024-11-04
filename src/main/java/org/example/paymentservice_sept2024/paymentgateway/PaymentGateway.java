package org.example.paymentservice_sept2024.paymentgateway;

public interface PaymentGateway {
    String getPaymentLink(Long amount,String orderId,String phoneNumber,String name);
}
