package com.example.GroupPay.controllers;

import com.example.GroupPay.model.requests.AddUserRequest;
import com.example.GroupPay.model.requests.LoginRequest;
import com.example.GroupPay.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@CrossOrigin(origins = "http://localhost:3000")

@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest body){
        return ResponseEntity.ok(getMapResponse(userService.login(body.getUserName(), body.getPassword())));

    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody AddUserRequest body){
        return ResponseEntity.ok(getMapResponse(userService.register(body)));
    }

    private Map<String, Object> getMapResponse(Map<String, Object> body){
        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.putAll(body);
        return res;
    }



}
