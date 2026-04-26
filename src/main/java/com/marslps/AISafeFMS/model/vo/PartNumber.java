package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import com.marslps.AISafeFMS.exceptions.IllegalPartNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record PartNumber(@NotBlank String part_number) {
    public PartNumber(@NotBlank String part_number) {
        String clean_part_number = part_number.trim();
        if(clean_part_number.matches("^[A-Z0-9]+([-][A-Z0-9]+)*$")){
            throw new IllegalPartNumber("We're sorry, but that part number format is invalid in our system!");
        }
        this.part_number = clean_part_number;
    }
}
