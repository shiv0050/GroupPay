package com.example.GroupPay.model.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
public class CreateOrderRequest {

    @NotNull(message = "Merchant id cannot be blank")
    private UUID merchantId;

    @NotBlank(message = "Merchant name cannot be blank")
    private String merchantName;

    @NotNull(message = "Amount cannot be blank")
    private double amount;

    @NotNull(message = "No of contributors cannot be blank")
    private int numberOfContributors;

    @NotNull(message = "Expiry hour cannot be blank")
    @Getter
    private int expiry;

    @NotNull(message = "Reference id cannot be blank")
    private UUID referenceId;

}
