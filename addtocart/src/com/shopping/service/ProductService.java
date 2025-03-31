package com.shopping.service;

import com.shopping.model.Product;
import com.shopping.repository.ProductRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private ProductRepository productRepository;
    
    public ProductService() {
        this.productRepository = new ProductRepository();
    }
    
    public List<Product> getAllProducts() {
        try {
            return productRepository.getAllProducts();
        } catch (SQLException e) {
            System.err.println("Error loading products: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public Product getProductById(int id) {
        try {
            return productRepository.getProductById(id);
        } catch (SQLException e) {
            System.err.println("Error loading product: " + e.getMessage());
            return null;
        }
    }
}
