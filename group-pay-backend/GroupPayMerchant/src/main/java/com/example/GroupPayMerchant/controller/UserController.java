package com.example.GroupPayMerchant.controller;


import com.example.GroupPayMerchant.models.requests.AddUserRequest;
import com.example.GroupPayMerchant.models.requests.LoginRequest;
import com.example.GroupPayMerchant.models.responses.UserResponse;
import com.example.GroupPayMerchant.service.UserService;

import jakarta.validation.Valid;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/merchant-user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest body) {
        try{
            Map<String, Object> res = userService.loginUser(body.getEmail(), body.getPassword());
            res.put("success", true);
            return ResponseEntity.ok(res);
        } catch (RuntimeException e){
            Map<String, Object> res = new HashMap<>();
            res.put("success", false);
            res.put("error", "Invalid username or password");
            return ResponseEntity.badRequest().body(res);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody AddUserRequest body) {
        Map<String, Object> res = userService.register(body);
        res.put("success", true);
        return ResponseEntity.ok(res);
    }

    private Map<String, Object> getMapResponse(String token) {
        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("userToken", token);

        return res;
    }

    @GetMapping("/userInfo")
    public UserResponse getUser(@RequestHeader("Authorization") String header) throws ParseException {
        return userService.getUserDetails(header);
    }

}




