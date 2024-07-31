package com.example.GroupPayMerchant.models.requests;

import com.example.GroupPayMerchant.enums.PaymentStatus;

import java.time.LocalDateTime;

public interface TxnResponse {
    double getAmount();
    LocalDateTime getCreatedAt();
    PaymentStatus getPaymentStatus();
    String getName();
    String getEmail();

}
