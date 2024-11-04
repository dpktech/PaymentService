package org.example.paymentservice_sept2024.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InititatePaymentDto {
    Long amount;
    String orderId;
    String phoneNumber;
    String name;
}
