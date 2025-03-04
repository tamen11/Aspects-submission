package com.example.demo.controllers;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.demo.controllers.*.*(..))")
    public void logBeforeController() {
        System.out.println("method is about to be called...");
    }
}
