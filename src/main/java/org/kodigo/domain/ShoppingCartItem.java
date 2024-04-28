package org.kodigo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class ShoppingCartItem {

    private Product product;

    private Integer quantity;

    @Override
    public String toString() {
        return "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
