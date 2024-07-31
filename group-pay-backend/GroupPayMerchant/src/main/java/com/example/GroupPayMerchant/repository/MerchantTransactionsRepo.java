package com.example.GroupPayMerchant.repository;

import com.example.GroupPayMerchant.models.MerchantTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantTransactionsRepo extends JpaRepository<MerchantTransactions , Integer> {


}
