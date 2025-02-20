package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.models.User;
import com.example.GroupPayMerchant.models.requests.AddUserRequest;
import com.example.GroupPayMerchant.models.responses.UserResponse;
import org.apache.tomcat.util.json.ParseException;

import java.util.Map;
import java.util.UUID;

public interface UserService {

    Map<String, Object>  loginUser(String email, String password);

    Map<String, Object> register(AddUserRequest body);

    UserResponse getUserDetails(String authHeader);

    UserResponse getUserDetailsById(UUID id);

}
