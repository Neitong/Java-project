//package addtocart.src.com.shopping;
//
//import com.shopping.model.Product;
//import com.shopping.repository.ProductRepository;
//import com.shopping.util.DatabaseConnection;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//    private static List<Product> productCatalog = new ArrayList<>();
//    private static ShoppingCart cart = new ShoppingCart();
//    private static Scanner scanner = new Scanner(System.in);
//    private static ProductRepository productRepository;
//
//    public static void main(String[] args) {
//        try {
//            // Initialize database connection and repository
//            productRepository = new ProductRepository();
//
//            // Load products from database instead of hardcoding
//            loadProductsFromDatabase();
//
//            showWelcomeMessage();
//
//            boolean running = true;
//            while (running) {
//                displayMenu();
//                try {
//                    int choice = getIntInput("Enter your choice: ");
//                    running = processMenuChoice(choice);
//                } catch (Exception e) {
//                    System.out.println("An error occurred: " + e.getMessage());
//                    System.out.println("Please try again.");
//                }
//            }
//
//            System.out.println("\nThank you for shopping with us! Goodbye!");
//            scanner.close();
//
//            // Close the database connection when done
//            DatabaseConnection.closeConnection();
//
//        } catch (Exception e) {
//            System.err.println("An unexpected error occurred: " + e.getMessage());
//        }
//    }
//
//    private static void loadProductsFromDatabase() {
//        try {
//            productCatalog = productRepository.getAllProducts();
//            System.out.println("Successfully loaded " + productCatalog.size() + " products from database.");
//        } catch (SQLException e) {
//            System.err.println("Error loading products from database: " + e.getMessage());
//            // Fall back to sample products if database load fails
//            initializeProducts();
//        }
//    }
//
//    private static void showWelcomeMessage() {
//        System.out.println("*********************************************");
//        System.out.println("*     WELCOME TO THE SHOPPING CART      *");
//        System.out.println("*********************************************");
//        System.out.println("Today's date: " + java.time.LocalDate.now());
//        System.out.println("We have " + productCatalog.size() + " products available.");
//        System.out.println("*********************************************");
//    }
//
//    private static boolean processMenuChoice(int choice) {
//        switch (choice) {
//            case 1:
//                displayProductCatalog();
//                break;
//            case 2:
//                addProductToCart();
//                break;
//            case 3:
//                removeProductFromCart();
//                break;
//            case 4:
//                updateProductQuantity();
//                break;
//            case 5:
//                cart.displayCart();
//                break;
//            case 6:
//                applyDiscount();
//                break;
//            case 7:
//                performCheckout();
//                break;
//            case 8:
//                cart.showHistory();
//                break;
//            case 9:
//                if (confirmExit()) {
//                    return false;
//                }
//                break;
//            case 10:
//                // Added new option to refresh product catalog from database
//                refreshProductCatalog();
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//        }
//        return true;
//    }
//
//    private static void displayMenu() {
//        System.out.println("\n===== Shopping Cart Menu =====");
//        System.out.println("1. View Product Catalog");
//        System.out.println("2. Add Product to Cart");
//        System.out.println("3. Remove Product from Cart");
//        System.out.println("4. Update Product Quantity");
//        System.out.println("5. View Cart");
//        System.out.println("6. Apply Discount");
//        System.out.println("7. Checkout");
//        System.out.println("8. View Cart History");
//        System.out.println("9. Exit");
//        System.out.println("10. Refresh Product Catalog from Database");
//    }
//
//    private static void refreshProductCatalog() {
//        System.out.println("Refreshing product catalog from database...");
//        loadProductsFromDatabase();
//        System.out.println("Product catalog refreshed successfully.");
//    }
//
//    // Backup method in case database connection fails
//    private static void initializeProducts() {
//        System.out.println("Loading sample products...");
//        // Add some sample products to the catalog
//        productCatalog.add(new Product(1, "Laptop", "XPS13", 1299.99, "Electronics",
//                "Dell", "Silver", 24, 10));
//        productCatalog.add(new Product(2, "Smartphone", "iPhone 15", 999.99, "Electronics",
//                "Apple", "Black", 12, 15));
//        productCatalog.add(new Product(3, "Headphones", "QC45", 349.99, "Audio",
//                "Bose", "White", 12, 20));
//        productCatalog.add(new Product(4, "Monitor", "UltraSharp", 499.99, "Computer Accessories",
//                "Dell", "Black", 36, 8));
//        productCatalog.add(new Product(5, "Keyboard", "MX Keys", 119.99, "Computer Accessories",
//                "Logitech", "Gray", 24, 30));
//    }
//
//    private static void displayProductCatalog() {
//        System.out.println("\n===== Product Catalog =====");
//        System.out.printf("%-5s %-15s %-20s %-15s %-10s %-10s %-15s%n",
//                "ID", "Brand", "Name", "Model", "Price", "Stock", "Warranty(mo)");
//        System.out.println("------------------------------------------------------------------------------------");
//
//        for (Product product : productCatalog) {
//            System.out.printf("%-5d %-15s %-20s %-15s $%-9.2f %-10d %-15d%n",
//                    product.getId(),
//                    product.getBrand(),
//                    product.getName(),
//                    product.getModel(),
//                    product.getPrice(),
//                    product.getStockQuantity(),
//                    product.getWarranty());
//        }
//    }
//
//    private static void addProductToCart() {
//        displayProductCatalog();
//        int productId = getIntInput("Enter product ID to add to cart: ");
//
//        try {
//            // Get fresh product data from database
//            Product product = productRepository.getProductById(productId);
//
//            if (product != null) {
//                int quantity = getIntInput("Enter quantity: ");
//                cart.addToCart(product, quantity);
//
//                // Update local catalog with fresh data
//                updateLocalProductCatalog(product);
//            } else {
//                System.out.println("Product not found. Please try again.");
//            }
//        } catch (SQLException e) {
//            System.err.println("Database error: " + e.getMessage());
//            // Fall back to local product lookup
//            Product product = findProductById(productId);
//            if (product != null) {
//                int quantity = getIntInput("Enter quantity: ");
//                cart.addToCart(product, quantity);
//            } else {
//                System.out.println("Product not found. Please try again.");
//            }
//        }
//    }
//
//    private static void updateLocalProductCatalog(Product updatedProduct) {
//        for (int i = 0; i < productCatalog.size(); i++) {
//            if (productCatalog.get(i).getId() == updatedProduct.getId()) {
//                productCatalog.set(i, updatedProduct);
//                break;
//            }
//        }
//    }
//
//    private static void removeProductFromCart() {
//        cart.displayCart();
//        if (cartIsEmpty()) return;
//
//        int productId = getIntInput("Enter product ID to remove from cart: ");
//        cart.removeProduct(productId);
//    }
//
//    private static void updateProductQuantity() {
//        cart.displayCart();
//        if (cartIsEmpty()) return;
//
//        int productId = getIntInput("Enter product ID to update: ");
//        int newQuantity = getIntInput("Enter new quantity: ");
//        cart.updateQuantity(productId, newQuantity);
//    }
//
//    private static void applyDiscount() {
//        cart.displayCart();
//        if (cartIsEmpty()) return;
//
//        double discount = getDoubleInput("Enter discount percentage (0-100): ");
//        cart.applyDiscount(discount);
//    }
//
//    private static void performCheckout() {
//        if (cartIsEmpty()) return;
//
//        System.out.println("\nPreparing to checkout...");
//        boolean confirmed = getYesNoInput("Confirm checkout (y/n): ");
//
//        if (confirmed) {
//            try {
//                cart.checkout();
//                // Refresh product catalog after checkout to reflect updated stock
//                refreshProductCatalog();
//            } catch (Exception e) {
//                System.out.println("Checkout failed: " + e.getMessage());
//                System.out.println("Please try again or contact customer support.");
//            }
//        } else {
//            System.out.println("Checkout canceled.");
//        }
//    }
//
//    private static boolean cartIsEmpty() {
//        if (cart.isEmpty()) {
//            System.out.println("Your cart is empty! Add some products first.");
//            return true;
//        }
//        return false;
//    }
//
//    private static boolean confirmExit() {
//        if (!cart.isEmpty()) {
//            boolean confirmed = getYesNoInput("You still have items in your cart. Are you sure you want to exit? (y/n): ");
//            if (!confirmed) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static Product findProductById(int id) {
//        for (Product product : productCatalog) {
//            if (product.getId() == id) {
//                return product;
//            }
//        }
//        return null;
//    }
//
//    private static int getIntInput(String prompt) {
//        System.out.print(prompt);
//        while (!scanner.hasNextInt()) {
//            System.out.println("Please enter a valid number.");
//            scanner.next();  // Consume invalid input
//            System.out.print(prompt);
//        }
//        int input = scanner.nextInt();
//        scanner.nextLine();  // Consume newline
//        return input;
//    }
//
//    private static double getDoubleInput(String prompt) {
//        System.out.print(prompt);
//        while (!scanner.hasNextDouble()) {
//            System.out.println("Please enter a valid number.");
//            scanner.next();  // Consume invalid input
//            System.out.print(prompt);
//        }
//        double input = scanner.nextDouble();
//        scanner.nextLine();  // Consume newline
//        return input;
//    }
//
//    private static boolean getYesNoInput(String prompt) {
//        System.out.print(prompt);
//        String input = scanner.nextLine().trim().toLowerCase();
//        while (!input.equals("y") && !input.equals("n")) {
//            System.out.println("Please enter 'y' for yes or 'n' for no.");
//            System.out.print(prompt);
//            input = scanner.nextLine().trim().toLowerCase();
//        }
//        return input.equals("y");
//    }
//}