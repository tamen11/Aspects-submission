package com.example.demo.aspect;

import com.example.demo.annotation.RedisLock;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
public class RedisLockAspect {

    private final StringRedisTemplate redisTemplate;

    @Around("@annotation(redisLock)")
    public Object around(ProceedingJoinPoint joinPoint, RedisLock redisLock) throws Throwable {
        String key = redisLock.key();
        String value = UUID.randomUUID().toString();

        Boolean success = redisTemplate.opsForValue()
                .setIfAbsent(key, value, redisLock.expire(), TimeUnit.SECONDS);

        if (Boolean.FALSE.equals(success)) {
            throw new RuntimeException("Could not acquire lock");
        }

        try {
            return joinPoint.proceed();
        } finally {
            redisTemplate.delete(key);
        }
    }
}