package com.marslps.AISafeFMS.model.vo;


import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import com.marslps.AISafeFMS.exceptions.IllegalPhoneNumberException;

public class ContactInfo {
    private String phone_number;

    public ContactInfo(String phone_number) {
        if(phone_number == null || phone_number.trim().isEmpty()) {
            throw new EmptyStringException("We're sorry, but the phone number cannot be empty!");
        }
        String clean_phone_number = phone_number.trim();
        if(!clean_phone_number.matches("^\\+(?:[0-9] ?){6,14}[0-9]$")) {
            throw new IllegalPhoneNumberException("We're sorry, but that phone number format is invalid in our system.");
        }
        this.phone_number = clean_phone_number;
    }
}
