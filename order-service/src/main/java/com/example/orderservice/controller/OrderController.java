package com.example.orderservice.controller;

import com.example.orderservice.client.UserClient;
import com.example.orderservice.dto.response.OrderResponse;
import com.example.orderservice.dto.response.UserDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final UserClient userClient;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @RequestMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable("id") Long id) {
        Order order = orderRepository.findById(id).orElseThrow();

//        Gọi user-service bằng Feign Client
        UserDto user = userClient.getUserById(order.getUserId());

        return OrderResponse.builder()
                .id(order.getId())
                .product(order.getProduct())
                .price(order.getPrice())
                .user(user) // Thông tin người dùng từ user-service
                .build();
    }
}
