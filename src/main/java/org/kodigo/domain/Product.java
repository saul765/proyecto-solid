package org.kodigo.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    private Integer id;

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    private Category category;
}
