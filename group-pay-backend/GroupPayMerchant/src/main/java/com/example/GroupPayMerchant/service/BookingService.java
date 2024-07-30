package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.models.BookingDetails;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface BookingService {

   BookingDetails createNewBooking(BookingDetails bookingDetails) ;

   List<BookingDetails> getAllBookings() ;

   BookingDetails getBookingById(Integer id) ;


   void deleteBooking(Integer id) ;

    BookingDetails updateStatus(Integer bookingId , String newStatus) ;

}
