package com.example.GroupPay.model.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
public class CreateOrderRequest {

    @NotBlank(message = "Merchant id cannot be blank")
    private UUID merchantId;

    @NotBlank(message = "Merchant name cannot be blank")
    private String merchantName;

    @NotBlank(message = "Amount cannot be blank")
    private double amount;

    @NotBlank(message = "No of contributors cannot be blank")
    private int numberOfContributors;

    @NotBlank(message = "Expiry hour cannot be blank")
    @Getter
    private int expiry;

    @NotBlank(message = "Reference id cannot be blank")
    private UUID referenceId;

}
