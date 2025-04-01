package User.service;

import java.util.List;
import java.util.Scanner;

import User.model.CartItem;
import User.repository.CartRepository;

public class CartService {
    private final CartRepository cartRepository;

    public CartService() {
        this.cartRepository = new CartRepository();
    }

    public void addProductToCart(Scanner scanner, String Username) {
        System.out.print("Enter Product ID: ");
        int productId = getValidatedInt(scanner);
        System.out.print("Enter Quantity: ");
        int quantity = getValidatedInt(scanner);

        cartRepository.addToCart(Username, productId, quantity);
        System.out.println("Product added to cart successfully.");
    }

    public void viewCart(String username) {
    List<CartItem> cartItems = cartRepository.getCartItems(username);

    if (cartItems.isEmpty()) {
        System.out.println("\n===============================================================");
        System.out.println("                           YOUR CART");
        System.out.println("===============================================================");
        System.out.println("                      Your cart is empty.");
        System.out.println("===============================================================");
        return;
    }

    System.out.println("\n===============================================================");
    System.out.println("                            YOUR CART");
    System.out.println("===============================================================");
    System.out.printf("%-25s %-10s %-10s %-10s%n", "Product Name", "Price", "Quantity", "Subtotal");
    System.out.println("===============================================================");

    double total = 0.0;
    for (CartItem item : cartItems) {
        String productName = item.getProduct().getName();
        double price = item.getProduct().getPrice();
        int quantity = item.getQuantity();
        double subtotal = price * quantity;

        total += subtotal;

        System.out.printf("%-25s $%-9.2f %-10d $%-9.2f%n", productName, price, quantity, subtotal);
    }

    System.out.println("===============================================================");
    System.out.printf("                    Total: $%.2f%n", total);
    System.out.println("===============================================================");
}

    public void updateCart(Scanner scanner, String Username) {
        System.out.print("Enter Product ID to update: ");
        int productId = getValidatedInt(scanner);
        System.out.print("Enter new Quantity: ");
        int quantity = getValidatedInt(scanner);

        cartRepository.updateCartItem(Username, productId, quantity);
        System.out.println("Cart updated successfully.");
    }

    private int getValidatedInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}