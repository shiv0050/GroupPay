package com.example.GroupPayMerchant.models.responses;

import com.example.GroupPayMerchant.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private UUID paymentRefId;
    private LocalDateTime createdAt;
    private PaymentStatus paymentStatus;
    private String name;
    private String email;
    private Double amount;
}
