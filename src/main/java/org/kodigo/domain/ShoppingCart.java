package org.kodigo.domain;

import lombok.*;
import org.kodigo.sequence.ShoppingCartSequence;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ShoppingCart {

    @Setter(value = AccessLevel.NONE)
    private final Integer id;

    private ArrayList<ShoppingCartItem> products;

    private Double subTotal;

    private Double subTotalWithTaxes;

    private List<Tax> taxes;

    private Integer userId;

    @Builder
    private ShoppingCart(Integer userId) {
        this.id = ShoppingCartSequence.getInstance().getNextId();
        this.userId = userId;
        this.products = new ArrayList<>(List.of());
        this.subTotalWithTaxes = 0.0;
        this.subTotal = 0.0;
        this.taxes = List.of();
    }
}
