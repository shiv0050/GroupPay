package com.example.GroupPay.exceptions;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException(String e) {
        super(InvalidCredentialException.generateMessage(e));
    }

    private static String generateMessage(String exception) {
        return  "Error: " + exception;
    }
}
