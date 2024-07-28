package com.example.GroupPayMerchant.controller;

import com.example.GroupPayMerchant.entity.User;
import com.example.GroupPayMerchant.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/user")
    private ResponseEntity<String> signUp(@RequestBody User user) {
        boolean userCreated = userServiceImpl.signUp(user);
        if (userCreated) {
            return ResponseEntity.ok("User created successfully!");
        } else {
            return ResponseEntity.status(401).body("User already exists with this email!");
        }
    }

    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        boolean isAuthenticated = userServiceImpl.loginUser(email, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password!");
        }
    }
}
