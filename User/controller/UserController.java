package User.controller;

import java.util.Scanner;

import User.service.CartService;
import User.service.OrderService;
import User.service.UserService;

public class UserController {
    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;

    public UserController() {
        this.userService = new UserService();
        this.cartService = new CartService();
        this.orderService = new OrderService();
    }

    public void displayUserMenu(Scanner scanner, String Username) {
        while (true) {
            System.out.println("\n===================================");
            System.out.println("         USER DASHBOARD");
            System.out.println("===================================");
            System.out.println("1. Browse Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Update Cart");
            System.out.println("5. Checkout");
            System.out.println("6. View Order History");
            System.out.println("7. Exit");
            System.out.println("===================================");
            System.out.print("Enter your choice (1-7): ");

            int choice = getValidatedChoice(scanner, 1, 7);

            switch (choice) {
                case 1:
                    userService.browseProducts();
                    break;
                case 2:
                    cartService.addProductToCart(scanner, Username);
                    break;
                case 3:
                    cartService.viewCart(Username);
                    break;
                case 4:
                    cartService.updateCart(scanner, Username);
                    break;
                case 5:
                    orderService.checkout(scanner, Username);
                    break;
                case 6:
                    orderService.viewOrderHistory(Username);
                    break;
                case 7:
                    System.out.println("Exiting User Dashboard...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private int getValidatedChoice(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.printf("Please enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}