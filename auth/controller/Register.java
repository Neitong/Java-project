package auth.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import auth.controller.role.Roles;
import auth.controller.role.Admin;
import auth.controller.role.User;


public class Register implements Authentication {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Ecommerce";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    CheckUser check = new CheckUser();




    public static void performRegistration(Scanner scanner) {
        System.out.println("\n**** Registration ****");

        String username, email, password, confirmPassword, firstName, lastName, fullname;
        Roles role = Roles.USER; // Default role


        // Username validation
        while (true) {
            System.out.print("Enter first name: ");
            firstName = scanner.nextLine().trim();

            System.out.print("Enter last name: ");
            lastName = scanner.nextLine().trim();

            username =firstName + " " + lastName;

            if (username.isEmpty()) {
                System.out.println("Username cannot be empty.");
                continue;
            }

            if (username.length() > 50) {
                System.out.println("Username must be 50 characters or less.");
                continue;
            }

            try {
                if (CheckUser.checkIfExists("Username", username)) {
                    System.out.println("Username already taken. Please choose another.");
                    continue;
                }
            } catch (SQLException e) {
                System.out.println("Database error while checking username: " + e.getMessage());
                return; // Exit registration on database error
            }

            break;
        }

        // Email validation
        while (true) {
            System.out.print("Enter Email: ");
            email = scanner.nextLine().trim();

            if (email.isEmpty()) {
                System.out.println("Email cannot be empty.");
                continue;
            }

            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                System.out.println("Invalid email format.");
                continue;
            }

            try {
                if (CheckUser.checkIfExists("Email", email)) {
                    System.out.println("Email already registered.");
                    continue;
                }
            } catch (SQLException e) {
                System.out.println("Database error while checking email: " + e.getMessage());
                return; // Exit registration on database error
            }

            break;
        }

        // Password validation
        while (true) {
            System.out.print("Enter Password: ");
            password = scanner.nextLine();
            System.out.print("Confirm Password: ");
            confirmPassword = scanner.nextLine();

            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match.");
                continue;
            }

            if (password.length() < 8) {
                System.out.println("Password must be at least 8 characters.");
                continue;
            }

            break;
        }

        // Role selection (only allow USER for self-registration)
        System.out.println("Your account will be created as a regular USER.");

        String query = "INSERT INTO Users (Username, Email, Password, Roles, Firstname, Last_name, Registration_Date) " +
                "VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, password);
                statement.setString(4, role.toString());
                statement.setString(5, firstName);
                statement.setString(6, lastName);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    Login.performLogin(scanner);
                } else {
                    System.out.println("Registration failed. Please try again.");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
