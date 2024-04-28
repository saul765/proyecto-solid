package org.kodigo.domain;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
public class Invoice {

    @Setter(AccessLevel.NONE)
    private Integer id;

    private List<Product> products;

    private Double subTotal;

    private List<Double> taxes;

    private Double shippingCost;

    private Double total;

    @Builder
    public Invoice(List<Product> products, Double subTotal, List<Double> taxes, Double shippingCost, Double total) {
        this.products = products;
        this.subTotal = subTotal;
        this.taxes = taxes;
        this.shippingCost = shippingCost;
        this.total = total;
    }
}
