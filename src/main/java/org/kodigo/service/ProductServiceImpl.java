package org.kodigo.service;

import lombok.AllArgsConstructor;
import org.kodigo.domain.Product;
import org.kodigo.repository.IProductRepository;

import java.util.List;

@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;


    @Override
    public void createProduct(Product product) {
        productRepository.createProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }


    @Override
    public void deleteProduct(Integer productId) {
        productRepository.deleteProduct(productId);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public List<Product> getProductsByCategory(Integer categoryId) {
        return productRepository.getProductsByCategory(categoryId);
    }
}
