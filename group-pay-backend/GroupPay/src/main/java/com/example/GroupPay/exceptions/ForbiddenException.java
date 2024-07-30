package com.example.GroupPay.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String e) {
        super(ForbiddenException.generateMessage(e));
    }

    private static String generateMessage(String exception) {
        return  "Error: " + exception;
    }
}

