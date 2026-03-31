package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import com.marslps.AISafeFMS.exceptions.IllegalPartNumber;

public class PartNumber {
    private String part_number;

    public PartNumber(String part_number) {
        if(part_number == null || part_number.trim().isEmpty()) {
            throw new EmptyStringException("We're sorry, but part number cannot be empty!");
        }
        String clean_part_number = part_number.trim();
        if(clean_part_number.matches("^[A-Z0-9]+([-][A-Z0-9]+)*$")){
            throw new IllegalPartNumber("We're sorry, but that part number format is invalid in our system!");
        }
        this.part_number = clean_part_number;
    }
}
