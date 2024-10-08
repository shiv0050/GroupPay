package com.example.GroupPayMerchant.repository;

import com.example.GroupPayMerchant.models.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingRepo extends JpaRepository<BookingDetails, UUID> {
}
