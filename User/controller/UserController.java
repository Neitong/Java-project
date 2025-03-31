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
            System.out.println("\n===== User Dashboard =====");
            System.out.println("1. Browse Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Update Cart");
            System.out.println("5. Checkout");
            System.out.println("6. View Order History");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
}