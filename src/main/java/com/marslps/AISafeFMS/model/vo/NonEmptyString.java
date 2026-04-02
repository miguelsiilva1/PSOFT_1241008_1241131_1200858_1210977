package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class NonEmptyString {
    @Column
    private String string;

    public NonEmptyString() {
        this.string = "";
    }

    public NonEmptyString(String string) {
        if(string == null || string.trim().isEmpty()) {
            throw new EmptyStringException("We're sorry, but this data cannot be empty!");
        }
        this.string = string;
    }
}
