package com.example.GroupPayMerchant.models;

import com.example.GroupPayMerchant.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "booking_details")
public class BookingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Booking_id")
    private Integer id;

    @Column(name = "No_of_contributors")
    private Integer numberOfContributors;

    @Column(name = "Amount")
    private double amount;

    @Column(name = "Initiator_id")
    private Integer initiatorId;

    @Column(name = "Product_id")
    private Integer productId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Completion_status")
    private Status status;

    @Column(name = "Expiry_timestamp")
    private LocalDateTime expiry;

}
