package com.example.GroupPay.services.impl;

import com.example.GroupPay.exceptions.InvalidCredentialException;
import com.example.GroupPay.model.User;
import com.example.GroupPay.model.requests.AddUserRequest;
import com.example.GroupPay.model.responses.AddUserResponse;
import com.example.GroupPay.repositories.UserRepository;
import com.example.GroupPay.services.UserService;
import com.example.GroupPay.utils.ConvertDate;
import com.example.GroupPay.utils.JWTUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public Map<String, Object> login(String username, String password) {
        User user = userRepository.findByEmailIgnoreCaseOrPhone(username, username);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                AddUserResponse newUser = new AddUserResponse();
                newUser.setUserId(user.getId());
                newUser.setFirstName(user.getFirstName());
                newUser.setLastName(user.getLastName());
                newUser.setEmail(user.getEmail());

                Map<String, Object> res = new HashMap<>();
                res.put("user", newUser);
                res.put("userToken", getToken("" + newUser.getUserId()));

                return res;
            }

            throw new InvalidCredentialException("Invalid Password");
        }

        throw new InvalidCredentialException("Invalid Username");
    }

    @Override
    public Map<String, Object> register(AddUserRequest userRequest) {
        User user = new User();
        AddUserResponse newUser = new AddUserResponse();
        BeanUtils.copyProperties(userRequest, user, "password", "pin", "dob");
        String encodedPass = passwordEncoder.encode(userRequest.getPassword());
        String encodedPin = passwordEncoder.encode(userRequest.getPin());
        user.setPassword(encodedPass);
        user.setPin(encodedPin);
        user.setDob(ConvertDate.parseDate(userRequest.getDob()));

        user = userRepository.save(user);

        newUser.setUserId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
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
