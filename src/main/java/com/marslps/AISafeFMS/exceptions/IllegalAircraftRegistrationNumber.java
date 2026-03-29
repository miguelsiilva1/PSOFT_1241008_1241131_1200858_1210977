package com.marslps.AISafeFMS.exceptions;

public class IllegalAircraftRegistrationNumber extends RuntimeException {
    public IllegalAircraftRegistrationNumber(String message) {
        super(message);
    }
}
