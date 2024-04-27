package org.kodigo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.kodigo.sequence.ProductSequence;

@Data
public class Product {

    @Setter(AccessLevel.NONE)
    private Integer id;

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    private Category category;

    @Builder
    public Product(String name, String description, Double price, Integer stock, Category category) {
        this.id = ProductSequence.getInstance().getNextId();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }
}
