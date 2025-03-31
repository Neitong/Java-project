package auth.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CheckUser implements Authentication{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Ecommerce";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static boolean checkIfExists(String column, String value) throws SQLException {
        String query = String.format("SELECT 1 FROM Users WHERE %s = ?", column);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, value);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}
