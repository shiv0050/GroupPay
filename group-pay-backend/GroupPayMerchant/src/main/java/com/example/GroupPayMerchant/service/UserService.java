package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.models.requests.AddUserRequest;

import java.util.Map;

public interface UserService {

    String  loginUser(String email, String password);

    Map<String, Object> signUp(AddUserRequest body);
}
