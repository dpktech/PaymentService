package org.example.paymentservice_sept2024.paymentgateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayChooserStrategy {

    @Autowired
    private RazorpayPaymentGateway razorpayPaymentGateway;

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    public PaymentGateway getPaymentGateway() {
        //ToDo -: you need to generate a random number, call stripe if its even , razorpay otherwise

        return stripePaymentGateway;
    }
}
