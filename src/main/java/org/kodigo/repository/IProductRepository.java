package org.kodigo.repository;

import org.kodigo.domain.Product;

import java.util.List;

public interface IProductRepository {

    void createProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Integer productId);

    List<Product> getProducts();

    List<Product> getProductsByCategory(Integer categoryId);

}
