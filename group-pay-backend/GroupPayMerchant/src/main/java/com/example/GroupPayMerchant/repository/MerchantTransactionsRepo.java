package com.example.GroupPayMerchant.repository;

import com.example.GroupPayMerchant.models.MerchantTransactions;
import com.example.GroupPayMerchant.models.requests.TxnResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MerchantTransactionsRepo extends JpaRepository<MerchantTransactions , UUID> {

    MerchantTransactions findByPaymentRefId(UUID paymentRefId);

    @Query(value = "Select mt.amount, mt.created_at, mt.payment_status, usr.name, usr.email from bank_of_apis.merchant_transactions mt, bank_of_apis.merchant_user usr where mt.user_id=usr.user_id AND mt.payment_status IN ('APPROVED','COMPLETED') AND mt.booking_id= :bookingId;", nativeQuery = true)
    List<TxnResponse> getAllTransactions(@Param("bookingId") UUID bookingId);

    @Query(value = "SELECT count(*) from bank_of_apis.merchant_transactions WHERE booking_id = :bookingId AND payment_status = 'APPROVED';", nativeQuery = true)
    long checkOrderComplete(@Param("bookingId") String bookingId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE bankofapis.merchant_transactions SET payment_status = 'COMPLETED' where booking_id = :bookingId AND payment_status = 'APPROVED';", nativeQuery = true)
    void markTransactionCompleted(UUID bookingId);

}
