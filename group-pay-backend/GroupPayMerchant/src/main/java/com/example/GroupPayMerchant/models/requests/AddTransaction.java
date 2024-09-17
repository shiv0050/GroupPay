package com.example.GroupPayMerchant.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class AddTransaction {

    private UUID userId;

    private UUID bookingId;

    private double amount;


}
