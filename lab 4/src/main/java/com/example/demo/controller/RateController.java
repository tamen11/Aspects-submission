package com.example.demo.controller;

import com.example.demo.annotation.RateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateController {

    @RateLimit(key = "rate:test", time = 60, count = 5)
    @GetMapping("/rate")
    public String rateLimitedEndpoint() {
        return "Success - you are within the rate limit!";
    }
}