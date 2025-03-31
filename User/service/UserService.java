package User.service;

import User.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void browseProducts() {
        System.out.println("\n===== Product Catalog =====");
        userRepository.getAllProducts().forEach(System.out::println);
    }
}