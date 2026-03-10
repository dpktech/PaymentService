package org.example.paymentservice.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Kafka producer for PaymentService.
 * Same pattern as AuthService and CartService KafkaProducerClient.
 */
@Component
public class KafkaProducerClient {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
