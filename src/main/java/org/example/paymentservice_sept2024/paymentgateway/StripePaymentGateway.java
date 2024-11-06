package org.example.paymentservice_sept2024.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway {

    @Value("${stripe.apikey}")
    private String apiKey;

    @Override
    public String getPaymentLink(Long amount, String orderId, String phoneNumber, String name) {
       try {
           Stripe.apiKey = this.apiKey;
           Price price = getPrice(amount);
           PaymentLinkCreateParams params =
                   PaymentLinkCreateParams.builder()
                           .addLineItem(
                                   PaymentLinkCreateParams.LineItem.builder()
                                           .setPrice(price.getId())
                                           .setQuantity(1L)
                                           .build()
                           ).setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                   .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                   .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                           .setUrl("https://scaler.com")
                                           .build()).build())
                           .build();

           PaymentLink paymentLink = PaymentLink.create(params);
           return paymentLink.getUrl().toString();
       }catch (StripeException ex) {
           throw new RuntimeException(ex.getMessage());
       }
    }

    private Price getPrice(Long amount) {
        try {
            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("usd")
                            .setUnitAmount(amount)
                            .setRecurring(
                                    PriceCreateParams.Recurring.builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .build()
                            )
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                            )
                            .build();

            Price price = Price.create(params);
            return  price;
        }catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
