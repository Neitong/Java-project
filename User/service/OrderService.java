package User.service;

import java.util.Scanner;

import User.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    public void checkout(Scanner scanner, int userId) {
        System.out.println("\n===== Checkout =====");
        double total = orderRepository.calculateTotal(userId);
        System.out.printf("Total Amount: $%.2f%n", total);

        System.out.print("Confirm checkout? (y/n): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            orderRepository.createOrder(userId);
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Checkout canceled.");
        }
    }

    public void viewOrderHistory(int userId) {
        System.out.println("\n===== Order History =====");
        orderRepository.getOrderHistory(userId).forEach(System.out::println);
    }
}