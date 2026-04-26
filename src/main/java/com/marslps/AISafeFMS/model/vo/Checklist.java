package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Embeddable;

import java.util.List;

@Embeddable
public record Checklist(List<NumberedItem> checklist) {
    public Checklist(List<NumberedItem> checklist) {
        this.checklist = checklist;
    }
}
