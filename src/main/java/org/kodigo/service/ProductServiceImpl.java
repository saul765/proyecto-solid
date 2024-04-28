package org.kodigo.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.kodigo.domain.Product;
import org.kodigo.repository.ICategoryRepository;
import org.kodigo.repository.IProductRepository;

import java.util.List;

@AllArgsConstructor
public class ProductServiceImpl implements IProductService, IBindMockCategories {

    private final IProductRepository productRepository;

    private final ICategoryRepository categoryRepository;


    @Override
    public void createProduct(Product product) {
        val products = productRepository.getProducts();
        products.stream().filter(pro -> pro.getId().equals(product.getId()))
                .findFirst()
                .ifPresent(pro -> {
                    throw new IllegalArgumentException("Product already exists");
                });
        productRepository.createProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        val products = productRepository.getProducts();
        Product searchProduct = products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        boolean isRemoved = products.remove(searchProduct);

        if (!isRemoved) {
            throw new IllegalArgumentException("Product not found");
        } else {
            productRepository.updateProduct(product);
        }
    }


    @Override
    public void deleteProduct(Integer productId) {
        val products = productRepository.getProducts();

        val product = products.stream().filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        productRepository.deleteProduct(product.getId());
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public List<Product> getProductsByCategory(Integer categoryId) {
        return productRepository.getProductsByCategory(categoryId);
    }

    @Override
    public void bindMockCategories() {
        // Hice esta clase ya que en la logica de mi aplicacion no puedo hacer set del id de la categoria
        // por lo tanto necesito mapear manual para ajustar las categorias de los productos existentes
        val categories = categoryRepository.getAll();

        val products = productRepository.getProducts();

        products.forEach(product -> {
            val category = categories.get((int) (Math.random() * categories.size()));
            product.setCategory(category);
        });

    }
}
