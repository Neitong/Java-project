package com.shopping;

import com.shopping.model.Product;
import com.shopping.model.CartItem;
import com.shopping.service.CartHistory;
import com.shopping.service.DiscountManager;
import com.shopping.util.CartDisplay;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Integer, CartItem> cartItems;
    private CartHistory history;
    private DiscountManager discountManager;

    public ShoppingCart() {
        this.cartItems = new HashMap<>();
        this.history = new CartHistory();
        this.discountManager = new DiscountManager();
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

    public void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
            return;
        }

        CartDisplay.printCheckoutSummary(cartItems, discountManager);

        // Update stock
        cartItems.values().forEach(item ->
                item.getProduct().decreaseStock(item.getQuantity()));

        history.addEvent(String.format("Checkout completed - Total: $%.2f",
                calculateTotal()));

        cartItems.clear();
        discountManager.clearDiscount();
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