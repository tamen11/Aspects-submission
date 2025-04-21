package com.example.demo.controller;

import com.example.demo.annotation.RedisLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LockTestController {

    @RedisLock(key = "lock:test", expire = 10)
    @GetMapping("/lock")
    public String lockTest() throws InterruptedException {
        Thread.sleep(10000); // Simulate long processing
        return "Processed with lock!";
    }
}