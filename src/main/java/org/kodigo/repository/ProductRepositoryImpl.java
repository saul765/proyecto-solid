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
    public void updateProduct(Product product) {
        Product searchProduct = products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        boolean isRemoved = products.remove(searchProduct);

        if (!isRemoved) {
            throw new IllegalArgumentException("Product not found");
        } else {
            products.add(product);
        }
    }

    @Override
    public void deleteProduct(Integer productId) {
        boolean isRemoved = products.removeIf(p -> p.getId().equals(productId));

        if (!isRemoved) {
            throw new IllegalArgumentException("Product not found");
        }
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
