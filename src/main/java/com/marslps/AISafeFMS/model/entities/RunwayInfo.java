package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import com.marslps.AISafeFMS.model.vo.Orientation;
import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import jakarta.persistence.*;

@Entity
public class RunwayInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "runway_info_db_id")
    private Long db_id;
    @Embedded
    private NonEmptyString name;
    @Embedded
    private PositiveDouble length;
    @Embedded
    private Orientation orientation;
}
