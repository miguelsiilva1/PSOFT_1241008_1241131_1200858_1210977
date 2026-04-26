package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.apache.axis.types.PositiveInteger;

@Embeddable
public record NumberedItem(PositiveInteger id, @NotBlank String task) {
    public NumberedItem(PositiveInteger id, @NotBlank String task) {
        this.id = id;
        this.task = task;
    }

    public NumberedItem() {
        this(new PositiveInteger("1"), "something");
    }
}