package com.example.GroupPayMerchant.models;


import com.example.GroupPayMerchant.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "merchant_transactions")
public class MerchantTransactions {

    @Column(name = "User_id", nullable = false)
    private UUID userId;

    @Column(name = "Booking_id", nullable = false)
    private UUID bookingId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Id
    @Column(name = "Payment_Ref_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID paymentRefId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "Amount")
    private double amount;
}
