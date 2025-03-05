package Admin.Control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestConnectionDatabase {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/OnlineShopping";
        String user = "root";
        String password = "";

        String insertQuery = "INSERT INTO StockProduct (Product_ID, Product_Name, Product_Model, Product_Price, Product_Quantity, Product_Type, Product_Brand, Product_Color, Product_Warranty) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Insert Product 1
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Smartphone");
            preparedStatement.setString(3, "S12");
            preparedStatement.setFloat(4, 599.99f);
            preparedStatement.setInt(5, 50);
            preparedStatement.setString(6, "Electronics");
            preparedStatement.setString(7, "BrandX");
            preparedStatement.setString(8, "Black");
            preparedStatement.setInt(9, 12);
            preparedStatement.executeUpdate();

            // Insert Product 2
            preparedStatement.setInt(1, 2);
            preparedStatement.setString(2, "Galaxy S23");
            preparedStatement.setString(3, "SM-S911B");
            preparedStatement.setFloat(4, 899.99f);
            preparedStatement.setInt(5, 8);
            preparedStatement.setString(6, "Smartphone");
            preparedStatement.setString(7, "Samsung");
            preparedStatement.setString(8, "Phantom Black");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            // Insert Product 3
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, "Sony WH-1000XM5");
            preparedStatement.setString(3, "WH1000XM5/B");
            preparedStatement.setFloat(4, 349.99f);
            preparedStatement.setInt(5, 15);
            preparedStatement.setString(6, "Headset");
            preparedStatement.setString(7, "Sony");
            preparedStatement.setString(8, "Blue");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            // Insert Product 4
            preparedStatement.setInt(1, 4);
            preparedStatement.setString(2, "Logitech G502 Hero");
            preparedStatement.setString(3, "G502");
            preparedStatement.setFloat(4, 49.99f);
            preparedStatement.setInt(5, 30);
            preparedStatement.setString(6, "Mouse");
            preparedStatement.setString(7, "Logitech");
            preparedStatement.setString(8, "Black");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            // Insert Product 5
            preparedStatement.setInt(1, 5);
            preparedStatement.setString(2, "Dell XPS 13");
            preparedStatement.setString(3, "XPS13-2023");
            preparedStatement.setFloat(4, 1499.99f);
            preparedStatement.setInt(5, 10);
            preparedStatement.setString(6, "Laptop");
            preparedStatement.setString(7, "Dell");
            preparedStatement.setString(8, "White");
            preparedStatement.setInt(9, 2);
            preparedStatement.executeUpdate();

            // Insert Product 6
            preparedStatement.setInt(1, 6);
            preparedStatement.setString(2, "iPhone 13");
            preparedStatement.setString(3, "IP13");
            preparedStatement.setFloat(4, 799.99f);
            preparedStatement.setInt(5, 12);
            preparedStatement.setString(6, "Smartphone");
            preparedStatement.setString(7, "Apple");
            preparedStatement.setString(8, "Blue");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            // Insert Product 7
            preparedStatement.setInt(1, 7);
            preparedStatement.setString(2, "OnePlus 9 Pro");
            preparedStatement.setString(3, "OP9P");
            preparedStatement.setFloat(4, 999.99f);
            preparedStatement.setInt(5, 20);
            preparedStatement.setString(6, "Smartphone");
            preparedStatement.setString(7, "OnePlus");
            preparedStatement.setString(8, "Morning Mist");
            preparedStatement.setInt(9, 2);
            preparedStatement.executeUpdate();

            // Insert Product 8
            preparedStatement.setInt(1, 8);
            preparedStatement.setString(2, "HP Pavilion 15");
            preparedStatement.setString(3, "HP-PAV15");
            preparedStatement.setFloat(4, 799.99f);
            preparedStatement.setInt(5, 10);
            preparedStatement.setString(6, "Laptop");
            preparedStatement.setString(7, "HP");
            preparedStatement.setString(8, "Black");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            // Insert Product 9
            preparedStatement.setInt(1, 9);
            preparedStatement.setString(2, "Razer Kraken X");
            preparedStatement.setString(3, "RZ-04010100-R3M1");
            preparedStatement.setFloat(4, 49.99f);
            preparedStatement.setInt(5, 15);
            preparedStatement.setString(6, "Headset");
            preparedStatement.setString(7, "Razer");
            preparedStatement.setString(8, "Green");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            // Insert Product 10
            preparedStatement.setInt(1, 10);
            preparedStatement.setString(2, "Asus ROG Strix");
            preparedStatement.setString(3, "ROG1234");
            preparedStatement.setFloat(4, 1200.00f);
            preparedStatement.setInt(5, 8);
            preparedStatement.setString(6, "Laptop");
            preparedStatement.setString(7, "Asus");
            preparedStatement.setString(8, "Black");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            // Insert Product 11
            preparedStatement.setInt(1, 11);
            preparedStatement.setString(2, "Oculus Quest 2");
            preparedStatement.setString(3, "OCU2");
            preparedStatement.setFloat(4, 299.99f);
            preparedStatement.setInt(5, 25);
            preparedStatement.setString(6, "VR Headset");
            preparedStatement.setString(7, "Oculus");
            preparedStatement.setString(8, "White");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            // Insert Product 12
            preparedStatement.setInt(1, 12);
            preparedStatement.setString(2, "Bose QuietComfort 35");
            preparedStatement.setString(3, "BQC35");
            preparedStatement.setFloat(4, 299.99f);
            preparedStatement.setInt(5, 10);
            preparedStatement.setString(6, "Headset");
            preparedStatement.setString(7, "Bose");
            preparedStatement.setString(8, "Black");
            preparedStatement.setInt(9, 2);
            preparedStatement.executeUpdate();

            // Insert Product 13
            preparedStatement.setInt(1, 13);
            preparedStatement.setString(2, "Lenovo Legion 5");
            preparedStatement.setString(3, "L5-2023");
            preparedStatement.setFloat(4, 1399.99f);
            preparedStatement.setInt(5, 12);
            preparedStatement.setString(6, "Laptop");
            preparedStatement.setString(7, "Lenovo");
            preparedStatement.setString(8, "Grey");
            preparedStatement.setInt(9, 2);
            preparedStatement.executeUpdate();

            // Insert Product 14
            preparedStatement.setInt(1, 14);
            preparedStatement.setString(2, "Acer Nitro 5");
            preparedStatement.setString(3, "AN5-2023");
            preparedStatement.setFloat(4, 999.99f);
            preparedStatement.setInt(5, 18);
            preparedStatement.setString(6, "Laptop");
            preparedStatement.setString(7, "Acer");
            preparedStatement.setString(8, "Red");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            // Insert Product 15
            preparedStatement.setInt(1, 15);
            preparedStatement.setString(2, "Microsoft Surface Laptop");
            preparedStatement.setString(3, "SL-2023");
            preparedStatement.setFloat(4, 1299.99f);
            preparedStatement.setInt(5, 10);
            preparedStatement.setString(6, "Laptop");
            preparedStatement.setString(7, "Microsoft");
            preparedStatement.setString(8, "Platinum");
            preparedStatement.setInt(9, 2);
            preparedStatement.executeUpdate();

            // Insert Product 16
            preparedStatement.setInt(1, 16);
            preparedStatement.setString(2, "Google Pixel 6");
            preparedStatement.setString(3, "GP6");
            preparedStatement.setFloat(4, 599.99f);
            preparedStatement.setInt(5, 15);
            preparedStatement.setString(6, "Smartphone");
            preparedStatement.setString(7, "Google");
            preparedStatement.setString(8, "Stormy Black");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            // Insert Product 17
            preparedStatement.setInt(1, 17);
            preparedStatement.setString(2, "Nokia 5.4");
            preparedStatement.setString(3, "Nokia54");
            preparedStatement.setFloat(4, 199.99f);
            preparedStatement.setInt(5, 30);
            preparedStatement.setString(6, "Smartphone");
            preparedStatement.setString(7, "Nokia");
            preparedStatement.setString(8, "Polar Night");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            // Insert Product 18
            preparedStatement.setInt(1, 18);
            preparedStatement.setString(2, "Xiaomi Mi 11");
            preparedStatement.setString(3, "MI11");
            preparedStatement.setFloat(4, 749.99f);
            preparedStatement.setInt(5, 12);
            preparedStatement.setString(6, "Smartphone");
            preparedStatement.setString(7, "Xiaomi");
            preparedStatement.setString(8, "Horizon Blue");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            // Insert Product 19
            preparedStatement.setInt(1, 19);
            preparedStatement.setString(2, "HP Envy 13");
            preparedStatement.setString(3, "HPEN13");
            preparedStatement.setFloat(4, 999.99f);
            preparedStatement.setInt(5, 15);
            preparedStatement.setString(6, "Laptop");
            preparedStatement.setString(7, "HP");
            preparedStatement.setString(8, "Silver");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            // Insert Product 20
            preparedStatement.setInt(1, 20);
            preparedStatement.setString(2, "Apple Watch SE");
            preparedStatement.setString(3, "AWSE");
            preparedStatement.setFloat(4, 279.99f);
            preparedStatement.setInt(5, 25);
            preparedStatement.setString(6, "Smartwatch");
            preparedStatement.setString(7, "Apple");
            preparedStatement.setString(8, "Midnight");
            preparedStatement.setInt(9, 1);
            preparedStatement.executeUpdate();

            System.out.println("All 20 products inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
