package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import org.apache.axis.types.PositiveInteger;

public class TimeZone {
    private NonEmptyString abbreviation;
    private PositiveInteger utc_offset_minutes;
    private PositiveInteger gmt_offset_minutes;
}
