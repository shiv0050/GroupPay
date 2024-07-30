package com.example.GroupPayMerchant.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class AddTransaction {

    @NotBlank(message = "userId cannot be empty")
    private UUID userId;

    @NotBlank(message = "userId cannot be empty")
    private UUID bookingId;

    @NotBlank(message = "Amount cannot be empty")
    private Double amount;


}
