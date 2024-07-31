package com.example.GroupPayMerchant.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddUserRequest {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    public String getPassword() {
        return password;
    }
}
