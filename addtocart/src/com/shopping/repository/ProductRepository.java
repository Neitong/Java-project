//package com.shopping.repository;
//
//import com.shopping.model.Product;
//import com.shopping.util.DatabaseConnection;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class ProductRepository {
//    private static final Logger LOGGER = Logger.getLogger(ProductRepository.class.getName());
//    private static final String TABLE_NAME = "StockProduct";
//    private static final String HISTORY_TABLE_NAME = "adminhistory";
//
//    public List<Product> getAllProducts() throws SQLException {
//        List<Product> products = new ArrayList<>();
//        String query = "SELECT * FROM " + TABLE_NAME;
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(query)) {
//
//            while (rs.next()) {
//                Product product = mapResultSetToProduct(rs);
//                products.add(product);
//            }
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Error fetching all products", e);
//            throw e;
//        }
//
//        return products;
//    }
//
//    public Product getProductById(int id) throws SQLException {
//        String query = "SELECT * FROM " + TABLE_NAME + " WHERE Product_ID = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(query)) {
//
//            pstmt.setInt(1, id);
//
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    return mapResultSetToProduct(rs);
//                }
//            }
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Error fetching product by ID: " + id, e);
//            throw e;
//        }
//
//        return null;
//    }
//
//    public void updateProductStock(int productId, int newQuantity) throws SQLException {
//        String query = "UPDATE " + TABLE_NAME + " SET Product_Quantity = ? WHERE Product_ID = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(query)) {
//
//            pstmt.setInt(1, newQuantity);
//            pstmt.setInt(2, productId);
//
//            int rowsAffected = pstmt.executeUpdate();
//            if (rowsAffected == 0) {
//                throw new SQLException("Failed to update product stock, no rows affected.");
//            }
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Error updating product stock for Product ID: " + productId, e);
//            throw e;
//        }
//    }
//
//    public void addProductHistoryEntry(int productId, String action) throws SQLException {
//        String query = "INSERT INTO " + HISTORY_TABLE_NAME + " (product_id, action_type, action_date) VALUES (?, ?, NOW())";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(query)) {
//
//            pstmt.setInt(1, productId);
//            pstmt.setString(2, action);
//
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Error adding product history entry for Product ID: " + productId, e);
//            throw e;
//        }
//    }
//
//    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
//        return new Product(
//            rs.getInt("Product_ID"),
//            rs.getString("Product_Name"),
//            rs.getString("Product_Model"),
//            rs.getDouble("Product_Price"),
//            rs.getString("Product_Type"),
//            rs.getString("Product_Brand"),
//            rs.getString("Product_Color"),
//            rs.getInt("Product_Warranty"),
//            rs.getInt("Product_Quantity")
//        );
//    }
//}