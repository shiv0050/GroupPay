package com.example.GroupPay.repositories;

import com.example.GroupPay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailIgnoreCaseOrPhone(String email, String phone);
}
