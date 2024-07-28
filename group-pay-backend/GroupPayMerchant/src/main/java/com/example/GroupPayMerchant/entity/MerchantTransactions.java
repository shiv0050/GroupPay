package com.example.GroupPayMerchant.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Merchant_Transactions")
public class MerchantTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Transaction_id")
    private Integer transactionId;

    @Column(name = "User_id")
    private Integer userId;

    @Column(name = "Booking_id")
    private Integer bookingId;

    @Column(name = "Booking_timestamp")
    private LocalDateTime bookingTimestamp;

    @Column(name = "Payment_Ref_id")
    private String paymentRefId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "Amount")
    private Double amount;
}
