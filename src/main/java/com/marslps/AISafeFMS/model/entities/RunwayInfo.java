package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import com.marslps.AISafeFMS.model.vo.Orientation;
import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Embeddable
public record RunwayInfo(@NotBlank String name, @Positive double length, @Embedded Orientation orientation) {
    public RunwayInfo(@NotBlank String name, @Positive double length, Orientation orientation) {
        this.name = name;
        this.length = length;
        this.orientation = orientation;
    }
    public RunwayInfo() {
        this("something", 0.0, null);
    }
}
