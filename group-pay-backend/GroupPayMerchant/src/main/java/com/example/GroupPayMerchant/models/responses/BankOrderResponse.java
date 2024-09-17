package com.example.GroupPayMerchant.models.responses;

public class BankOrderResponse {
    private String status;
    private String referenceId;
    private String expiry;
    private String createdAt;

    public String getStatus() {
        return status;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public String getExpiry() {
        return expiry;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
