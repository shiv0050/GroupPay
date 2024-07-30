package com.example.GroupPayMerchant.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;


@Data
public class LoginRequest {

    @NotBlank(message = "Email ID cannot be null")
    private String email;

    @NotBlank(message = "Password cannot be null")
    private String password;

}
