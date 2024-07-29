package com.example.GroupPayMerchant.models.responses;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Getter
@Data
public class AddUserResponse {

    private UUID userId;
    private String name;
    private String email;

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }


    public void setEmail(String email) {
        this.email = email;
    }

}
