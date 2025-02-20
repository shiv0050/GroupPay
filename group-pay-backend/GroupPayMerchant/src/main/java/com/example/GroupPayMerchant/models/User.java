package com.example.GroupPayMerchant.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Data
@Table(name = "merchant_user")
public class User {

    @Id
    @Column(name = "User_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID userId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;


}
