package com.example.orderservice.client;

import com.example.orderservice.dto.response.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Bạn chỉ cần chỉ định @FeignClient(name = "user-service"), phần còn lại sẽ được Spring tự động làm:
 * Tìm user-service trong Eureka
 * Gửi HTTP request tới /api/users/{id}
 * Parse JSON response thành UserDto
 */
@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/users/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
}
