package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resource")
public class controllers {

    @GetMapping
    public String getResource() {
        return "GET";
    }

    @PostMapping
    public String createResource() {
        return "POST";
    }

    @PutMapping
    public String updateResource() {
        return "PUT";
    }

    @DeleteMapping
    public String deleteResource() {
        return "DELETE";
    }
}
