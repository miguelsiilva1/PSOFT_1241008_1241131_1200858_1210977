package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;

public class NonEmptyString {
    private String string;

    public NonEmptyString(String string) {
        if(string == null || string.trim().isEmpty()) {
            throw new EmptyStringException("We're sorry, but this data cannot be empty!");
        }
        this.string = string;
    }
}
