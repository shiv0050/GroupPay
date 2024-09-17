package com.example.GroupPay.repositories;


import com.example.GroupPay.model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TransactionRespository extends JpaRepository<Transaction, UUID> {

    @Query(value = "SELECT count(*) from bank_of_apis.transactions WHERE order_reference_id = :orderReferenceId AND status = 'APPROVED';", nativeQuery = true)
    long checkOrderComplete(@Param("orderReferenceId") String orderReferenceId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE bank_of_apis.transactions SET status = 'COMPLETED' where order_reference_id = :orderId AND status = 'APPROVED';", nativeQuery = true)
    void markTransactionCompleted(@Param("orderId") UUID orderId);
}
