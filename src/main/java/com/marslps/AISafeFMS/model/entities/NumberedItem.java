package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import jakarta.persistence.*;
import org.apache.axis.types.PositiveInteger;

@Entity
public class NumberedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numered_item_db_id")
    private Long db_id;
    @Column
    private PositiveInteger id;
    @Embedded
    private NonEmptyString chore;

    public NumberedItem() {
        this.id = new PositiveInteger("1");
        this.chore = new NonEmptyString();
    }

    public NumberedItem(PositiveInteger id, NonEmptyString chore) {
        this.id = id;
        this.chore = chore;
    }
}