package org.kodigo.component;

import lombok.val;
import org.kodigo.domain.ShoppingCart;

import java.util.List;
import java.util.function.Function;

public class ShoppingCartVisualizationComponent extends BaseTableVisualization {

    public ShoppingCartVisualizationComponent(TableComponent tableComponent) {
        super(tableComponent);
    }

    @Override
    protected List<String> headers() {
        return List.of("ID", "Products", "Subtotal", "SubtotalWithTaxes", "Taxes", "User ID");
    }

    @Override
    protected <T> Function<T, List<String>> rowMapper() {
        return
                item -> {
                    ShoppingCart shoppingCart = (ShoppingCart) item;
                    return List.of(
                            shoppingCart.getId().toString(),
                            shoppingCart.getProducts().toString(),
                            "$" + shoppingCart.getSubTotal().toString(),
                            "$" + shoppingCart.getSubTotalWithTaxes().toString(),
                            shoppingCart.getTaxes().toString(),
                            shoppingCart.getUserId().toString()
                    );
                };
    }

    @Override
    public <T> void showData(List<T> data) {
        val shoppingCarts = (List<ShoppingCart>) data;
        showTable(shoppingCarts);
    }
}
