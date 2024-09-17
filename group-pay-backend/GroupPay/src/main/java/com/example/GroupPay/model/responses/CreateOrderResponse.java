package com.example.GroupPay.model.responses;

import com.example.GroupPay.enums.OrderStatus;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class CreateOrderResponse {
    private OrderStatus status;
    private UUID referenceId;
    private Timestamp expiry;
    private Timestamp createdAt;

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setReferenceId(UUID referenceId) {
        this.referenceId = referenceId;
    }

    public void setExpiry(Timestamp expiry) {
        this.expiry = expiry;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
