package com.shopping;

import com.shopping.model.Product;
import com.shopping.model.CartItem;
import com.shopping.service.CartHistory;
import com.shopping.service.DiscountManager;
import com.shopping.util.CartDisplay;
import com.shopping.repository.ProductRepository;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Integer, CartItem> cartItems;
    private CartHistory history;
    private DiscountManager discountManager;
    private ProductRepository productRepository;

    public ShoppingCart() {
        this.cartItems = new HashMap<>();
        this.history = new CartHistory();
        this.discountManager = new DiscountManager();
        this.productRepository = new ProductRepository();
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

        try {
            // Get latest stock from database
            Product freshProduct = productRepository.getProductById(product.getId());
            if (freshProduct != null && freshProduct.getStockQuantity() < quantity) {
                System.out.println("Error: Not enough stock available. Current stock: " +
                        freshProduct.getStockQuantity());
                return false;
            }
        } catch (SQLException e) {
            // If database check fails, fall back to local product
            if (product.getStockQuantity() < quantity) {
                System.out.println("Error: Not enough stock available. Current stock: " +
                        product.getStockQuantity());
                return false;
            }
        }
        return true;
    }

    private void updateExistingItem(CartItem existingItem, Product product, int quantity) {
        int newQuantity = existingItem.getQuantity() + quantity;
        try {
            // Get latest stock from database
            Product freshProduct = productRepository.getProductById(product.getId());
            if (freshProduct != null && newQuantity > freshProduct.getStockQuantity()) {
                System.out.println("Error: Cannot add more items than available in stock.");
                return;
            }
        } catch (SQLException e) {
            // If database check fails, fall back to local product
            if (newQuantity > product.getStockQuantity()) {
                System.out.println("Error: Cannot add more items than available in stock.");
                return;
            }
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

        try {
            // Check latest stock in database
            Product freshProduct = productRepository.getProductById(productId);
            if (freshProduct != null && newQuantity > freshProduct.getStockQuantity()) {
                System.out.println("Error: Requested quantity exceeds available stock.");
                return;
            }
            
            item.setQuantity(newQuantity);
            history.addEvent(String.format("Updated quantity of %s to %d",
                    item.getProduct().getName(), newQuantity));
            System.out.printf("Updated quantity of %s to %d%n",
                    item.getProduct().getName(), newQuantity);
        } catch (SQLException e) {
            // Fall back to local product check if database check fails
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
    }

    public void applyDiscount(double percentOff) {
        if (discountManager.applyDiscount(percentOff)) {
            System.out.printf("Applied %.1f%% discount to cart.%n", percentOff);
            history.addEvent(String.format("Applied %.1f%% discount", percentOff));
        } else {
            System.out.println("Invalid discount percentage.");
        }
    }

    public void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
            return;
        }

        CartDisplay.printCheckoutSummary(cartItems, discountManager);

        try {
            // Update stock in database
            for (CartItem item : cartItems.values()) {
                Product product = item.getProduct();
                int newStock = product.getStockQuantity() - item.getQuantity();
                
                // Update database stock
                productRepository.updateProductStock(product.getId(), newStock);
                
                // Update local object
                product.decreaseStock(item.getQuantity());
                
                // Log to history table if it exists
                try {
                    productRepository.addProductHistoryEntry(product.getId(), 
                            "Purchase: " + item.getQuantity() + " units");
                } catch (SQLException e) {
                    // If history table doesn't exist, just ignore this error
                    System.out.println("Note: Transaction history not recorded in database.");
                }
            }

            history.addEvent(String.format("Checkout completed - Total: $%.2f",
                    calculateTotal()));

            System.out.println("\nCheckout successful! Thank you for your purchase.");
            cartItems.clear();
            discountManager.clearDiscount();
        } catch (SQLException e) {
            System.err.println("Database error during checkout: " + e.getMessage());
            throw new RuntimeException("Checkout failed due to database error", e);
        }
    }

    public void displayCart() {
        CartDisplay.printCartContents(cartItems, discountManager);
    }

    public void showHistory() {
        history.display();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    private double calculateTotal() {
        double subtotal = cartItems.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
        return subtotal - discountManager.calculateDiscount(subtotal);
    }
}