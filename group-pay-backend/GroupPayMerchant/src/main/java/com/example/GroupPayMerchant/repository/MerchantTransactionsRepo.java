package com.example.GroupPayMerchant.repository;

import com.example.GroupPayMerchant.models.MerchantTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MerchantTransactionsRepo extends JpaRepository<MerchantTransactions , UUID> {

    MerchantTransactions findByPaymentRefId(UUID paymentRefId);

}
