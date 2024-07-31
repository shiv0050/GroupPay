package com.example.GroupPay.repositories;


import com.example.GroupPay.model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TransactionRespository extends JpaRepository<Transaction, UUID> {

    @Query(value = "SELECT count(*) from bankofapis.transactions WHERE order_reference_id = :orderReferenceId AND status = 'APPROVED';", nativeQuery = true)
    long checkOrderComplete(@Param("orderReferenceId") UUID orderReferenceId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE bankofapis.transactions SET status = 'COMPLETED' where order_reference_id = :orderId AND status = 'APPROVED';", nativeQuery = true)
    void markTransactionCompleted(@Param("orderId") UUID orderId);
}
