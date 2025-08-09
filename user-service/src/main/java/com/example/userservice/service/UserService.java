package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Cacheable("allUsers")
    public List<User> getAllUsers(){
        System.out.println(" %%%%%%%%%%%%%%%% Querying DB...");
        return userRepository.findAll();
    }
}
