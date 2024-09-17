package com.example.GroupPay.model;

import com.example.GroupPay.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "order_reference_id", nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID orderReferenceId;

    @Column(name = "reference_id", nullable = false, unique = true)
    @JdbcTypeCode(Types.VARCHAR)
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
}
