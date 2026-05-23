package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.exceptions.IllegalContactException;
import com.marslps.AISafeFMS.model.enums.ContactInfoType;
import com.marslps.AISafeFMS.model.vo.ContactInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactInfoTest {

    @Test
    void testValidEmail() {
        ContactInfo email = new ContactInfo(ContactInfoType.EMAIL, "teste@aeroporto.pt", "Geral");
        assertEquals("teste@aeroporto.pt", email.contact());
        assertEquals("Geral", email.description());
    }

    @Test
    void testInvalidEmail() {
        IllegalContactException exception = assertThrows(IllegalContactException.class, () -> {
            new ContactInfo(ContactInfoType.EMAIL, "email_invalido.pt", "Geral");
        });
        assertEquals("We're sorry, but that email format is invalid in our system.", exception.getMessage());
    }

    @Test
    void testValidPhoneNumber() {
        ContactInfo phone = new ContactInfo(ContactInfoType.PHONE_NUMBER, "+351 912345678", "Geral");
        assertEquals("+351 912345678", phone.contact());
    }

    @Test
    void testInvalidPhoneNumber() {
        IllegalContactException exception = assertThrows(IllegalContactException.class, () -> {
            new ContactInfo(ContactInfoType.PHONE_NUMBER, "912345678", "Geral");
        });
        assertEquals("We're sorry, but that phone number format is invalid in our system.", exception.getMessage());
    }

    @Test
    void testValidWebsite() {
        ContactInfo website = new ContactInfo(ContactInfoType.WEBSITE, "https://www.aeroporto.pt", "Site Oficial");
        assertEquals("https://www.aeroporto.pt", website.contact());
    }

    @Test
    void testInvalidWebsite() {
        IllegalContactException exception = assertThrows(IllegalContactException.class, () -> {
            new ContactInfo(ContactInfoType.WEBSITE, "htp://site errado", "Site Oficial");
        });
        assertEquals("We're sorry, but that website format is invalid in our system.", exception.getMessage());
    }

    @Test
    void testValidMailingAddress() {
        ContactInfo address = new ContactInfo(ContactInfoType.MAILING_ADDRESS, "Rua do Aeroporto, 123, Porto", "Sede");
        assertEquals("Rua do Aeroporto, 123, Porto", address.contact());
    }

    @Test
    void testInvalidMailingAddress() {
        IllegalContactException exception = assertThrows(IllegalContactException.class, () -> {
            new ContactInfo(ContactInfoType.MAILING_ADDRESS, "Rua", "Sede");
        });
        assertEquals("We're sorry, but that mailing address format is invalid in our system.", exception.getMessage());
    }
}