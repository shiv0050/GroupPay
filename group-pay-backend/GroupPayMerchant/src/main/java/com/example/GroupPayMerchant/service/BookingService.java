package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.enums.Status;
import com.example.GroupPayMerchant.models.BookingDetails;

import java.util.List;
import java.util.UUID;

public interface BookingService {

   BookingDetails createNewBooking(BookingDetails bookingDetails) ;

   List<BookingDetails> getAllBookings() ;

   BookingDetails getBookingById(UUID id) ;


   void deleteBooking(UUID id) ;

    BookingDetails updateStatus(UUID bookingId , Status newStatus) ;

}
