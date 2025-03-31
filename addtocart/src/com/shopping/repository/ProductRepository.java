//package addtocart.src.com.shopping.repository;
//
//import addtocart.src.com.shopping.entity.Product; // Fixed import for Product class
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
//    private static final String TABLE_NAME = "Products"; // Changed from "StockProduct" to match your schema
//    private static final String HISTORY_TABLE_NAME = "AdminHistory"; // Changed to match your schema
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
//    public boolean updateProductStock(int productId, int quantityChange) throws SQLException {
//        String query = "UPDATE " + TABLE_NAME + " SET Product_Quantity = Product_Quantity + ? WHERE Product_ID = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(query)) {
//
//            pstmt.setInt(1, quantityChange);
//            pstmt.setInt(2, productId);
//
//            int rowsAffected = pstmt.executeUpdate();
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Error updating product stock for Product ID: " + productId, e);
//            throw e;
//        }
//    }
//
//    public boolean addProductHistoryEntry(int productId, String actionType) throws SQLException {
//        String query = "INSERT INTO " + HISTORY_TABLE_NAME +
//                " (ProductID, ActionType, ActionTimestamp) VALUES (?, ?, NOW())";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(query)) {
//
//            pstmt.setInt(1, productId);
//            pstmt.setString(2, actionType);
//
//            int rowsAffected = pstmt.executeUpdate();
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Error adding product history entry for Product ID: " + productId, e);
//            throw e;
//        }
//    }
//
//    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
//        Product product = new Product();
//        product.setId(rs.getInt("Product_ID"));
//        product.setName(rs.getString("Product_Name"));
//        product.setModel(rs.getString("Product_Model"));
//        product.setPrice(rs.getDouble("Product_Price"));
//        product.setType(rs.getString("Product_Type"));
//        product.setBrand(rs.getString("Product_Brand"));
//        product.setColor(rs.getString("Product_Color"));
//        product.setWarranty(rs.getInt("Product_Warranty"));
//        product.setQuantity(rs.getInt("Product_Quantity"));
//        return product;
//    }
//
//    // Additional useful methods
//    public List<Product> getProductsByType(String productType) throws SQLException {
//        List<Product> products = new ArrayList<>();
//        String query = "SELECT * FROM " + TABLE_NAME + " WHERE Product_Type = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(query)) {
//
//            pstmt.setString(1, productType);
//
//            try (ResultSet rs = pstmt.executeQuery()) {
//                while (rs.next()) {
//                    products.add(mapResultSetToProduct(rs));
//                }
//            }
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Error fetching products by type: " + productType, e);
//            throw e;
//        }
//
//        return products;
//    }
//}