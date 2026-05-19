package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.NumberedItem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// deve ser o seu próprio agregado

@Entity
public class Checklist {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection(fetch = FetchType.EAGER)
    List<NumberedItem> checklist;

    public Checklist(List<NumberedItem> checklist) {
        this.checklist = checklist;
    }
    public Checklist() {this(new ArrayList<>());}
}
