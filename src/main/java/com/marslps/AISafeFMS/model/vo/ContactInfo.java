package com.marslps.AISafeFMS.model.vo;


import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import com.marslps.AISafeFMS.exceptions.IllegalContactException;
import com.marslps.AISafeFMS.model.enums.ContactInfoType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record ContactInfo(ContactInfoType type, @NotBlank String contact, @NotBlank String description) {
    public ContactInfo(ContactInfoType type, @NotBlank String contact, @NotBlank String description) {
        String clean_contact = contact.trim();
        switch(type) {
            case ContactInfoType.EMAIL:
                if(!clean_contact.matches("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z]{2,}$")) {
                    throw new IllegalContactException("We're sorry, but that email format is invalid in our system.");
                }
                break;
            case ContactInfoType.PHONE_NUMBER, ContactInfoType.FAX_NUMBER:
                if(!clean_contact.matches("^\\+(?:[0-9] ?){6,14}[0-9]$")) {
                    if(type == ContactInfoType.PHONE_NUMBER) {
                        throw new IllegalContactException("We're sorry, but that phone number format is invalid in our system.");
                    }
                    if(type == ContactInfoType.FAX_NUMBER) {
                        throw new IllegalContactException("We're sorry, but that fax number format is invalid in our system.");
                    }
                }
                break;
            case ContactInfoType.WEBSITE:
                if(!clean_contact.matches("^(https?:\\/\\/)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)$")) {
                    throw new IllegalContactException("We're sorry, but that website format is invalid in our system.");
                }
                break;
            case ContactInfoType.MAILING_ADDRESS:
                if(!clean_contact.matches("^[a-zA-Z0-9\\s,.'\\-ºªÀ-ÿ]{5,150}$")) {
                    throw new IllegalContactException("We're sorry, but that mailing address format is invalid in our system.");
                }
                break;
        }

        this.type = type;
        this.contact = clean_contact;
        this.description = description.trim();
    }
    public ContactInfo() {
        this(ContactInfoType.WEBSITE, "www.something.com", "something");
    }
}

