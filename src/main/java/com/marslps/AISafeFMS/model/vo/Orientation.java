package com.marslps.AISafeFMS.model.vo;

import com.marslps.AISafeFMS.exceptions.EmptyStringException;
import com.marslps.AISafeFMS.exceptions.IllegalOrientationException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.*;

@Embeddable
public class Orientation {
    @Column(length = 3)
    private String orientation;

    public Orientation() {
        this.orientation = "";
    }

    public Orientation(String orientation) {
        if(orientation == null || orientation.trim().isEmpty()) {
            throw new EmptyStringException("We're sorry, but the orientation cannot be empty!");
        }
        String clean_orientation = orientation.trim();
        if(!clean_orientation.matches("^(N|S|E|W|NNE|ENE|ESE|SSE|SSW|WSW|WNW|NNW)$")) {
            throw new IllegalOrientationException("We're sorry, but that orientation is invalid in our system!");
        }
        this.orientation = clean_orientation;
    }
}
