package User.repository;

import User.model.CartItem;
import User.model.Product;
import User.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {
     public void addToCart(String Username, int productId, int quantity) {
         String query = "INSERT INTO Cart (Username, ProductID, Quantity) VALUES (?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE Quantity = Quantity + ?";
         try (Connection connection = DatabaseConnection.getConnection();
              PreparedStatement statement = connection.prepareStatement(query)) {
             statement.setString(1, Username);
             statement.setInt(2, productId);
             statement.setInt(3, quantity);
             statement.setInt(4, quantity);
             statement.executeUpdate();
         } catch (SQLException e) {
             System.err.println("Error adding to cart: " + e.getMessage());
         }
     }

     public List<CartItem> getCartItems(String Username) {
         List<CartItem> cartItems = new ArrayList<>();
         String query = "SELECT c.ProductID, c.Quantity, p.Product_Name, p.Product_Price " +
                        "FROM Cart c " +
                        "JOIN Products p ON c.ProductID = p.Product_ID " +
                        "WHERE c.Username = ?";
         try (Connection connection = DatabaseConnection.getConnection();
              PreparedStatement statement = connection.prepareStatement(query)) {
             statement.setString(1, Username);
             ResultSet resultSet = statement.executeQuery();

             while (resultSet.next()) {
                 int productId = resultSet.getInt("ProductID");
                 int quantity = resultSet.getInt("Quantity");
                 String productName = resultSet.getString("Product_Name");
                 double productPrice = resultSet.getDouble("Product_Price");

                 cartItems.add(new CartItem(new Product(productName, productPrice, "", "", ""), quantity));
             }
         } catch (SQLException e) {
             System.err.println("Error fetching cart items: " + e.getMessage());
         }
         return cartItems;
     }

     public void updateCartItem(String Username, int productId, int quantity) {
         String query = "UPDATE Cart SET Quantity = ? WHERE Username = ? AND ProductID = ?";
         try (Connection connection = DatabaseConnection.getConnection();
              PreparedStatement statement = connection.prepareStatement(query)) {
             statement.setInt(1, quantity);
             statement.setString(2, Username);
             statement.setInt(3, productId);
             statement.executeUpdate();
         } catch (SQLException e) {
             System.err.println("Error updating cart item: " + e.getMessage());
         }
     }
 }

