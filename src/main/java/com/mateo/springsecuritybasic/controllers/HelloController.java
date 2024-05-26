package com.mateo.springsecuritybasic.controllers;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class HelloController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/public/hello")
    public String helloGet() {
        return "hello public GET";
    }

    @GetMapping("/private/hello")
    public String helloGetPrivate() {
        return "helo private GET";
    }

    @GetMapping("/")
    public String hello(){
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        String p = context.getAuthentication().getDetails().toString();
        return "Bienvenido " + username + p;
    }

    @PostMapping("/public/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        try {
            // Autenticar al usuario utilizando el AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

                    
            
            // Si la autenticación es exitosa, devolver un mensaje de éxito
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } catch (Exception e) {
            // Si la autenticación falla, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación: " + e.getMessage());
        }
    }

    public static class AuthRequest {
        private String username;
        private String password;

        public AuthRequest(String username,String password){
            this.username = username;
            this.password = password;
        }

        public AuthRequest(){}

        public String getPassword() {
            return password;
        }

        public String getUsername() {
            return username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
    
}
