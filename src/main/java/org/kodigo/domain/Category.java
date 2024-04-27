package org.kodigo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.kodigo.sequence.CategorySequence;

@Data
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
