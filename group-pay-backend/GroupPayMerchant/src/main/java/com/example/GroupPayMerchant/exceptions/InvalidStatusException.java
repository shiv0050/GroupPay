package com.example.GroupPayMerchant.exceptions;

import com.example.GroupPayMerchant.models.BookingDetails;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String s) {
        super(s);
    }
}
