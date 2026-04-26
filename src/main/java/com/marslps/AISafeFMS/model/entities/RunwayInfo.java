package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import com.marslps.AISafeFMS.model.vo.Orientation;
import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class RunwayInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "runway_info_db_id")
    private static Long db_id;
    @NotBlank
    private static String name;
    @Positive
    private static double length;
    @Embedded
    private static Orientation orientation;
}
