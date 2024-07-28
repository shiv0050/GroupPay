package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.entity.User;

public interface UserService {

    boolean loginUser(String email, String password);

    boolean signUp(User user);
}
