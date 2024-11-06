package org.example.paymentservice_sept2024.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripeWebhook")
public class StripeWebhookController {

    @PostMapping
    public void respondToEvent(@RequestBody String stripeJsonEvent) {
        System.out.println(stripeJsonEvent);
    }
}

/*
{
  "id": "evt_1QHzRMSBnugwUBr5yVWGZLZx",
  "object": "event",
  "api_version": "2023-10-16",
  "created": 1730861872,
  "data": {
    "object": {
      "id": "plink_1QHzRMSBnugwUBr5BC4ak1Y5",
      "object": "payment_link",
      "active": true,
      "after_completion": {
        "redirect": {
          "url": "https://scaler.com"
        },
        "type": "redirect"
      },
      "allow_promotion_codes": false,
      "application": null,
      "application_fee_amount": null,
      "application_fee_percent": null,
      "automatic_tax": {
        "enabled": false,
        "liability": null
      },
      "billing_address_collection": "auto",
      "consent_collection": null,
      "currency": "usd",
      "custom_fields": [

      ],
      "custom_text": {
        "after_submit": null,
        "shipping_address": null,
        "submit": null,
        "terms_of_service_acceptance": null
      },
      "customer_creation": "if_required",
      "inactive_message": null,
      "invoice_creation": {
        "enabled": false,
        "invoice_data": {
          "account_tax_ids": null,
          "custom_fields": null,
          "description": null,
          "footer": null,
          "issuer": null,
          "metadata": {
          },
          "rendering_options": null
        }
      },
      "livemode": false,
      "metadata": {
      },
      "on_behalf_of": null,
      "payment_intent_data": null,
      "payment_method_collection": "always",
      "payment_method_types": null,
      "phone_number_collection": {
        "enabled": false
      },
      "restrictions": null,
      "shipping_address_collection": null,
      "shipping_options": [

      ],
      "submit_type": "auto",
      "subscription_data": {
        "description": null,
        "invoice_settings": {
          "issuer": {
            "type": "self"
          }
        },
        "metadata": {
        },
        "trial_period_days": null,
        "trial_settings": {
          "end_behavior": {
            "missing_payment_method": "create_invoice"
          }
        }
      },
      "tax_id_collection": {
        "enabled": false,
        "required": "never"
      },
      "transfer_data": null,
      "url": "https://buy.stripe.com/test_14keVKeyOdYneKQ29r"
    }
  },
  "livemode": false,
  "pending_webhooks": 4,
  "request": {
    "id": "req_Ysz7VvaTVnBm1X",
    "idempotency_key": "e41d7e19-90c8-4786-9918-a780847cee28"
  },
  "type": "payment_link.created"
}
{
  "id": "evt_1QHzU3SBnugwUBr5w4QThPON",
  "object": "event",
  "api_version": "2023-10-16",
  "created": 1730862039,
  "data": {
    "object": {
      "id": "cs_test_a11MVbFs9D5cEDn3CPGu4sjqGRkzVF0PcmIa0FqSA3FHRwIsS9zklsmbEW",
      "object": "checkout.session",
      "after_expiration": null,
      "allow_promotion_codes": false,
      "amount_subtotal": 1000,
      "amount_total": 1000,
      "automatic_tax": {
        "enabled": false,
        "liability": null,
        "status": null
      },
      "billing_address_collection": "auto",
      "cancel_url": "https://stripe.com",
      "client_reference_id": null,
      "client_secret": null,
      "consent": null,
      "consent_collection": null,
      "created": 1730861999,
      "currency": "usd",
      "currency_conversion": null,
      "custom_fields": [

      ],
      "custom_text": {
        "after_submit": null,
        "shipping_address": null,
        "submit": null,
        "terms_of_service_acceptance": null
      },
      "customer": "cus_RAK8n6S1CzNHdh",
      "customer_creation": "if_required",
      "customer_details": {
        "address": {
          "city": "Seattle",
          "country": "US",
          "line1": "abc",
          "line2": "def",
          "postal_code": "98101",
          "state": "WA"
        },
        "email": "anurag.khanna_1@scaler.com",
        "name": "Anurag Khanna",
        "phone": null,
        "tax_exempt": "none",
        "tax_ids": [

        ]
      },
      "customer_email": null,
      "expires_at": 1730948399,
      "invoice": "in_1QHzTwSBnugwUBr5Y2E2qnit",
      "invoice_creation": null,
      "livemode": false,
      "locale": "auto",
      "metadata": {
      },
      "mode": "subscription",
      "payment_intent": null,
      "payment_link": "plink_1QHzRMSBnugwUBr5BC4ak1Y5",
      "payment_method_collection": "always",
      "payment_method_configuration_details": {
        "id": "pmc_1OwTSXSBnugwUBr5mnaWpl5w",
        "parent": null
      },
      "payment_method_options": {
        "card": {
          "request_three_d_secure": "automatic"
        }
      },
      "payment_method_types": [
        "card"
      ],
      "payment_status": "paid",
      "phone_number_collection": {
        "enabled": false
      },
      "recovered_from": null,
      "saved_payment_method_options": {
        "allow_redisplay_filters": [
          "always"
        ],
        "payment_method_remove": null,
        "payment_method_save": null
      },
      "setup_intent": null,
      "shipping_address_collection": null,
      "shipping_cost": null,
      "shipping_details": null,
      "shipping_options": [

      ],
      "status": "complete",
      "submit_type": "auto",
      "subscription": "sub_1QHzTwSBnugwUBr58JZE1i6k",
      "success_url": "https://scaler.com",
      "total_details": {
        "amount_discount": 0,
        "amount_shipping": 0,
        "amount_tax": 0
      },
      "ui_mode": "hosted",
      "url": null
    }
  },
  "livemode": false,
  "pending_webhooks": 3,
  "request": {
    "id": null,
    "idempotency_key": null
  },
  "type": "checkout.session.completed"
}

 */
