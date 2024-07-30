package com.example.GroupPayMerchant.repository;

import com.example.GroupPayMerchant.models.MerchantTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MerchantTransactionsRepo extends JpaRepository<MerchantTransactions , UUID> {

    MerchantTransactions findByPaymentRefId(UUID paymentRefId);

    @Query(value = "Select mt.*, usr.name, usr.email from bankofapis.merchant_transactions mt, bankofapis.merchant_user usr where mt.user_id,usr.user_id;", nativeQuery = true)
    List<MerchantTransactions> getAllTransactions(UUID bookingId);

    @Query(value = "SELECT count(*) from bankofapis.merchant_transactions WHERE booking_id = :bookingId AND status = 'APPROVED';", nativeQuery = true)
    long checkOrderComplete(UUID bookingId);

    @Query(value = "UPDATE bankofapis.merchant_transactions SET status = 'COMPLETED' where booking_id = :bookingId AND payment_status = 'APPROVED';", nativeQuery = true)
    void markTransactionCompleted(UUID bookingId);

}
