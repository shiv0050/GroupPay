package com.example.GroupPay.repositories;


import com.example.GroupPay.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TransactionRespository extends JpaRepository<Transaction, UUID> {

    @Query(value = "SELECT count(*) from bankofapis.transactions WHERE order_id = :orderId AND status = 'APPROVED';", nativeQuery = true)
    long checkOrderComplete(UUID orderId);

    @Query(value = "UPDATE bankofapis.transactions SET status = 'COMPLETED' where order_id = :orderId AND status = 'APPROVED';", nativeQuery = true)
    void markTransactionCompleted(UUID orderId);
}
