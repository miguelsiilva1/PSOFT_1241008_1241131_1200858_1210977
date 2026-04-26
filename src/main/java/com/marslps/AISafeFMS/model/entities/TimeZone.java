package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.apache.axis.types.PositiveInteger;

@Entity
public class TimeZone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "time_zone_db_id")
    private Long db_id;
    @NotBlank
    private String abbreviation;
    @Column
    private PositiveInteger utc_offset_minutes;
    @Column
    private PositiveInteger gmt_offset_minutes;
}
