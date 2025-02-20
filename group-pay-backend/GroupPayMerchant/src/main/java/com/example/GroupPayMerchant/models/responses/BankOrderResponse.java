package com.example.GroupPayMerchant.models.responses;

import lombok.Data;

@Data
public class BankOrderResponse {
    private String status;
    private String referenceId;
    private String expiry;
    private String createdAt;
}
