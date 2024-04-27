package org.kodigo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kodigo.sequence.ShoppingCartSequence;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
        this.products = new ArrayList<>();
        this.taxes = List.of();
    }
}
