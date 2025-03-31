package User.service;

import java.util.Scanner;

import User.repository.CartRepository;

public class CartService {
    private final CartRepository cartRepository;

    public CartService() {
        this.cartRepository = new CartRepository();
    }

    public void addProductToCart(Scanner scanner, int userId) {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        cartRepository.addToCart(userId, productId, quantity);
        System.out.println("Product added to cart successfully.");
    }

    public void viewCart(int userId) {
        System.out.println("\n===== Your Cart =====");
        cartRepository.getCartItems(userId).forEach(System.out::println);
    }

    public void updateCart(Scanner scanner, int userId) {
        System.out.print("Enter Product ID to update: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new Quantity: ");
        int quantity = scanner.nextInt();

        cartRepository.updateCartItem(userId, productId, quantity);
        System.out.println("Cart updated successfully.");
    }
}