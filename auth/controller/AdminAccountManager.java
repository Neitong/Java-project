package auth.controller;

import java.sql.*;
import java.util.Scanner;

public class AdminAccountManager {
    // Class-level database connection constants
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Ecommerce";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void viewAdminAccount(Scanner obj) {
        while (true) {
            System.out.println("\n===== Admin Account Management =====");
            System.out.println("1. Create Admin Account");
            System.out.println("2. View All Admin Accounts");
            System.out.println("3. Search Admin Account");
            System.out.println("4. Update Admin Account");
            System.out.println("5. Delete Admin Account");
            System.out.println("6. Exit");
            System.out.println("---------------------------------");
            System.out.print("Enter your choice: ");

            int choice = obj.nextInt();
            obj.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAdminAccount(obj);
                    break;
                case 2:
                    viewAllAdminAccounts();
                    break;
                case 3:
                    searchAdminAccount(obj);
                    break;
                case 4:
                    updateAdminAccount(obj);
                    break;
                case 5:
                    deleteAdminAccount(obj);
                    break;
                case 6:
                    System.out.println("Exiting Admin Account Management...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1-6.");
            }
        }
    }

    private static void createAdminAccount(Scanner obj) {
        System.out.println("\n--- Create Admin Account ---");
        System.out.print("Enter first name: ");
        String firstName = obj.nextLine();

        System.out.print("Enter last name: ");
        String lastName = obj.nextLine();

        String username = firstName + " " + lastName;

        System.out.print("Enter email: ");
        String email = obj.nextLine();

        System.out.print("Enter password: ");
        String password = obj.nextLine();



        String query = "INSERT INTO Users (Username, Email, Password, Firstname, Last_Name, Roles) " +
                "VALUES (?, ?, ?, ?, ?, 'ADMIN')";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, firstName);
            statement.setString(5, lastName);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Admin account created successfully!");
            } else {
                System.out.println("Failed to create admin account.");
            }
        } catch (SQLException e) {
            System.out.println("Error creating admin account: " + e.getMessage());
        }
    }

    private static void viewAllAdminAccounts() {
        System.out.println("\n--- All Admin Accounts ---");

        String query = "SELECT User_ID, Username, Email, Firstname, Last_Name, Registration_Date " +
                "FROM Users WHERE Roles = 'ADMIN' ORDER BY User_ID";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.printf("%-5s %-15s %-25s %-15s %-15s %-20s%n",
                    "ID", "Username", "Email", "First Name", "Last Name", "Registration Date");
            System.out.println("-------------------------------------------------------------------------------");

            while (resultSet.next()) {
                System.out.printf("%-5d %-15s %-25s %-15s %-15s %-20s%n",
                        resultSet.getInt("User_ID"),
                        resultSet.getString("Username"),
                        resultSet.getString("Email"),
                        resultSet.getString("Firstname"),
                        resultSet.getString("Last_Name"),
                        resultSet.getTimestamp("Registration_Date"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving admin accounts: " + e.getMessage());
        }
    }

    private static void searchAdminAccount(Scanner obj) {
        System.out.println("\n--- Search Admin Account ---");
        System.out.print("Enter username to search: ");
        String searchTerm = obj.nextLine();

        String query = "SELECT User_ID, Username, Email, Firstname, Last_Name, Registration_Date " +
                "FROM Users WHERE Roles = 'ADMIN' AND Username = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            try {
                int id = Integer.parseInt(searchTerm);
                statement.setString(1, "%" + searchTerm + "%");
            } catch (NumberFormatException e) {
                statement.setString(1, "%" + searchTerm + "%");
            }

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No admin accounts found matching your search.");
            } else {
                System.out.printf("%-5s %-15s %-25s %-15s %-15s %-20s%n",
                        "ID", "Username", "Email", "First Name", "Last Name", "Registration Date");
                System.out.println("-------------------------------------------------------------------------------");

                while (resultSet.next()) {
                    System.out.printf("%-5d %-15s %-25s %-15s %-15s %-20s%n",
                            resultSet.getInt("User_ID"),
                            resultSet.getString("Username"),
                            resultSet.getString("Email"),
                            resultSet.getString("Firstname"),
                            resultSet.getString("Last_Name"),
                            resultSet.getTimestamp("Registration_Date"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching admin accounts: " + e.getMessage());
        }
    }

    private static void updateAdminAccount(Scanner obj) {
        System.out.println("\n--- Update Admin Account ---");
        System.out.print("Enter Username to update: ");
        String adminId = obj.nextLine();
        obj.nextLine(); // Consume newline

        // First display current info
        String selectQuery = "SELECT * FROM Users WHERE User_ID = ? AND Roles = 'ADMIN'";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {

            selectStmt.setString(1, adminId);
            ResultSet resultSet = selectStmt.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Admin account not found.");
                return;
            }

            System.out.println("\nCurrent Admin Information:");
            System.out.println("ID: " + resultSet.getInt("User_ID"));
            System.out.println("Username: " + resultSet.getString("Username"));
            System.out.println("Email: " + resultSet.getString("Email"));
            System.out.println("First Name: " + resultSet.getString("Firstname"));
            System.out.println("Last Name: " + resultSet.getString("Last_Name"));

            System.out.println("\nEnter new information (leave blank to keep current value):");

            System.out.print("New first name: ");
            String newFirstName = obj.nextLine();

            System.out.print("New last name: ");
            String newLastName = obj.nextLine();

            String newUsername = newFirstName + " " + newLastName;

            System.out.print("New email: ");
            String newEmail = obj.nextLine();

            System.out.print("New password: ");
            String newPassword = obj.nextLine();

            String updateQuery = "UPDATE Users SET " +
                    "Username = COALESCE(NULLIF(?, ''), Username), " +
                    "Email = COALESCE(NULLIF(?, ''), Email), " +
                    "Password = COALESCE(NULLIF(?, ''), Password), " +
                    "Firstname = COALESCE(NULLIF(?, ''), Firstname), " +
                    "Last_Name = COALESCE(NULLIF(?, ''), Last_Name) " +
                    "WHERE Username = ?";

            try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                updateStmt.setString(1, newUsername);
                updateStmt.setString(2, newEmail);
                updateStmt.setString(3, newPassword);
                updateStmt.setString(4, newFirstName);
                updateStmt.setString(5, newLastName);
                updateStmt.setString(6, adminId);

                int rowsAffected = updateStmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Admin account updated successfully!");
                } else {
                    System.out.println("Failed to update admin account.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating admin account: " + e.getMessage());
        }
    }

    private static void deleteAdminAccount(Scanner obj) {
        System.out.println("\n--- Delete Admin Account ---");
        System.out.print("Enter admin ID to delete: ");
        int adminId = obj.nextInt();
        obj.nextLine(); // Consume newline

        System.out.print("Are you sure you want to delete this admin account? (yes/no): ");
        String confirmation = obj.nextLine();

        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        String query = "DELETE FROM Users WHERE User_ID = ? AND Roles = 'ADMIN'";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, adminId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Admin account deleted successfully!");
            } else {
                System.out.println("No admin account found with that ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting admin account: " + e.getMessage());
        }
    }
}
