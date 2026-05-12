package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public record Checklist(@ElementCollection List<NumberedItem> checklist) {
    public Checklist(List<NumberedItem> checklist) {
        this.checklist = checklist;
    }
    public Checklist() {this(new ArrayList<>());}
}
