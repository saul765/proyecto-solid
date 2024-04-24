package org.kodigo.repository;

import org.kodigo.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements IProductRepository {

    private final ArrayList<Product> products = new ArrayList<>();

    @Override
    public void createProduct(Product product) {
        products.add(product);
    }

    @Override
    public void updateProduct(Integer productId, Product product) {
        products.stream().filter(p -> p.getId().equals(productId)).forEach(p -> {
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setCategory(product.getCategory());
        });
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
}
