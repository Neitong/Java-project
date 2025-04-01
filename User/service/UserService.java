package User.service;

import User.model.Product;
import User.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void browseProducts() {
        System.out.println("\n===============================================================");
        System.out.println("         PRODUCT CATALOG");
        System.out.println("===============================================================");
        System.out.printf("%-10s %-20s %-10s %-15s %n", "ID", "Name", "Price", "Category");
        System.out.println("---------------------------------------------------------------");

        for (Product product : userRepository.getAllProducts()) {
            System.out.printf("%-10d %-20s $%-9.2f %-15s %n",
                    product.getProductId(),
                    product.getName(),
                    product.getPrice(),
                    product.getCategory()
                    );
        }
        System.out.println("===============================================================");
    }
}