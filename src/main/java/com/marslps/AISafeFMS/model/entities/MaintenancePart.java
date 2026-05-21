package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.PartNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@Entity
public class MaintenancePart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "maintenance_part_db_id")
    private Long db_id;
    @Column @NotBlank
    private String name;
    @Column
    private String description;
    @Column @Positive
    private int stock_quantity;
    @Embedded
    private PartNumber part_number;

    protected MaintenancePart() {}

    public MaintenancePart(@NotBlank String name,
                           String description,
                           @Positive int stock_quantity,
                           PartNumber part_number) {
        this.name = name;
        this.description = description;
        this.stock_quantity = stock_quantity;
        this.part_number = part_number;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MaintenancePart part)) {return false;}
        return Objects.equals(this.part_number, part.part_number);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.part_number);
    }
}
