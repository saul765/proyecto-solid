package org.kodigo.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingCartItem {

    private Product product;

    private Integer quantity;
}
