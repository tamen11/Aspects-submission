package com.example.demo.aspect;

import com.example.demo.annotation.RateLimit;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitAspect {

    private final StringRedisTemplate redisTemplate;

    @Around("@annotation(rateLimit)")
    public Object around(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String key = rateLimit.key();
        int time = rateLimit.time();
        int count = rateLimit.count();

        Long currentCount = redisTemplate.opsForValue().increment(key);
        if (currentCount == 1) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }

        if (currentCount > count) {
            throw new RuntimeException("Rate limit exceeded");
        }

        return joinPoint.proceed();
    }
}