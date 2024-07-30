package com.example.GroupPayMerchant.models.requests;

import com.example.GroupPayMerchant.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class StatusUpdate {

    @NotBlank(message = "paymentRefId cannot be empty")
    private UUID paymentRefId;

    @NotBlank(message = "Payment Status cannot be empty")
    private PaymentStatus status;
}
