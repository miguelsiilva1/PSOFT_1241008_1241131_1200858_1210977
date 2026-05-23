package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.model.vo.AirportTerminal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportTerminalTest {

    @Test
    void testValidTerminal() {
        AirportTerminal terminal = new AirportTerminal("T1");
        assertEquals("T1", terminal.terminal());
    }
}