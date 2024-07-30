package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.enums.PaymentStatus;
import com.example.GroupPayMerchant.models.MerchantTransactions;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MerchantTransactionService {

    Map<String, Object> createTransaction(UUID userId, double amount, UUID bookingId);

    boolean updateStatus(UUID paymentRefId, PaymentStatus status);

    List<MerchantTransactions> getSuccessfulTransactions(UUID bookingId);
}
