package Admin.Control.Function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackHistory {

    static String url = "jdbc:mysql://localhost:3306/Ecommerce";
    static String user = "root";
    static String password = "";

    public static void getAllAdminActions() {

        String selectSQL = "SELECT * FROM AdminHistory ORDER BY ActionTimestamp DESC";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = conn.prepareStatement(selectSQL);
             ResultSet result = statement.executeQuery()) {

            if (!result.isBeforeFirst()) {
                System.out.println("No action history available.");
            } else {
                while (result.next()) {
                    int id = result.getInt("ProductID");
                    String action = result.getString("ActionType");
                    String timestamp = result.getString("ActionTimestamp");

                    System.out.println("---------------------------------");
                    System.out.println("Product ID       : " + id);
                    System.out.println("Action Type      : " + action);
                    System.out.println("Time             : " + timestamp);
                    System.out.println("---------------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
