package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.models.User;
import com.example.GroupPayMerchant.models.requests.AddUserRequest;
import com.example.GroupPayMerchant.models.responses.AddUserResponse;
import com.example.GroupPayMerchant.exceptions.InvalidCredentialException;
import com.example.GroupPayMerchant.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.GroupPayMerchant.utils.JWTUtil;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public Map<String, Object> loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                Map<String, Object> res = new HashMap<>();
                res.put("userToken", getToken("" + user.getUserId()));
                res.put("user", user);
                return res;
            }

            throw new InvalidCredentialException("Invalid Password");
        }

        throw new InvalidCredentialException("Invalid Email ID");
    }

    @Override
    public Map<String, Object> signUp(AddUserRequest userRequest) {
        User user = new User();
        AddUserResponse newUser = new AddUserResponse();
        BeanUtils.copyProperties(userRequest, user, "password");
        String encodedPass = passwordEncoder.encode(userRequest.getPassword());
        user.setPassword(encodedPass);

        user = userRepository.save(user);

        newUser.setUserId(user.getUserId());
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());

        Map<String, Object> res = new HashMap<>();
        res.put("user", newUser);
        res.put("userToken", getToken("" + newUser.getUserId()));
        return res;
    }

    public String getToken(String subject) {
        return jwtUtil.generateToken(subject);
    }
}
