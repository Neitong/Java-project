package User.repository;

import User.Product;

import java.util.List;

public class UserRepository {
    public List<Product> getAllProducts() {
        // Fetch products from the database
        // Example: SELECT * FROM Products
        return List.of(
            new Product("Laptop", 1200.0, "High-performance laptop", "Electronics", "laptop.jpg"),
            new Product("Smartphone", 800.0, "Latest smartphone", "Electronics", "smartphone.jpg")
        );
    }
}