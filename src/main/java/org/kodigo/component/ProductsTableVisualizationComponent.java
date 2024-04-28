package org.kodigo.component;

import lombok.val;
import org.kodigo.domain.Product;

import java.util.List;
import java.util.function.Function;

public class ProductsTableVisualizationComponent extends BaseTableVisualization {


    public ProductsTableVisualizationComponent(TableComponent tableComponent) {
        super(tableComponent);
    }


    @Override
    protected List<String> headers() {
        return List.of("ID", "Name", "Price", "Stock", "Description", "Category Name");
    }


    @Override
    protected <T> Function<T, List<String>> rowMapper() {
        return item -> {
            Product product = (Product) item;
            return List.of(
                    product.getId().toString(),
                    product.getName(),
                    product.getPrice().toString(),
                    product.getStock().toString(),
                    product.getDescription(),
                    product.getCategory().getName()
            );
        };
    }

    @Override
    public <T> void showData(List<T> data) {
        val products = (List<Product>) data;
        showTable(products);
    }

}
