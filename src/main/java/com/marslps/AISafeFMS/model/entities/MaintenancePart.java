package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import com.marslps.AISafeFMS.model.vo.PartNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.apache.axis.types.PositiveInteger;

@Entity
public class MaintenancePart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "maintenance_part_db_id")
    private Long db_id;
    @Column @NotBlank
    private String name;
    @Column
    private String description;
    @Column
    private PositiveInteger stock_quantity;
    @Embedded
    private PartNumber part_number;

}
