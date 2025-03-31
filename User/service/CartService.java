package User.service;

import java.util.Scanner;

import User.repository.CartRepository;

public class CartService {
    private final CartRepository cartRepository;

    public CartService() {
        this.cartRepository = new CartRepository();
    }

    public void addProductToCart(Scanner scanner, String Username) {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        cartRepository.addToCart(Username, productId, quantity);
        System.out.println("Product added to cart successfully.");
    }

    public void viewCart(String username) {
        System.out.println("\n===== Your Cart =====");
        cartRepository.getCartItems(username).forEach(System.out::println);
    }

    public void updateCart(Scanner scanner, String Username) {
        System.out.print("Enter Product ID to update: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new Quantity: ");
        int quantity = scanner.nextInt();

        cartRepository.updateCartItem(Username, productId, quantity);
        System.out.println("Cart updated successfully.");
    }
}