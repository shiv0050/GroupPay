package com.example.GroupPay.services;

import com.example.GroupPay.model.requests.AddUserRequest;

import java.util.Map;

public interface UserService {

    public Map<String, Object> login(String username, String password);

    public Map<String, Object> register(AddUserRequest body);
}
