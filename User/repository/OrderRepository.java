package User.repository;

import User.model.Order;
import User.model.Product;
import User.model.User;
import User.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    public double calculateTotal(String Username) {
        String query = "SELECT SUM(p.Product_Price * c.Quantity) AS Total " +
                       "FROM Cart c " +
                       "JOIN Products p ON c.ProductID = p.Product_ID " +
                       "WHERE c.Username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("Total");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total: " + e.getMessage());
        }
        return 0.0;
    }

    public void createOrder(String Username) {
        String insertOrderQuery = "INSERT INTO Orders (Username, TotalAmount, OrderDate) VALUES (?, ?, NOW())";
        String getCartItemsQuery = "SELECT ProductID, Quantity FROM Cart WHERE Username = ?";
        String insertOrderDetailsQuery = "INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES (?, ?, ?)";
        String clearCartQuery = "DELETE FROM Cart WHERE Username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement insertOrderStmt = connection.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement getCartItemsStmt = connection.prepareStatement(getCartItemsQuery);
             PreparedStatement insertOrderDetailsStmt = connection.prepareStatement(insertOrderDetailsQuery);
             PreparedStatement clearCartStmt = connection.prepareStatement(clearCartQuery)) {

            // Calculate total and create order
            double total = calculateTotal(Username);
            insertOrderStmt.setString(1, Username);
            insertOrderStmt.setDouble(2, total);
            insertOrderStmt.executeUpdate();

            // Get generated OrderID
            ResultSet generatedKeys = insertOrderStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);

                // Fetch cart items
                getCartItemsStmt.setString(1, Username);
                ResultSet cartItems = getCartItemsStmt.executeQuery();

                // Insert order details
                while (cartItems.next()) {
                    int productId = cartItems.getInt("ProductID");
                    int quantity = cartItems.getInt("Quantity");
                    insertOrderDetailsStmt.setInt(1, orderId);
                    insertOrderDetailsStmt.setInt(2, productId);
                    insertOrderDetailsStmt.setInt(3, quantity);
                    insertOrderDetailsStmt.executeUpdate();
                }

                // Clear cart
                clearCartStmt.setString(1, Username);
                clearCartStmt.executeUpdate();

                System.out.println("Order created successfully with Order ID: " + orderId);
            }
        } catch (SQLException e) {
            System.err.println("Error creating order: " + e.getMessage());
        }
    }

    public List<Order> getOrderHistory(String Username) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT o.OrderID, o.TotalAmount, o.OrderDate, od.ProductID, od.Quantity, p.Product_Name " +
                       "FROM Orders o " +
                       "JOIN OrderDetails od ON o.OrderID = od.OrderID " +
                       "JOIN Products p ON od.ProductID = p.Product_ID " +
                       "WHERE o.Username = ? " +
                       "ORDER BY o.OrderDate DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("OrderID");
                double totalAmount = resultSet.getDouble("TotalAmount");
                Timestamp orderDate = resultSet.getTimestamp("OrderDate");
                int productId = resultSet.getInt("ProductID");
                int quantity = resultSet.getInt("Quantity");
                String productName = resultSet.getString("Product_Name");

                // Create or update the order object
                Order order = orders.stream()
                        .filter(o -> o.getOrderId() == orderId)
                        .findFirst()
                        .orElseGet(() -> {
                            Order newOrder = new Order(orderId, totalAmount, orderDate);
                            orders.add(newOrder);
                            return newOrder;
                        });

                // Add product details to the order
                order.addProduct(new Product(productName, 0.0, "", "", ""), quantity);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching order history: " + e.getMessage());
        }
        return orders;
    }
}