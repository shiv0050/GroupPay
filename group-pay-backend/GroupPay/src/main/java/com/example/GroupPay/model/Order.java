package com.example.GroupPay.model;

import com.example.GroupPay.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

    @Column(name = "merchant_name", nullable = false)
    private String merchantName;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "num_of_contributors", nullable = false)
    private int numOfContributors;

    @Column(name = "expiry_time")
    private Timestamp expiryTime;

    @Column(name = "reference_id")
    private UUID referenceId;
}
