package com.example.GroupPayMerchant.models.requests;

import com.example.GroupPayMerchant.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TxnResponse {
    double getAmount();
    UUID getRefernceId();
    LocalDateTime getCreatedAt();
    PaymentStatus getPaymentStatus();
    String getName();
    String getEmail();

}
