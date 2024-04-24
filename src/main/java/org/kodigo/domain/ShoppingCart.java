package org.kodigo.domain;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ShoppingCart {

    private Integer id;

    private ArrayList<ShoppingCartItem> products;

    private Double subTotal;

    private Double subTotalWithTaxes;

    private List<Tax> taxes;

    private String userId;


}
