package org.kodigo;

import org.kodigo.repository.IProductRepository;
import org.kodigo.repository.ProductRepositoryImpl;

public class Main {
    public static void main(String[] args) {

        // Initialize the repositories
        IProductRepository productRepository = new ProductRepositoryImpl();


    }
}