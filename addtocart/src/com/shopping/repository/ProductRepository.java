package com.shopping.repository;

import com.shopping.model.Product;
import com.shopping.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM StockProduct";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Product product = mapResultSetToProduct(rs);
                products.add(product);
            }
        }
        
        return products;
    }
    
    public Product getProductById(int id) throws SQLException {
        String query = "SELECT * FROM StockProduct WHERE Product_ID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToProduct(rs);
                }
            }
        }
        
        return null;
    }
    
    public void updateProductStock(int productId, int newQuantity) throws SQLException {
        String query = "UPDATE StockProduct SET Product_Quantity = ? WHERE Product_ID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, productId);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Failed to update product stock, no rows affected.");
            }
        }
    }
    
    public void addProductHistoryEntry(int productId, String action) throws SQLException {
        // Only use this if you have an adminhistory table
        String query = "INSERT INTO adminhistory (product_id, action_type, action_date) VALUES (?, ?, NOW())";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, productId);
            pstmt.setString(2, action);
            
            pstmt.executeUpdate();
        }
    }
    
    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        return new Product(
            rs.getInt("Product_ID"),
            rs.getString("Product_Name"),
            rs.getString("Product_Model"),
            rs.getDouble("Product_Price"),
            rs.getString("Product_Type"),
            rs.getString("Product_Brand"),
            rs.getString("Product_Color"),
            rs.getInt("Product_Warranty"),
            rs.getInt("Product_Quantity")
        );
    }
}