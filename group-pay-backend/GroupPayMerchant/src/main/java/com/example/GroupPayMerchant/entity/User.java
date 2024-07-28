package com.example.GroupPayMerchant.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "Merchant_User")
public class User {

    @Id
    @Column(name = "User_id")
    private UUID userId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Username")
    private String userName;
    @Column(name = "Email")
    private String email;
    private String password;

}
