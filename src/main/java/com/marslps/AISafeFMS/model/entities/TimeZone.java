package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import jakarta.persistence.*;
import org.apache.axis.types.PositiveInteger;

@Entity
public class TimeZone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "time_zone_db_id")
    private Long db_id;
    @Embedded
    private NonEmptyString abbreviation;
    @Column
    private PositiveInteger utc_offset_minutes;
    @Column
    private PositiveInteger gmt_offset_minutes;
}
