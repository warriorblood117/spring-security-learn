package com.mateo.springsecuritybasic.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloController {

    @GetMapping("/public/hello")
    public String helloGet() {
        return "hello public GET";
    }

    @GetMapping("/private/hello")
    public String helloGetPrivate() {
        return "helo private GET";
    }
    
    
}
