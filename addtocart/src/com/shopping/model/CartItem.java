package addtocart.src.com.shopping.model;

import User.test.Product;

import java.time.LocalDateTime;

public class CartItem {
    private Product product;
    private int quantity;
    private double totalPrice;
    private LocalDateTime addedAt;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
        this.addedAt = LocalDateTime.now();
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
        this.totalPrice = product.getPrice() * quantity;
    }
    public LocalDateTime getAddedAt() { return addedAt; }

    public double getTotalPrice() {
        return totalPrice;
    }
}