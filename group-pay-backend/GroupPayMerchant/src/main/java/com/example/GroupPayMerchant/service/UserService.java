package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.models.requests.AddUserRequest;

import java.util.Map;

public interface UserService {

<<<<<<< Updated upstream
    public String  loginUser(String email, String password);
=======
    Map<String, Object>  loginUser(String email, String password);
>>>>>>> Stashed changes

    public Map<String, Object> signUp(AddUserRequest body);
}
