package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.enums.PaymentStatus;
import com.example.GroupPayMerchant.models.MerchantTransactions;
import com.example.GroupPayMerchant.repository.MerchantTransactionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MerchantTransactionServiceImpl implements MerchantTransactionService {

    @Autowired
    MerchantTransactionsRepo transactionsRepo;

    @Override
    public Map<String, Object> createTransaction(UUID userId, double amount, UUID bookingId) {
        MerchantTransactions transaction = new MerchantTransactions();
        transaction.setUserId(userId);
        transaction.setAmount(amount);
        transaction.setBookingId(bookingId);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setPaymentStatus(PaymentStatus.PENDING);

        transaction = transactionsRepo.save(transaction);

        Map<String, Object> res = new HashMap<>();
        res.put("data", transaction);
        return res;
    }

    @Override
    public boolean updateStatus(UUID paymentRefId, PaymentStatus status) {
        MerchantTransactions transaction = transactionsRepo.findByPaymentRefId(paymentRefId);
        transaction.setPaymentStatus(status);

        transaction = transactionsRepo.save(transaction);

        return true;
    }

    @Override
    public List<MerchantTransactions> getSuccessfulTransactions(UUID bookingId) {
        List<MerchantTransactions> result = transactionsRepo.findAllByBookingId(bookingId);
        return result.stream().filter(item->item.getPaymentStatus().equals(PaymentStatus.APPROVED)).collect(Collectors.toList());
    }



}
