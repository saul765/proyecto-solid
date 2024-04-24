package org.kodigo.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Invoice {

    private Integer id;

    private List<Product> products;

    private Double subTotal;

    private List<Double> taxes;

    private Double shippingCost;

    private Double total;

}
