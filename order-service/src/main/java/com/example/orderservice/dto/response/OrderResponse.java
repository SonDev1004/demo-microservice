package com.example.orderservice.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private String product;
    private Double price;
    private UserDto user; // Thông tin người dùng từ user-service
}
