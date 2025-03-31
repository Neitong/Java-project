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
        try {
            // Fetch the latest product data from the database
            Product freshProduct = productRepository.getProductById(product.getId());
            if (freshProduct == null) {
                System.out.println("Error: Product not found in the database.");
                return;
            }

            if (quantity <= 0 || freshProduct.getStockQuantity() < quantity) {
                System.out.println("Error: Invalid quantity or insufficient stock.");
                return;
            }

            CartItem existingItem = cartItems.get(product.getId());
            if (existingItem != null) {
                updateExistingItem(existingItem, freshProduct, quantity);
            } else {
                cartItems.put(product.getId(), new CartItem(freshProduct, quantity));
            }

            history.addEvent(String.format("Added %dx %s to cart", quantity, freshProduct.getName()));
            System.out.printf("Added %dx %s to cart.%n", quantity, freshProduct.getName());
        } catch (SQLException e) {
            System.err.println("Database error while adding to cart: " + e.getMessage());
        }
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
        try {
            CartItem item = cartItems.get(productId);
            if (item == null) {
                System.out.println("Product not found in cart.");
                return;
            }

            if (newQuantity <= 0) {
                removeProduct(productId);
                return;
            }

            // Fetch the latest product data from the database
            Product freshProduct = productRepository.getProductById(productId);
            if (freshProduct == null || newQuantity > freshProduct.getStockQuantity()) {
                System.out.println("Error: Invalid quantity or insufficient stock.");
                return;
            }

            item.setQuantity(newQuantity);
            history.addEvent(String.format("Updated quantity of %s to %d", item.getProduct().getName(), newQuantity));
            System.out.printf("Updated quantity of %s to %d%n", item.getProduct().getName(), newQuantity);
        } catch (SQLException e) {
            System.err.println("Database error while updating quantity: " + e.getMessage());
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
            for (CartItem item : cartItems.values()) {
                Product product = item.getProduct();
                int newStock = product.getStockQuantity() - item.getQuantity();

                // Update stock in the database
                productRepository.updateProductStock(product.getId(), newStock);

                // Log the transaction in the history table
                productRepository.addProductHistoryEntry(product.getId(), "Purchase: " + item.getQuantity() + " units");
            }

            history.addEvent(String.format("Checkout completed - Total: $%.2f", calculateTotal()));
            System.out.println("\nCheckout successful! Thank you for your purchase.");
            cartItems.clear();
            discountManager.clearDiscount();
        } catch (SQLException e) {
            System.err.println("Database error during checkout: " + e.getMessage());
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