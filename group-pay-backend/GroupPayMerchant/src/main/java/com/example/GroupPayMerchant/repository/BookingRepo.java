package com.example.GroupPayMerchant.repository;

import com.example.GroupPayMerchant.entity.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<BookingDetails, Integer> {


}
