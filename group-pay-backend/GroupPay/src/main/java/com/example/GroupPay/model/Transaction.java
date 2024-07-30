package com.example.GroupPay.model;

import com.example.GroupPay.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "order_reference_id", nullable = false)
    private UUID orderReferenceId;

    @Column(name = "reference_id", nullable = false)
    private UUID referenceId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(name = "amount", nullable = false)
    private double amount;

    public Transaction() {
    }

    public Transaction(UUID id, Timestamp createdAt, long userId, UUID orderId, UUID txnReferenceId, TransactionStatus status, double amount) {
        this.id = id;
        this.createdAt = createdAt;
        this.userId = userId;
        this.orderReferenceId = orderId;
        this.referenceId = txnReferenceId;
        this.status = status;
        this.amount = amount;
    }

    public UUID getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(UUID referenceId) {
        this.referenceId = referenceId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UUID getOrderReferenceId() {
        return orderReferenceId;
    }

    public void setOrderReferenceId(UUID orderReferenceId) {
        this.orderReferenceId = orderReferenceId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
