package com.example.GroupPayMerchant.models.responses;

import com.example.GroupPayMerchant.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private double amount;
    private LocalDateTime createdAt;
    private PaymentStatus paymentStatus;
    private String name;
    private String email;

    public TransactionResponse( double amount, LocalDateTime createdAt, PaymentStatus paymentStatus, String name, String email) {
        this.amount = amount;
        this.createdAt = createdAt;
        this.paymentStatus = paymentStatus;
        this.name = name;
        this.email = email;
    }
}
