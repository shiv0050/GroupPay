package com.example.GroupPayMerchant.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "merchant_user")
public class User {

    @Id
    @Column(name = "User_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Email", nullable = false, unique = true)
    private String email;
    @Column(name = "Password", nullable = false)
    private String password;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
