package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.enums.PaymentStatus;
import com.example.GroupPayMerchant.enums.Status;
import com.example.GroupPayMerchant.models.BookingDetails;
import com.example.GroupPayMerchant.models.MerchantTransactions;
import com.example.GroupPayMerchant.models.requests.TxnResponse;
import com.example.GroupPayMerchant.models.responses.TransactionResponse;
import com.example.GroupPayMerchant.models.responses.UserResponse;
import com.example.GroupPayMerchant.repository.MerchantTransactionsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MerchantTransactionServiceImpl implements MerchantTransactionService {

    @Autowired
    MerchantTransactionsRepo transactionsRepo;

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;

    @Override
    public TransactionResponse createTransaction(UUID userId, double amount, UUID bookingId) {
        MerchantTransactions transaction = new MerchantTransactions();
        transaction.setUserId(userId);
        transaction.setAmount(amount);
        transaction.setBookingId(bookingId);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setPaymentStatus(PaymentStatus.PENDING);

        transaction = transactionsRepo.save(transaction);
        UserResponse user = userService.getUserDetailsById(userId);

        TransactionResponse res = new TransactionResponse();
        res.setPaymentRefId(transaction.getPaymentRefId());
        res.setAmount(transaction.getAmount());
        res.setCreatedAt(transaction.getCreatedAt());
        res.setPaymentStatus(transaction.getPaymentStatus());
        res.setName(user.getName());
        res.setEmail(user.getEmail());

        return res;
    }

    @Override
    public boolean updateStatus(UUID paymentRefId, PaymentStatus status) {
        MerchantTransactions transaction = transactionsRepo.findByPaymentRefId(paymentRefId);
        transaction.setPaymentStatus(status);
        transactionsRepo.save(transaction);
        return true;

    }

    private boolean isOrderComplete(UUID bookingId, int numOfContributors){
        long res = transactionsRepo.checkOrderComplete(bookingId.toString());
        if(numOfContributors != res)
            return false;
        bookingService.updateStatus(bookingId, Status.SUCCESSFUL);
        return true;
    }

    @Override
    public List<TransactionResponse> getSuccessfulTransactions(UUID bookingId) {
//        log.info("booking Id - " + bookingId);
        List<MerchantTransactions> result = transactionsRepo.findAllByPaymentRefId(bookingId);
        return result.stream().map(item-> {
            UserResponse user = userService.getUserDetailsById(item.getUserId());
            return new TransactionResponse(item.getPaymentRefId(), item.getCreatedAt(), item.getPaymentStatus(), user.getName(), user.getEmail(),item.getAmount());
        }).collect(Collectors.toList());
    }

    @Override
    public boolean checkCompletionStatus(UUID bookingId) {
        BookingDetails bookingDetails = bookingService.getBookingById(bookingId);
        return isOrderComplete(bookingId,bookingDetails.getNumberOfContributors());
    }


}
