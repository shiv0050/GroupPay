package com.example.GroupPay.services;

import com.example.GroupPay.model.requests.AddUserRequest;
import com.example.GroupPay.model.responses.AddUserResponse;

import java.util.Map;

public interface UserService {

    public String login(String username, String password);

    public Map<String, Object> register(AddUserRequest body);
}
