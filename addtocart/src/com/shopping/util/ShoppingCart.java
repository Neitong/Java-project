package com.shopping.util;

import com.shopping.model.Product;
import com.shopping.model.CartItem;
import com.shopping.service.CartHistory;
import com.shopping.service.DiscountManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner; 

public class ShoppingCart {
    private Map<Integer, CartItem> cartItems;
    private CartHistory history;
    private DiscountManager discountManager;

    public ShoppingCart() {
        this.cartItems = new HashMap<>();
        this.history = new CartHistory();
        this.discountManager = new DiscountManager();
    }

    public boolean isEmpty() {
        // Assuming you have a list or collection to store cart items
        return cartItems == null || cartItems.isEmpty();
    }

    public void addToCart(Product product, int quantity) {
        if (!validateAddToCart(product, quantity)) {
            return;
        }

        CartItem existingItem = cartItems.get(product.getId());
        if (existingItem != null) {
            updateExistingItem(existingItem, product, quantity);
        } else {
            cartItems.put(product.getId(), new CartItem(product, quantity));
        }

        history.addEvent(String.format("Added %dx %s to cart", quantity, product.getName()));
        System.out.printf("Added %dx %s to cart.%n", quantity, product.getName());
    }

    private boolean validateAddToCart(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be at least 1.");
            return false;
        }

        if (product.getStockQuantity() < quantity) {
            System.out.println("Error: Not enough stock available. Current stock: " +
                    product.getStockQuantity());
            return false;
        }
        return true;
    }

    private void updateExistingItem(CartItem existingItem, Product product, int quantity) {
        int newQuantity = existingItem.getQuantity() + quantity;
        if (newQuantity > product.getStockQuantity()) {
            System.out.println("Error: Cannot add more items than available in stock.");
            return;
        }
        existingItem.setQuantity(newQuantity);
    }

    public void removeProduct(int productId) {
        CartItem removed = cartItems.remove(productId);
        if (removed != null) {
            history.addEvent("Removed " + removed.getProduct().getName() + " from cart");
            System.out.println("Removed " + removed.getProduct().getName() + " from cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    public void updateQuantity(int productId, int newQuantity) {
        CartItem item = cartItems.get(productId);
        if (item == null) {
            System.out.println("Product not found in cart.");
            return;
        }

        if (newQuantity <= 0) {
            removeProduct(productId);
            return;
        }

        if (newQuantity > item.getProduct().getStockQuantity()) {
            System.out.println("Error: Requested quantity exceeds available stock.");
            return;
        }

        item.setQuantity(newQuantity);
        history.addEvent(String.format("Updated quantity of %s to %d",
                item.getProduct().getName(), newQuantity));
        System.out.printf("Updated quantity of %s to %d%n",
                item.getProduct().getName(), newQuantity);
    }

    public void applyDiscount(double percentOff) {
        if (discountManager.applyDiscount(percentOff)) {
            System.out.printf("Applied %.1f%% discount to cart.%n", percentOff);
            history.addEvent(String.format("Applied %.1f%% discount", percentOff));
        } else {
            System.out.println("Invalid discount percentage.");
        }
    }

    public void checkout(Scanner scanner) {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
            return;
        }

        System.out.println("\n====== CHECKOUT PROCESS ======");
        
        // 1. Display order summary
        CartDisplay.printCheckoutSummary(cartItems, discountManager);
        
        // 2. Check inventory availability one last time
        boolean inventoryAvailable = validateInventory();
        if (!inventoryAvailable) {
            System.out.println("Checkout canceled due to inventory issues.");
            return;
        }
        
        // 3. Payment processing
        boolean paymentSuccessful = processPayment(scanner);
        if (!paymentSuccessful) {
            System.out.println("Checkout canceled due to payment failure.");
            return;
        }
        
        // 4. Order confirmation
        String orderNumber = generateOrderNumber();
        System.out.println("\nOrder confirmed! Your order number is: " + orderNumber);
        
        // 5. Update inventory
        updateInventory();
        
        // 6. Record transaction in history
        double finalTotal = calculateTotal();
        history.addEvent(String.format("Checkout completed - Order #%s - Total: $%.2f",
                orderNumber, finalTotal));
        
        // 7. Clear cart
        cartItems.clear();
        discountManager.clearDiscount();
        
        System.out.println("Thank you for your purchase!");
    }

    // Simple checkout method for backward compatibility
    public void checkout() {
        checkout(new Scanner(System.in));
    }

    private boolean validateInventory() {
        boolean valid = true;
        for (CartItem item : cartItems.values()) {
            Product product = item.getProduct();
            if (item.getQuantity() > product.getStockQuantity()) {
                System.out.printf("Error: %s has insufficient stock. Available: %d, Requested: %d%n",
                        product.getName(), product.getStockQuantity(), item.getQuantity());
                valid = false;
            }
        }
        return valid;
    }

    private boolean processPayment(Scanner scanner) {
        System.out.println("\n====== PAYMENT INFORMATION ======");
        
        // Payment method selection
        System.out.println("Select payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Bank Transfer");
        
        int paymentMethod = -1;
        while (paymentMethod < 1 || paymentMethod > 3) {
            System.out.print("Enter payment method (1-3): ");
            try {
                paymentMethod = Integer.parseInt(scanner.nextLine());
                if (paymentMethod < 1 || paymentMethod > 3) {
                    System.out.println("Invalid selection. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        // Payment details collection - simplified for demo purposes
        System.out.println("\nProcessing payment...");
        
        // Simulate payment processing
        try {
            System.out.print("Processing");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            System.out.println("\nPayment approved!");
            return true;
        } catch (InterruptedException e) {
            System.out.println("\nPayment processing interrupted.");
            return false;
        }
    }

    private String generateOrderNumber() {
        // Generate a simple order number based on timestamp and random number
        long timestamp = System.currentTimeMillis();
        int random = (int) (Math.random() * 1000);
        return "ORD-" + timestamp % 10000 + "-" + random;
    }

    private void updateInventory() {
        for (CartItem item : cartItems.values()) {
            Product product = item.getProduct();
            product.decreaseStock(item.getQuantity());
            System.out.printf("Updated inventory for %s: %d remaining in stock%n",
                    product.getName(), product.getStockQuantity());
        }
    }

    public void displayCart() {
        CartDisplay.printCartContents(cartItems, discountManager);
    }

    public void showHistory() {
        history.display();
    }

    private double calculateTotal() {
        double subtotal = cartItems.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
        return subtotal - discountManager.calculateDiscount(subtotal);
    }
}