package User.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import User.model.Product;
import User.utils.DatabaseConnection;

public class UserRepository {
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM Products";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int productId = resultSet.getInt("Product_ID");
                String name = resultSet.getString("Product_Name");
                double price = resultSet.getDouble("Product_Price");
                String description = ""; // You can add more columns if needed
                String category = resultSet.getString("Product_Type");
                
                String imageUrl = ""; // Add image if you have it in database

                products.add(new Product(productId, name, price, description, category, imageUrl));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products: " + e.getMessage());
        }

        return products;
    }
}