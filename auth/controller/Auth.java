package auth.controller;

import auth.LogIn.Login;
import auth.SignIn.SignIn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class Auth {
    private String username;
    private String password;
    private final static String url = "jdbc:mysql://localhost:3306/online_shopping";
    private final static String user_db = "root";
    private final static String password_db = "";

    static Roles roles; //
    private static Login loginBehavior; //Hold a reference to interface class
    private static SignIn signInBehavior;

    // Constructor
    public Auth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Auth(String username, String password, Roles roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }


    public Auth(){

    }

    public void display() {

    }


    public static void performLogin() {
        Scanner input = new Scanner(System.in);
        System.out.println("**** Authentication Form ****");
        System.out.print("Enter Username/E-mail: ");
        String username = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();

        // Query to check user credentials
        String query = "SELECT username, email, roles FROM User WHERE email=? AND password=?";

        try (Connection connection = DriverManager.getConnection(url, user_db, password_db);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {  // If a user is found
                String dbUsername = resultSet.getString("username");
                String email = resultSet.getString("email");
                String role = resultSet.getString("roles");

                System.out.println("Login Successful!");
                System.out.println("Welcome, " + dbUsername + " (" + email + ")");

                // Role-based authentication
                if (role.equalsIgnoreCase("Admin")) {
                    System.out.println("You have Admin access.");
                } else if (role.equalsIgnoreCase("User")) {
                    System.out.println("You have User access.");
                } else if (role.equalsIgnoreCase("Guest")) {
                    System.out.println("You have Guest access.");
                } else {
                    System.out.println("Unknown role.");
                }

            } else {
                System.out.println("Invalid username/email or password.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        input.close();
    }

    public static void performSignIn() {
        Scanner input = new Scanner(System.in);
        System.out.println("**** Registration Form ****");

        System.out.print("Enter Username: ");
        String username = input.nextLine();

        System.out.print("Enter Email: ");
        String email = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        System.out.print("Enter Role (Admin/User/Guest): ");
        String role = input.nextLine();

        // Validate role input
        if (!(role.equalsIgnoreCase("Admin") || role.equalsIgnoreCase("User") || role.equalsIgnoreCase("Guest"))) {
            System.out.println("Invalid role. Please enter Admin, User, or Guest.");
            return;
        }

        // Query to check if email already exists
        String checkQuery = "SELECT email FROM User WHERE email = ?";

        // Query to insert a new user
        String insertQuery = "INSERT INTO User (username, email, password, roles) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user_db, password_db);
             PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            // Check if email already exists
            checkStatement.setString(1, email);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Error: This email is already registered.");
                return;
            }

            // Insert new user
            insertStatement.setString(1, username);
            insertStatement.setString(2, email);
            insertStatement.setString(3, password);  // Ideally, hash the password before saving!
            insertStatement.setString(4, role);

            int rowsInserted = insertStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Registration successful! You can now log in.");
            } else {
                System.out.println("Error: Registration failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        input.close();
    }





    // Setter methods to change behavior dynamically
    public void setLoginBehavior(Login loginBehavior) {
        Auth.loginBehavior = loginBehavior;
    }

    public void setSignInBehavior(SignIn signInBehavior) {
        Auth.signInBehavior = signInBehavior;
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("**** Authentication Form ****");
        System.out.println("1.log in ");
        System.out.println("2.Sign in");
        System.out.println("3.Exit");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                performLogin();
                break;
            case 2:
                performSignIn();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please2 try again.");
        }

    }


}

