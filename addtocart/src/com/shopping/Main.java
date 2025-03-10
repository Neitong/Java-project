package com.shopping;

import com.shopping.model.Product;
// import com.shopping.model.History; 

public class Main {
    public static void main(String[] args) {
        System.out.println("===== Shopping Cart Test =====");
        
        // Create a few test products
        Product laptop = new Product(1, "Laptop", "MacBook Pro", 1299.99, "Electronics", 
                                    "Apple", "Silver", 12, 10);
        Product phone = new Product(2, "Smartphone", "iPhone 13", 999.99, "Electronics", 
                                   "Apple", "Black", 12, 20);
        Product headphones = new Product(3, "Headphones", "AirPods Pro", 249.99, "Audio", 
                                        "Apple", "White", 12, 15);
        
        // Create a new shopping cart
        ShoppingCart cart = new ShoppingCart();
        
        // Test adding products to cart
        System.out.println("\nTesting addToCart method:");
        cart.addToCart(laptop, 1);
        cart.addToCart(phone, 2);
        cart.displayCart();
        
        // Test updating quantity
        System.out.println("\nTesting updateQuantity method:");
        cart.updateQuantity(1, 2);
        cart.displayCart();
        
        // Test applying discount
        System.out.println("\nTesting applyDiscount method:");
        cart.applyDiscount(10.0);
        cart.displayCart();
        
        // Test removing product
        System.out.println("\nTesting removeProduct method:");
        cart.removeProduct(1);
        cart.displayCart();
        
        // Test showing history
        System.out.println("\nTesting showHistory method:");
        cart.showHistory();
        
        // Test checkout
        System.out.println("\nTesting checkout method:");
        cart.addToCart(headphones, 1);
        cart.checkout();
        
        // Verify cart is empty after checkout
        System.out.println("\nVerifying cart is empty after checkout:");
        cart.displayCart();
        
        System.out.println("\n===== Test Completed =====");
    }
}