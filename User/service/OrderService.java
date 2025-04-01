package User.service;

import java.util.Scanner;

import User.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    public void checkout(Scanner scanner, String Username) {
        System.out.println("\n===================================");
        System.out.println("             CHECKOUT");
        System.out.println("===================================");
        double total = orderRepository.calculateTotal(Username);
        System.out.printf("Total Amount: $%.2f%n", total);

        System.out.print("Confirm checkout? (y/n): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            orderRepository.createOrder(Username);
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Checkout canceled.");
        }
    }

    public void viewOrderHistory(String Username) {
        System.out.println("\n===================================");
        System.out.println("         ORDER HISTORY");
        System.out.println("===================================");
        orderRepository.getOrderHistory(Username).forEach(System.out::println);
        System.out.println("===================================");
    }
}