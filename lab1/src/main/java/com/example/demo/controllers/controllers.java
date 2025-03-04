package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resource")
public class controllers {

    @GetMapping
    public String getResource() {
        return "GET: Retrieved resource";
    }

    @PostMapping
    public String createResource() {
        return "POST: Resource created successfully";
    }

    @PutMapping
    public String updateResource() {
        return "PUT: Resource updated successfully";
    }

    @DeleteMapping
    public String deleteResource() {
        return "DELETE: Resource deleted successfully";
    }
}
