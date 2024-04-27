package org.kodigo.service;

import lombok.AllArgsConstructor;
import org.kodigo.domain.Product;
import org.kodigo.repository.IProductRepository;

import java.util.List;

@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;


    @Override
    public void createProduct() {

    }

    @Override
    public void updateProduct(Integer productId) {

    }

    @Override
    public void deleteProduct(Integer productId) {

    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategory(Integer categoryId) {
        return List.of();
    }
}
