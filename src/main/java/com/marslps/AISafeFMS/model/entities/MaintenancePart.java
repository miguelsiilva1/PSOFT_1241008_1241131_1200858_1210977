package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import com.marslps.AISafeFMS.model.vo.PartNumber;
import org.apache.axis.types.PositiveInteger;

public class MaintenancePart {
    private NonEmptyString name;
    private String description;
    private PositiveInteger stock_quantity;
    private PartNumber part_number;

}
