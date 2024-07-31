package com.example.GroupPayMerchant.controller;

import com.example.GroupPayMerchant.models.User;
import com.example.GroupPayMerchant.models.requests.AddUserRequest;
import com.example.GroupPayMerchant.models.requests.LoginRequest;
import com.example.GroupPayMerchant.service.UserService;
import com.example.GroupPayMerchant.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/merchant-user")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest body) {
        return ResponseEntity.ok(getMapResponse(userService.loginUser(body.getEmail(), body.getPassword())));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody AddUserRequest body) {
        Map<String, Object> res = userService.signUp(body);
        res.put("success", true);
        return ResponseEntity.ok(res);
    }

    private Map<String, Object> getMapResponse(String token) {
        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("userToken", token);

        return res;
    }
}




