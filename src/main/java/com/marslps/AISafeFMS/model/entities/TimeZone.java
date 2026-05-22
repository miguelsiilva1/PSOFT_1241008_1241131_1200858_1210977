package com.marslps.AISafeFMS.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
public class TimeZone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "time_zone_db_id")
    @JsonIgnore
    private Long db_id;
    @NotBlank
    private String abbreviation;
    @Column
    private @PositiveOrZero int utc_offset_minutes;
    @Column
    private @PositiveOrZero int gmt_offset_minutes;

    protected TimeZone() {}

    public TimeZone(@NotBlank String abbreviation,
                    @PositiveOrZero int utc_offset_minutes,
                    @PositiveOrZero int gmt_offset_minutes) {
        this.abbreviation = abbreviation;
        this.utc_offset_minutes = utc_offset_minutes;
        this.gmt_offset_minutes = gmt_offset_minutes;
    }

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
