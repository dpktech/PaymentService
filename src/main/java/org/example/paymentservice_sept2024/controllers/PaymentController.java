package org.example.paymentservice_sept2024.controllers;

import org.example.paymentservice_sept2024.dtos.InititatePaymentDto;
import org.example.paymentservice_sept2024.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public String initiatePayment(@RequestBody InititatePaymentDto inititatePaymentDto) {
        return paymentService.getPaymentLink(inititatePaymentDto.getAmount(),inititatePaymentDto.getOrderId(),inititatePaymentDto.getPhoneNumber(),inititatePaymentDto.getName());
    }
}
