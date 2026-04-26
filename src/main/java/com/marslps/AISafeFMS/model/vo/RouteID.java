package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record RouteID(@NotBlank String id) {
    public RouteID(@NotBlank String id) {
        this.id = id;
    }
}
