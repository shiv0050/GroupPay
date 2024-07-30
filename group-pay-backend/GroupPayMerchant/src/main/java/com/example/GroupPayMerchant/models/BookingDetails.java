package com.example.GroupPayMerchant.models;

import com.example.GroupPayMerchant.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "booking_details")
public class BookingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Booking_id")
    private UUID id;

    @Column(name = "No_of_contributors")
    private Integer numberOfContributors;

    @Column(name = "Amount")
    private double amount;

    @Column(name = "Initiator_id")
    private UUID initiatorId;

    @Column(name = "Product_id")
    private int productId;

    @Column(name = "Completion_status")
    private Status status;

    @Column(name = "Expiry_timestamp")
    private LocalDateTime expiry;

}
