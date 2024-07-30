package com.example.GroupPay.services;

import com.example.GroupPay.model.requests.CreateTransactionRequest;

public interface TransactionService {
    public boolean createTransaction(CreateTransactionRequest transactionRequest, long userId);
}
