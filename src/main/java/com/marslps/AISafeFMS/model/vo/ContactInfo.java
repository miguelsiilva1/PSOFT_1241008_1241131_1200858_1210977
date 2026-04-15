package com.marslps.AISafeFMS.model.vo;


import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import com.marslps.AISafeFMS.exceptions.IllegalPhoneNumberException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ContactInfo {
    @Column
    private String type;
    @Column
    private String contact;
    @Column
    private String description;

    public ContactInfo() {
        this.type = "";
        this.contact = "";
        this.description = "";
    }

    // é necessário atualizar o construtor
    public ContactInfo(String phone_number) {
        if(phone_number == null || phone_number.trim().isEmpty()) {
            throw new EmptyStringException("We're sorry, but the phone number cannot be empty!");
        }
        String clean_contact = phone_number.trim();
        if(!clean_contact.matches("^\\+(?:[0-9] ?){6,14}[0-9]$")) {
            throw new IllegalPhoneNumberException("We're sorry, but that phone number format is invalid in our system.");
        }
        this.contact = clean_contact;
    }
}
