package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.exceptions.IllegalAircraftRegistrationNumber;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.model.vo.AircraftRegistration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AircraftRegistrationTest {
    @Test
    void ensureAircraftRegistrationNumberCannotBeAnything() {
        assertThrows(IllegalAircraftRegistrationNumber.class, () -> new AircraftRegistration("123MDFOIWEJF/2913082"));
    }
}
