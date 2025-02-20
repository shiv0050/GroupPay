package com.example.GroupPayMerchant.models.requests;

import com.example.GroupPayMerchant.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingRequest {
    private UUID id;
    private Integer numberOfContributors;
    private double amount;
    private UUID initiatorId;
    private int productId;
    private Status status;
    private LocalDateTime expiry;
}
