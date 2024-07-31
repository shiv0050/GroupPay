package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.enums.PaymentStatus;
import com.example.GroupPayMerchant.enums.Status;
import com.example.GroupPayMerchant.models.BookingDetails;
import com.example.GroupPayMerchant.models.MerchantTransactions;
import com.example.GroupPayMerchant.models.requests.TxnResponse;
import com.example.GroupPayMerchant.models.responses.TransactionResponse;
import com.example.GroupPayMerchant.repository.MerchantTransactionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MerchantTransactionServiceImpl implements MerchantTransactionService {

    @Autowired
    MerchantTransactionsRepo transactionsRepo;

    @Autowired
    BookingService bookingService;

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

        BookingDetails bookingDetails = bookingService.getBookingById(transaction.getBookingId());

        checkOrderComplete(transaction.getBookingId(),bookingDetails.getNumberOfContributors());

        return true;
    }

    protected void checkOrderComplete(UUID bookingId, int numOfContributors){
        long res = transactionsRepo.checkOrderComplete(bookingId);
        if(numOfContributors != res)
            return;
        bookingService.updateStatus(bookingId, Status.SUCCESSFUL);
        transactionsRepo.markTransactionCompleted(bookingId);
    }

    @Override
    public List<TransactionResponse> getSuccessfulTransactions(UUID bookingId) {
        List<TxnResponse> result = transactionsRepo.getAllTransactions(bookingId);
        return result.stream().map(item-> new TransactionResponse(item.getAmount(), item.getCreatedAt(), item.getPaymentStatus(), item.getName(), item.getEmail())).collect(Collectors.toList());
    }



}
