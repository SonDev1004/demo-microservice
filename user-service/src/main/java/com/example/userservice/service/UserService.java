package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j(topic = "USER_SERVICE")
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Cacheable("allUsers")
    public List<User> getAllUsers(){
        log.info("Querying DB...");
        return userRepository.findAll();
    }

    @CacheEvict(value = "allUsers", allEntries = true)
    public User createUser(User user) {
        log.info("Creating user: {}", user);
        return userRepository.save(user);
    }
}
