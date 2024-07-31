package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.models.requests.AddUserRequest;

import java.util.Map;

public interface UserService {

    public String  loginUser(String email, String password);

    public Map<String, Object> signUp(AddUserRequest body);
}
