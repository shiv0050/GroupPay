package com.example.GroupPayMerchant.service;

import com.example.GroupPayMerchant.enums.Status;
import com.example.GroupPayMerchant.exceptions.BookingIDNotFoundException;
import com.example.GroupPayMerchant.exceptions.InvalidStatusException;
import com.example.GroupPayMerchant.models.BookingDetails;
import com.example.GroupPayMerchant.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService{



    @Autowired
    BookingRepo bookingRepository ;

    @Override
    public BookingDetails createNewBooking(BookingDetails bookingDetails) {
        bookingDetails.setStatus(Status.IN_PROGRESS);  //Set default status as IN_PROGRESS
        bookingRepository.save(bookingDetails) ;

        return bookingDetails;
    }

    @Override
    public List<BookingDetails> getAllBookings() {
        return bookingRepository.findAll() ;
    }

    @Override
    public BookingDetails getBookingById(UUID id) {
        return bookingRepository.getReferenceById(id) ;
    }

    @Override
    public void deleteBooking(UUID id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public BookingDetails updateStatus(UUID bookingId , Status newStatus) {

        BookingDetails bookingDetails = bookingRepository.getReferenceById(bookingId);
        if (bookingDetails==null)
                throw new BookingIDNotFoundException("Booking not found with ID:" + bookingId);

        try{
            Status  status = newStatus ;
            bookingDetails.setStatus(status);
            return bookingRepository.save(bookingDetails) ;
        } catch (IllegalArgumentException e){
            throw  new InvalidStatusException("Invalid status value: " + newStatus) ;
        }

    }
}
