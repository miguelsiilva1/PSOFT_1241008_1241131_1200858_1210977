package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@Entity
public class TimeZone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "time_zone_db_id")
    private Long db_id;
    @NotBlank
    private String abbreviation;
    @Column
    private @Positive int utc_offset_minutes;
    @Column
    private @Positive int gmt_offset_minutes;

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TimeZone time_zone)) {return false;}
        return Objects.equals(this.abbreviation, time_zone.abbreviation);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.abbreviation);
    }
}
