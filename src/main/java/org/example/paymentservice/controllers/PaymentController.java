package org.example.paymentservice.controllers;

import org.example.paymentservice.dtos.InititatePaymentDto;
import org.example.paymentservice.services.PaymentService;
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
