package com.example.GroupPayMerchant.models.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class BankOrderRequest {
    private String merchantId;
    private String merchantName;
    private double amount;
    private int numberOfContributors;
    private UUID referenceId;
    private int expiry;

    public BankOrderRequest() {
    }

    public BankOrderRequest(String merchantId, String merchantName, double amount, int numberOfContributors, UUID referenceId, int expiry) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.amount = amount;
        this.numberOfContributors = numberOfContributors;
        this.referenceId = referenceId;
        this.expiry = expiry;
    }

    @Override
    public String toString() {
        return "BankOrderRequest{" +
                "merchantId='" + merchantId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", amount=" + amount +
                ", numberOfContributors=" + numberOfContributors +
                ", referenceId=" + referenceId +
                ", expiry=" + expiry +
                '}';
    }


}
