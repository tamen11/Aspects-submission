package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

    private final StringRedisTemplate redisTemplate;

    public String getUserFromCache(String id) {
        String key = "user:" + id;
        String cached = redisTemplate.opsForValue().get(key);

        if (cached != null) {
            return "[CACHE] User: " + cached;
        }

        // Simulate DB fetch
        String user = "UserFromDB-" + id;
        redisTemplate.opsForValue().set(key, user, 60, TimeUnit.SECONDS);
        return "[DB] User: " + user;
    }
}