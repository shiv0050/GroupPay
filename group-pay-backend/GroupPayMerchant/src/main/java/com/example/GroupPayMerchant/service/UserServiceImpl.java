package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.entity.User;
import com.example.GroupPayMerchant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;


    @Override
    public boolean loginUser(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user == null) return false;
        return user.getPassword().equals(password);
    }

    @Override
    public boolean signUp(User user) {
        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser != null) return false;
        user.setUserId(UUID.randomUUID()); // Generate UUID before saving
        userRepo.save(user);
        return true;
    }
}
