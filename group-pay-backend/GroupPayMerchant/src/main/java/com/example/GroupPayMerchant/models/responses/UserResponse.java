package com.example.GroupPayMerchant.models.responses;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {

    private UUID userId;
    private String name;
    private String email;

}
