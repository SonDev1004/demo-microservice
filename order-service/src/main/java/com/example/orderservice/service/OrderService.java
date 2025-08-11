package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.event.OrderPlacedEvent;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j(topic = "ORDER-SERVICE")
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public Order createOrder(Order order) {
        Order saved = orderRepository.save(order);

        // Gửi event kafka
        OrderPlacedEvent event = OrderPlacedEvent.builder()
                .orderId(saved.getId())
                .userId(saved.getUserId())
                .total(saved.getTotal())
                .build();
        kafkaTemplate.send("order-topic", event);
        log.info("Đã gửi kafka event: {}", event);

        return saved;
    }
}
