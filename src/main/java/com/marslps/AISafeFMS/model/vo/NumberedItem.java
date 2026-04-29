package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Embeddable
public record NumberedItem(@Positive int id, @NotBlank String task) {
    public NumberedItem(@Positive int id, @NotBlank String task) {
        this.id = id;
        this.task = task;
    }
    public NumberedItem() {
        this(1, "something");
    }
}