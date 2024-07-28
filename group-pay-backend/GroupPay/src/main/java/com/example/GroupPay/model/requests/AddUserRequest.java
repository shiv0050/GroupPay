package com.example.GroupPay.model.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AddUserRequest {

    @NotBlank(message = "Name cannot be empty")
    private String firstName;

    private String lastName;

    @NotBlank(message = "Phone cannot be empty")
    private String phone;

    private String dob;

    @NotBlank(message = "Address cannot be empty")
    private String addressLine1;

    @NotBlank(message = "State cannot be empty")
    private String state;

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "Postal code cannot be empty")
    private String postalCode;

    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Government id cannot be empty")
    private String governmentId;

    @NotBlank(message = "Pin cannot be empty")
    private String pin;

    public String getPassword() {
        return password;
    }

    public String getPin() {
        return pin;
    }

    public String getDob() {
        return dob;
    }
}
