package auth.controller;

import auth.controller.role.Roles;
import auth.controller.role.Admin;
import auth.controller.role.User;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login implements Authentication {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Ecommerce";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private String Username;
    private String Password;
    private String email;
    private Roles role;

    public Login( String password, String email, Roles role) {
        this.Password = password;
        this.email = email;
        this.role = role;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }




    public static void performLogin(Scanner input){
        System.out.println("\n**** Login ****");
        System.out.print("Enter your email: ");
        String inputEmail = input.nextLine();
        System.out.print("Enter your password: ");
        String inputPassword = input.nextLine();

        String query = "SELECT Username, Email, Password , Roles FROM Users WHERE Email = ? AND Password = ?";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, inputEmail);
                statement.setString(2, inputPassword);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String usernameRetriveFromDatabase = resultSet.getString("Username");
                        String emailRetriveFromDatabase = resultSet.getString("Email");
                        String role = resultSet.getString("Roles");

                        System.out.println("\nLogin successful!");
                        System.out.printf("Welcome, %s (%s)%n", usernameRetriveFromDatabase, emailRetriveFromDatabase);

                        // Route based on role
                        if (role.equalsIgnoreCase("ADMIN")) {
                            System.out.println("Redirecting to Admin Dashboard...");
                            Roles roleAdmin = Roles.ADMIN;
                            Admin admin = new Admin(usernameRetriveFromDatabase, emailRetriveFromDatabase, inputPassword, roleAdmin);
                            admin.adminDisplay();

                        } else if (role.equalsIgnoreCase("SUPER-ADMIN")) {
                            System.out.println("Redirecting to Super Admin Dashboard...");
                            Roles roleAdmin = Roles.ADMIN;
                            Admin admin = new Admin(usernameRetriveFromDatabase, emailRetriveFromDatabase, inputPassword, roleAdmin);
                            admin.superAdminDisplay();
                        }else if(role.equalsIgnoreCase("USER")) {
                            System.out.println("Redirecting to User Dashboard...");
                            Roles roleUser = Roles.USER;
                            User user = new User(usernameRetriveFromDatabase, emailRetriveFromDatabase, inputPassword, roleUser);
                            user.display();
                        }
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}

