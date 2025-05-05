package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUser(String username) {
        // For demo: one hardcoded user
        if ("user".equals(username)) {
            return new User("user", "password");
        }
        return null;
    }
}
