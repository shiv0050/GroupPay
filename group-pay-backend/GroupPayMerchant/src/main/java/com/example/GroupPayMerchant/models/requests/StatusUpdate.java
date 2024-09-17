package com.example.GroupPayMerchant.models.requests;

import com.example.GroupPayMerchant.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class StatusUpdate {

    private UUID paymentRefId;

    private PaymentStatus status;
}
