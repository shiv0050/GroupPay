package com.example.GroupPay.model;

import com.example.GroupPay.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", nullable = false)
    @UuidGenerator
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "merchant_id", nullable = false)
    private String merchantId;

    @Column(name = "merchant_name", nullable = false)
    private String merchantName;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'IN_PROGRESS'")
    private OrderStatus status;

    @Column(name = "num_of_contributors", nullable = false)
    private int numberOfContributors;

    @Column(name = "expiry_time")
    private Timestamp expiryTime;

    @Column(name = "reference_id", unique = true)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID referenceId;

    public Order() {
    }

    public Order(UUID id, Timestamp createdAt, String merchantId, String merchantName, Double amount, OrderStatus status, int numOfContributors, Timestamp expiryTime, UUID referenceId) {
        this.id = id;
        this.createdAt = createdAt;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.amount = amount;
        this.status = status;
        this.numberOfContributors = numOfContributors;
        this.expiryTime = expiryTime;
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

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getNumberOfContributors() {
        return numberOfContributors;
    }

    public void setNumberOfContributors(int numberOfContributors) {
        this.numberOfContributors = numberOfContributors;
    }

    public Timestamp getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Timestamp expiryTime) {
        this.expiryTime = expiryTime;
    }

    public UUID getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(UUID referenceId) {
        this.referenceId = referenceId;
    }
}
