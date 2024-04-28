package org.kodigo.domain;

import lombok.*;
import org.kodigo.sequence.CategorySequence;

@Setter
@Getter
@ToString
public class Category {

    @Setter(AccessLevel.NONE)
    private Integer id;

    private String name;

    private String description;

    @Builder
    public Category(String name, String description) {
        this.id = CategorySequence.getInstance().getNextId();
        this.name = name;
        this.description = description;
    }
}
