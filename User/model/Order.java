package User.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private int orderId;
    private double totalAmount;
    private Timestamp orderDate;
    private Map<Product, Integer> orderItems;

    public Order(int orderId, double totalAmount, Timestamp orderDate) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.orderItems = new HashMap<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void addProduct(Product product, int quantity) {
        orderItems.put(product, quantity);
    }

    public Map<Product, Integer> getOrderItems() {
        return orderItems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Order #%d - Date: %s - Total: $%.2f\n", orderId, orderDate, totalAmount));
        sb.append("Items:\n");
        for (Map.Entry<Product, Integer> entry : orderItems.entrySet()) {
            sb.append(String.format("  %s (x%d)\n", entry.getKey().getName(), entry.getValue()));
        }
        return sb.toString();
    }
}