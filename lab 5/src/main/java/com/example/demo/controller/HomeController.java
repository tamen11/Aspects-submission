package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class HomeController {

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/hello")
    @PreAuthorize("isAuthenticated()")
    public String userAccess() {
        return "Hello, Authenticated User!";
    }
}
