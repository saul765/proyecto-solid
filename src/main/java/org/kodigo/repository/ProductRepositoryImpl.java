package org.kodigo.repository;

import org.kodigo.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements IProductRepository {

    private final ArrayList<Product> products = new ArrayList<>(mockProducts());

    @Override
    public void createProduct(Product product) {
        products.add(product);
    }

    @Override
    public void updateProduct(Product product) {
        products.add(product);
    }

    @Override
    public void deleteProduct(Integer productId) {
        products.removeIf(p -> p.getId().equals(productId));
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public List<Product> getProductsByCategory(Integer categoryId) {
        return products.stream()
                .filter(p -> p.getCategory().getId().equals(categoryId))
                .toList();
    }

    private List<Product> mockProducts() {
        return List.of(
                Product.builder().name("Product 1")
                        .stock(10)
                        .price(100.0)
                        .description("Description")
                        .build(),

                Product.builder().name("Product 2")
                        .stock(20)
                        .price(200.0)
                        .description("Description 2")
                        .build(),
                Product.builder().name("Product 3")
                        .stock(5)
                        .price(50.0)
                        .description("Description 3")
                        .build(),
                Product.builder().name("Product 4")
                        .stock(3)
                        .price(20.0)
                        .description("Description 4")
                        .build()
        );


    }
}
