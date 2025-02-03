package User;

import java.util.*;

public class Order {
    // Unique order ID
    private static int orderCounter = 1; 
    private int orderId;

    // List of products and their quantities
    private Map<Product, Integer> orderItems;

    // Order status (Pending, Shipped, Delivered, etc.)
    private String status;

    // Order creation date
    private Date orderDate;

    // Total price of the order
    private double totalPrice;

    // Constructor to create a new order
    public Order() {
        this.orderId = orderCounter++;
        this.orderItems = new HashMap<>();
        this.status = "Pending"; // Default status
        this.orderDate = new Date();
        this.totalPrice = 0.0;
    }

    // Method to add a product to the order
    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        orderItems.put(product, orderItems.getOrDefault(product, 0) + quantity);
        updateTotalPrice();
    }

    // Method to remove a product from the order
    public boolean removeProduct(Product product) {
        if (orderItems.containsKey(product)) {
            orderItems.remove(product);
            updateTotalPrice();
            return true;
        }
        return false;
    }

    // Method to update the total price based on items in the order
    private void updateTotalPrice() {
        totalPrice = 0.0;
        for (Map.Entry<Product, Integer> entry : orderItems.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
    }

    // Method to get the total price
    public double getTotalPrice() {
        return totalPrice;
    }

    // Method to change the order status
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    // Method to display order details
    public void printOrderDetails() {
        System.out.println("\nOrder ID: " + orderId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Status: " + status);
        System.out.println("Products:");
        for (Map.Entry<Product, Integer> entry : orderItems.entrySet()) {
            System.out.println(entry.getKey().getName() + " - Quantity: " + entry.getValue());
        }
        System.out.println("Total Price: $" + totalPrice);
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }
}
