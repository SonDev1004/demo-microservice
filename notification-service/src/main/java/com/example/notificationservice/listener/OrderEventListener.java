package com.example.notificationservice.listener;

import com.example.notificationservice.event.OrderPlacedEvent;
import com.example.notificationservice.serivce.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j(topic = "ORDER_EVENT_LISTENER")
@Component
public class OrderEventListener {

    private final EmailService emailService;

    public OrderEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(
            topics = "order-topic",
            groupId = "${spring.kafka.consumer.group-id}", // "notification-group" from application.yaml
            containerFactory = "orderPlacedEventListenerFactory")
    public void handleOrderEvent(OrderPlacedEvent event) {
        log.info("Nhận được event từ Kafka: {}", event);
        // Thực hiện gửi email ở đây
        emailService.sendOrderEmail(event);
    }
}
