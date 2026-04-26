package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import com.marslps.AISafeFMS.exceptions.IllegalOrientationException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

import java.util.*;

@Embeddable
public record Orientation(@NotBlank String orientation) {
    public Orientation(@NotBlank String orientation) {
        String clean_orientation = orientation.trim();
        if(!clean_orientation.matches("^(N|S|E|W|NNE|ENE|ESE|SSE|SSW|WSW|WNW|NNW)$")) {
            throw new IllegalOrientationException("We're sorry, but that orientation is invalid in our system!");
        }
        this.orientation = clean_orientation;
    }
}
