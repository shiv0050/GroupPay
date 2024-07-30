package com.example.GroupPayMerchant.controller;

import com.example.GroupPayMerchant.models.BookingDetails;
import com.example.GroupPayMerchant.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchant-user")
public class BookingController {

    @Autowired
    private BookingService bookingService ;

    @PostMapping("/newBooking")
    public ResponseEntity<BookingDetails> createNewBooking(@RequestBody BookingDetails newBookingDetails){
    BookingDetails newBooking = bookingService.createNewBooking(newBookingDetails) ;
        return new ResponseEntity<>(newBooking,HttpStatus.CREATED) ;
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<BookingDetails> updateBookingStatus(@PathVariable Integer id , @RequestParam String status){
            BookingDetails updatedBooking = bookingService.updateStatus(id, status) ;
            return new ResponseEntity<>(updatedBooking , HttpStatus.OK) ;
    }
}
