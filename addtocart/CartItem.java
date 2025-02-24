package com.shopping.model;

import java.time.LocalDateTime;

public class CartItem {
    private Product product;
    private int quantity;
    private LocalDateTime addedAt;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.addedAt = LocalDateTime.now();
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public LocalDateTime getAddedAt() { return addedAt; }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}