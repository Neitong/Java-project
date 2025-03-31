package Admin.Control.Function;

import java.sql.*;
import  java.util.Scanner;
import java.util.ArrayList;

import static java.lang.System.exit;

public class ManageProducts{

    static class TemporaryStock{
        int id;
        String name;
        String model;
        double price;
        int quantity;
        String productType;
        int warranty;
        String brand;
        String ProductColor;
    }


    public void AddProduct() {

        Scanner obj = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/Ecommerce";
        String user = "root";
        String password = "";
        ArrayList<TemporaryStock> productsList = new ArrayList<>();

        //Formula to insert data into database
        String insertProductQuery =
             "insert into Products (Product_ID, Product_Name, Product_Model," +
             "Product_Price, Product_Quantity, Product_Type, Product_Brand, Product_Color, Product_Warranty) " +
             "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        //Add History to history database when admin Make some changes
        String HistoryAdmin = "INSERT INTO AdminHistory (ProductID, ActionType, ActionTimestamp) VALUES (?, ?, NOW())";

        try(Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement insertProductStatement = connection.prepareStatement(insertProductQuery);
             PreparedStatement HistoryAdminStatement = connection.prepareStatement(HistoryAdmin)){

            while (true) {
                TemporaryStock tempStock = new TemporaryStock();

                System.out.print("Product ID        : ");
                tempStock.id = obj.nextInt();
                obj.nextLine();

                System.out.print("Product Name      : ");
                tempStock.name = obj.nextLine();

                System.out.print("Product Model     : ");
                tempStock.model = obj.nextLine();

                System.out.print("Product Price     : ");
                tempStock.price = obj.nextDouble();
                obj.nextLine();

                System.out.print("Product Quantity  : ");
                tempStock.quantity = obj.nextInt();
                obj.nextLine();

                System.out.print("Product Type      : ");
                tempStock.productType = obj.nextLine();

                System.out.print("Product Brand     : ");
                tempStock.brand = obj.nextLine();

                System.out.print("Product Color     : ");
                tempStock.ProductColor = obj.nextLine();

                System.out.print("Product Warranty  : ");
                tempStock.warranty = obj.nextInt();
                obj.nextLine();

                // Store product temporarily in the ArrayList
                productsList.add(tempStock);

                // Ask user if they want to add another product
                System.out.print("Do you want to add another product? (Y|N): ");
                char choice = obj.nextLine().charAt(0);

                if(choice == 'N' || choice == 'n'){
                    break;
                }
            }

            for (TemporaryStock tempStock : productsList) {
                // Insert the product into the StockProduct table
                insertProductStatement.setInt(1, tempStock.id);
                insertProductStatement.setString(2, tempStock.name);
                insertProductStatement.setString(3, tempStock.model);
                insertProductStatement.setDouble(4, tempStock.price);
                insertProductStatement.setInt(5, tempStock.quantity);
                insertProductStatement.setString(6, tempStock.productType);
                insertProductStatement.setString(7, tempStock.brand);
                insertProductStatement.setString(8, tempStock.ProductColor);
                insertProductStatement.setInt(9, tempStock.warranty);

                //It identified the row that have affected row in database
                int rowsAffected = insertProductStatement.executeUpdate();

                //Check it if affected
                if (rowsAffected > 0) {
                    System.out.println("Product " + tempStock.id + " added successfully.");

                    //Update the info into history database
                    HistoryAdminStatement.setInt(1, tempStock.id);
                    HistoryAdminStatement.setString(2, "ADD");
                    HistoryAdminStatement.executeUpdate();
                } else {
                    System.out.println("Add " + tempStock.id + " Product failed.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void UpdateProduct() {
        Scanner obj = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/Ecommerce";
        String user = "root";
        String password = "";

        System.out.print("Enter product ID: ");
        int product_id = obj.nextInt();
        obj.nextLine();

        //used to search product
        String searchQuery = "SELECT * FROM Products WHERE Product_ID = ?";

        //used to update products
        String updateQuery =
        "UPDATE Products SET Product_Name = ?, Product_Model = ?, Product_Price = ?," +
        " Product_Quantity = ?, Product_Type = ?, Product_Brand = ?, Product_Color = ?, " +
        "Product_Warranty = ? WHERE Product_ID = ?";

        String AdminHistory = "INSERT INTO AdminHistory (ProductID, ActionType, ActionTimestamp) VALUES (?, ?, NOW())";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement searchStatement = connection.prepareStatement(searchQuery);
             PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
             PreparedStatement AdminHistoryStatement = connection.prepareStatement(AdminHistory)) {

            //Search the product with match the id
            searchStatement.setInt(1, product_id);
            ResultSet resultSet = searchStatement.executeQuery();

            if (resultSet.next()) {
                // Display current product details
                System.out.println("Current Product Details:");
                System.out.println("---------------------------------");
                System.out.println("Product ID          : " + resultSet.getInt("Product_ID"));
                System.out.println("Product Name        : " + resultSet.getString("Product_Name"));
                System.out.println("Product Model       : " + resultSet.getString("Product_Model"));
                System.out.println("Product Price       : " + resultSet.getDouble("Product_Price") + " $");
                System.out.println("Product Quantity    : " + resultSet.getInt("Product_Quantity"));
                System.out.println("Product Type        : " + resultSet.getString("Product_Type"));
                System.out.println("Product Brand       : " + resultSet.getString("Product_Brand"));
                System.out.println("Product Color       : " + resultSet.getString("Product_Color"));
                System.out.println("Product Warranty    : " + resultSet.getInt("Product_Warranty") + " Year");
                System.out.println("---------------------------------");

                // Get new product details
                System.out.print("Enter new Product Name        : ");
                String name = obj.nextLine();
                System.out.print("Enter new Product Model       : ");
                String model = obj.nextLine();
                System.out.print("Enter new Product Price       : ");
                double price = obj.nextDouble();
                System.out.print("Enter new Product Quantity    : ");
                int quantity = obj.nextInt();
                obj.nextLine(); // Consume newline
                System.out.print("Enter new Product Type        : ");
                String type = obj.nextLine();
                System.out.print("Enter new Product Brand       : ");
                String brand = obj.nextLine();
                System.out.print("Enter new Product Color       : ");
                String color = obj.nextLine();
                System.out.print("Enter new Product Warranty    : ");
                int warranty = obj.nextInt();

                // Update new info into database
                updateStatement.setString(1, name);
                updateStatement.setString(2, model);
                updateStatement.setDouble(3, price);
                updateStatement.setInt(4, quantity);
                updateStatement.setString(5, type);
                updateStatement.setString(6, brand);
                updateStatement.setString(7, color);
                updateStatement.setInt(8, warranty);
                updateStatement.setInt(9, product_id);

                int rowsAffected = updateStatement.executeUpdate();


                //If rowsAffected > 0, it means at least one row was updated
                //If rowsAffected == 0, it means no rows were updated

                if (rowsAffected > 0) {
                    System.out.println("Product updated successfully.");

                    AdminHistoryStatement.setInt(1, product_id);
                    AdminHistoryStatement.setString(2, "UPDATE");// Set History type to Update
                    AdminHistoryStatement.executeUpdate();
                } else {
                    System.out.println("Product update failed.");
                }
            } else {
                System.out.println("Product with ID " + product_id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void DeleteProduct() {
        Scanner obj = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/Ecommerce";
        String user = "root";
        String password = "";

        System.out.print("Enter product ID to delete: ");
        int product_id = obj.nextInt();
        obj.nextLine();

        String deleteQuery = "DELETE FROM Products WHERE Product_ID = ?";
        String AdminHistory = "INSERT INTO AdminHistory (ProductID, ActionType, ActionTimestamp) VALUES (?, ?, NOW())";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
             PreparedStatement AdminHistoryStatement = connection.prepareStatement(AdminHistory)) {

            preparedStatement.setInt(1, product_id);
            int rowsAffected = preparedStatement.executeUpdate();

            //IT check if product is match the id or not to delete,
            //if it matchs the product id it will be 1 and delete
            //if not it will be 0

            if (rowsAffected > 0) {
                System.out.println("Product with ID " + product_id + " deleted successfully.");

                AdminHistoryStatement.setInt(1, product_id);
                AdminHistoryStatement.setString(2, "DELETE"); //Set product type to delete
                AdminHistoryStatement.executeUpdate();
            } else {
                System.out.println("Product with ID " + product_id + " not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SearchProduct() {
        Scanner obj = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/Ecommerce";
        String user = "root";
        String password = "";

        ArrayList<TemporaryStock> productsList = new ArrayList<>();

        System.out.print("Enter product ID: ");
        int product_id = obj.nextInt();
        obj.nextLine();

        //Formula to get product that match with id in databases
        String searchQuery = "SELECT * FROM Products WHERE Product_ID = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {

            preparedStatement.setInt(1, product_id);
            ResultSet resultSet = preparedStatement.executeQuery();// If it found


            while (resultSet.next()) {
                TemporaryStock tempStock = new TemporaryStock();

                tempStock.id = resultSet.getInt("Product_ID");
                tempStock.name = resultSet.getString("Product_Name");
                tempStock.model = resultSet.getString("Product_Model");
                tempStock.price = resultSet.getDouble("Product_Price");
                tempStock.quantity = resultSet.getInt("Product_Quantity");
                tempStock.productType = resultSet.getString("Product_Type");
                tempStock.brand = resultSet.getString("Product_Brand");
                tempStock.ProductColor = resultSet.getString("Product_Color");
                tempStock.warranty = resultSet.getInt("Product_Warranty");

                // Adding product to the list
                productsList.add(tempStock);
            }

            if (!productsList.isEmpty()) {
                //Just get data from productList to show
                for (TemporaryStock product : productsList) {
                    System.out.println("---------------------------------");
                    System.out.println("Product ID              : " + product.id);
                    System.out.println("Product Name            : " + product.name);
                    System.out.println("Product Model           : " + product.model);
                    System.out.println("Product Price           : " + product.price + " $");
                    System.out.println("Product Quantity        : " + product.quantity);
                    System.out.println("Product Type            : " + product.productType);
                    System.out.println("Product Brand           : " + product.brand);
                    System.out.println("Product Color           : " + product.ProductColor);
                    System.out.println("Product Warranty        : " + product.warranty + " Year");
                    System.out.println("---------------------------------");
                }
            } else {
                System.out.println("Product with ID " + product_id + " not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DisplayProduct() {
        ArrayList<StockProducts> productsList = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/Ecommerce";
        String user = "root";
        String password = "";

        String selectQuery = "SELECT * FROM Products";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            if (!resultSet.next()) {
                System.out.println("No products in the stock.");
                return;
            }
            do {
                TemporaryStock tempStock = new TemporaryStock();

                tempStock.id = resultSet.getInt("Product_ID");
                tempStock.name = resultSet.getString("Product_Name");
                tempStock.model = resultSet.getString("Product_Model");
                tempStock.price = resultSet.getDouble("Product_Price");
                tempStock.quantity = resultSet.getInt("Product_Quantity");
                tempStock.productType = resultSet.getString("Product_Type");
                tempStock.brand = resultSet.getString("Product_Brand");
                tempStock.ProductColor = resultSet.getString("Product_Color");
                tempStock.warranty = resultSet.getInt("Product_Warranty");


                productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price,
                        tempStock.quantity, tempStock.productType, tempStock.brand,
                        tempStock.ProductColor, tempStock.warranty));

            } while (resultSet.next());


            for (StockProducts product : productsList) {
                System.out.println("---------------------------------");
                System.out.println("Product ID              : " + product.getId());
                System.out.println("Product Name            : " + product.getName());
                System.out.println("Product Model           : " + product.getModel());
                System.out.println("Product Price           : " + product.getPrice() + " $");
                System.out.println("Product Quantity        : " + product.getQuantity());
                System.out.println("Product Type            : " + product.getProductType());
                System.out.println("Product Brand           : " + product.getBrand());
                System.out.println("Product Color           : " + product.getProductColor());
                System.out.println("Product Warranty        : " + product.getWarranty() + "  Year");
                System.out.println("---------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving products from the database.");
            e.printStackTrace();
        }
    }

    public void SortProduct(){
        Scanner obj= new Scanner(System.in);
        System.out.println("1. Sort Product by ID");
        System.out.println("2. Sort Product by price");
        System.out.println("3. Sort Product by Product Type");
        System.out.println("4. Exit this block");
        System.out.println("---------------------------------");
        System.out.print("Enter a number");
        int sort= obj.nextInt();
        obj.nextLine();
        switch (sort){
            case 1:
                SortByID();
                break;
            case 2:
                SortByPrice();
                break;
            case 3:
                SortByProductType();
                break;
            case 4:
                System.out.println("Exit this Page");
                exit(0);
            default:
                System.out.println("Invalid choice !!!");
        }

    }


    public void SortByPrice(){
        Scanner obj= new Scanner(System.in);
        int choice;
        System.out.println("1. Sort By Ascending");
        System.out.println("2. Sort By Descending");
        System.out.print("Choose a number");
        choice= obj.nextInt();
        obj.nextLine();
        switch (choice){
            case 1:
                Ascending();
                break;
            case 2:
                Descending();
                break;
            default:
                System.out.println("Invalid choice ");
        }
    }


    public void Ascending() {
        ArrayList<StockProducts> productsList = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/Ecommerce";
        String user = "root";
        String password = "";

        String selectQuery = "SELECT * FROM Products ORDER BY Product_Price ASC";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            if (!resultSet.next()) {
                System.out.println("No products in the stock.");
                return;
            }

            do {
                TemporaryStock tempStock = new TemporaryStock();

                tempStock.id = resultSet.getInt("Product_ID");
                tempStock.name = resultSet.getString("Product_Name");
                tempStock.model = resultSet.getString("Product_Model");
                tempStock.price = resultSet.getDouble("Product_Price");
                tempStock.quantity = resultSet.getInt("Product_Quantity");
                tempStock.productType = resultSet.getString("Product_Type");
                tempStock.brand = resultSet.getString("Product_Brand");
                tempStock.ProductColor = resultSet.getString("Product_Color");
                tempStock.warranty = resultSet.getInt("Product_Warranty");

                productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price,
                        tempStock.quantity, tempStock.productType, tempStock.brand,
                        tempStock.ProductColor, tempStock.warranty));

            } while (resultSet.next());

            // Display the sorted product list
            System.out.println("Product By Ascending");
            for (StockProducts product : productsList) {
                System.out.println("---------------------------------");
                System.out.println("Product ID              : " + product.getId());
                System.out.println("Product Name            : " + product.getName());
                System.out.println("Product Model           : " + product.getModel());
                System.out.println("Product Price           : " + product.getPrice() + " $");
                System.out.println("Product Quantity        : " + product.getQuantity());
                System.out.println("Product Type            : " + product.getProductType());
                System.out.println("Product Brand           : " + product.getBrand());
                System.out.println("Product Color           : " + product.getProductColor());
                System.out.println("Product Warranty        : " + product.getWarranty() + "  Year");
                System.out.println("---------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving products from the database.");
            e.printStackTrace();
        }
    }


    public void Descending() {
        ArrayList<StockProducts> productsList = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/Ecommerce";
        String user = "root";
        String password = "";

        String selectQuery = "SELECT * FROM Products ORDER BY Product_Price DESC";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            if (!resultSet.next()) {
                System.out.println("No products in the stock.");
                return;
            }

            do {
                TemporaryStock tempStock = new TemporaryStock();

                tempStock.id = resultSet.getInt("Product_ID");
                tempStock.name = resultSet.getString("Product_Name");
                tempStock.model = resultSet.getString("Product_Model");
                tempStock.price = resultSet.getDouble("Product_Price");
                tempStock.quantity = resultSet.getInt("Product_Quantity");
                tempStock.productType = resultSet.getString("Product_Type");
                tempStock.brand = resultSet.getString("Product_Brand");
                tempStock.ProductColor = resultSet.getString("Product_Color");
                tempStock.warranty = resultSet.getInt("Product_Warranty");

                productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price,
                        tempStock.quantity, tempStock.productType, tempStock.brand,
                        tempStock.ProductColor, tempStock.warranty));

            } while (resultSet.next());

            // Display the sorted product list
            System.out.println("Product By Descending");
            for (StockProducts product : productsList) {
                System.out.println("---------------------------------");
                System.out.println("Product ID              : " + product.getId());
                System.out.println("Product Name            : " + product.getName());
                System.out.println("Product Model           : " + product.getModel());
                System.out.println("Product Price           : " + product.getPrice() + " $");
                System.out.println("Product Quantity        : " + product.getQuantity());
                System.out.println("Product Type            : " + product.getProductType());
                System.out.println("Product Brand           : " + product.getBrand());
                System.out.println("Product Color           : " + product.getProductColor());
                System.out.println("Product Warranty        : " + product.getWarranty() + "  Year");
                System.out.println("---------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving products from the database.");
            e.printStackTrace();
        }
    }

    public void SortByID() {
        ArrayList<StockProducts> productsList = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/Ecommerce";
        String user = "root";
        String password = "";

        String selectQuery = "select * from Products order by Product_ID Asc";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            if (!resultSet.next()) {
                System.out.println("No products in the stock.");
                return;
            }

            do {
                TemporaryStock tempStock = new TemporaryStock();

                tempStock.id = resultSet.getInt("Product_ID");
                tempStock.name = resultSet.getString("Product_Name");
                tempStock.model = resultSet.getString("Product_Model");
                tempStock.price = resultSet.getDouble("Product_Price");
                tempStock.quantity = resultSet.getInt("Product_Quantity");
                tempStock.productType = resultSet.getString("Product_Type");
                tempStock.brand = resultSet.getString("Product_Brand");
                tempStock.ProductColor = resultSet.getString("Product_Color");
                tempStock.warranty = resultSet.getInt("Product_Warranty");

                productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price,
                        tempStock.quantity, tempStock.productType, tempStock.brand,
                        tempStock.ProductColor, tempStock.warranty));

            } while (resultSet.next());

            // Display the sorted product list
            System.out.println("Product By ID");
            for (StockProducts product : productsList) {
                System.out.println("---------------------------------");
                System.out.println("Product ID              : " + product.getId());
                System.out.println("Product Name            : " + product.getName());
                System.out.println("Product Model           : " + product.getModel());
                System.out.println("Product Price           : " + product.getPrice() + " $");
                System.out.println("Product Quantity        : " + product.getQuantity());
                System.out.println("Product Type            : " + product.getProductType());
                System.out.println("Product Brand           : " + product.getBrand());
                System.out.println("Product Color           : " + product.getProductColor());
                System.out.println("Product Warranty        : " + product.getWarranty() + "  Year");
                System.out.println("---------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving products from the database.");
            e.printStackTrace();
        }
    }


    public void SortByProductType() {
        ArrayList<StockProducts> productsList = new ArrayList<>();
        TemporaryStock tempStock = new TemporaryStock();
        boolean duplicateFound;
        Scanner obj = new Scanner(System.in);
        int choice;

        System.out.println("1 .  Sort By Laptop");
        System.out.println("2 .  Sort By Phone");
        System.out.println("3 .  Sort By Headphone");
        System.out.println("4 .  Sort By Charger");
        System.out.println("5 .  Sort By Adaptor");
        System.out.println("6 .  Sort By Headset");
        System.out.println("7 .  Sort By Speaker");
        System.out.println("8 .  Sort By Keyboard");
        System.out.println("9 .  Sort By Mouse");
        System.out.println("10. Sort  By MousePad");
        System.out.print("Choose a number : ");
        choice = obj.nextInt();
        obj.nextLine();  // Consume newline

        String productType = switch (choice) {
            case 1 -> "Laptop";
            case 2 -> "Phone";
            case 3 -> "Headphone";
            case 4 -> "Charger";
            case 5 -> "Adaptor";
            case 6 -> "Headset";
            case 7 -> "Speaker";
            case 8 -> "Keyboard";
            case 9 -> "Mouse";
            case 10 -> "MousePad";
            default -> {
                System.out.println("Invalid choice!");
                yield null;
//                yield null; means:
//                The switch returns null if someValue does not match any cases.
            }
        };

        if (productType == null) {
            return;
        }

        String url = "jdbc:mysql://localhost:3306/Ecommerce";
        String user = "root";
        String password = "";

        // Query to get products by product type
        String query = "SELECT * FROM Products WHERE Product_Type = ? ORDER BY Product_ID";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, productType);  // Set the product type in the query

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    System.out.println("No products found for this type.");
                    return;
                }

                do {
                    tempStock.id = resultSet.getInt("Product_ID");
                    tempStock.name = resultSet.getString("Product_Name");
                    tempStock.model = resultSet.getString("Product_Model");
                    tempStock.price = resultSet.getDouble("Product_Price");
                    tempStock.quantity = resultSet.getInt("Product_Quantity");
                    tempStock.productType = resultSet.getString("Product_Type");
                    tempStock.brand = resultSet.getString("Product_Brand");
                    tempStock.ProductColor = resultSet.getString("Product_Color");
                    tempStock.warranty = resultSet.getInt("Product_Warranty");

                    // Prevent adding duplicate products
                    duplicateFound = false;
                    for (StockProducts product : productsList) {
                        if (product.getId() == tempStock.id) {
                            duplicateFound = true;
                            break;
                        }
                    }

                    if (!duplicateFound) {
                        productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model,
                                tempStock.price, tempStock.quantity, tempStock.productType,
                                tempStock.brand, tempStock.ProductColor, tempStock.warranty));
                    }

                } while (resultSet.next());

                // Display sorted products
                System.out.println("Products of Type: " + productType);
                for (StockProducts product : productsList) {
                    System.out.println("---------------------------------");
                    System.out.println("Product ID              : " + product.getId());
                    System.out.println("Product Name            : " + product.getName());
                    System.out.println("Product Model           : " + product.getModel());
                    System.out.println("Product Price           : " + product.getPrice() + " $");
                    System.out.println("Product Quantity        : " + product.getQuantity());
                    System.out.println("Product Type            : " + product.getProductType());
                    System.out.println("Product Brand           : " + product.getBrand());
                    System.out.println("Product Color           : " + product.getProductColor());
                    System.out.println("Product Warranty        : " + product.getWarranty() + "  Year");
                    System.out.println("---------------------------------");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    public static void updateAmountProduct(int amount, int productID) {
        String url = "jdbc:mysql://localhost:3306/Ecommerce";
        String user = "root";
        String password = "";

        String searchQuery = "SELECT Product_Quantity FROM Products WHERE Product_ID = ?";
        String updateQuery = "UPDATE Products SET Product_Quantity = ? WHERE Product_ID = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement searchStatement = connection.prepareStatement(searchQuery);
             PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {

            searchStatement.setInt(1, productID);
            ResultSet resultSet = searchStatement.executeQuery();

            if (resultSet.next()) {
                int currentAmount = resultSet.getInt("Product_Quantity");
                if (currentAmount >= amount) {
                    int newAmount = currentAmount - amount;
                    updateStatement.setInt(1, newAmount);
                    updateStatement.setInt(2, productID);

                    int rowsAffected = updateStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Purchase successful. Remaining stock: " + newAmount);
                    } else {
                        System.out.println("Update failed.");
                    }
                } else {
                    System.out.println("Insufficient stock available.");
                }
            } else {
                System.out.println("Product not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void DisplayProductByProductID(int ProductID) {
        String url = "jdbc:mysql://localhost:3306/OnlineShopping";
        String user = "root";
        String password = "";

        String query = "SELECT * FROM Products WHERE Product_ID = ?";
        ArrayList<StockProducts> productsList = new ArrayList<>(); // Declare productsList

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, ProductID);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    TemporaryStock tempStock = new TemporaryStock();

                    tempStock.id = resultSet.getInt("Product_ID");
                    tempStock.name = resultSet.getString("Product_Name");
                    tempStock.model = resultSet.getString("Product_Model");
                    tempStock.price = resultSet.getDouble("Product_Price");
                    tempStock.quantity = resultSet.getInt("Product_Quantity");
                    tempStock.productType = resultSet.getString("Product_Type");
                    tempStock.brand = resultSet.getString("Product_Brand");
                    tempStock.ProductColor = resultSet.getString("Product_Color");
                    tempStock.warranty = resultSet.getInt("Product_Warranty");

                    productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price,
                            tempStock.quantity, tempStock.productType, tempStock.brand,
                            tempStock.ProductColor, tempStock.warranty));
                }

                // Display the sorted product list
                System.out.println("Product By ID");
                for (StockProducts product : productsList) {
                    System.out.println("---------------------------------");
                    System.out.println("Product ID              : " + product.getId());
                    System.out.println("Product Name            : " + product.getName());
                    System.out.println("Product Model           : " + product.getModel());
                    System.out.println("Product Price           : " + product.getPrice() + " $");
                    System.out.println("Product Quantity        : " + product.getQuantity());
                    System.out.println("Product Type            : " + product.getProductType());
                    System.out.println("Product Brand           : " + product.getBrand());
                    System.out.println("Product Color           : " + product.getProductColor());
                    System.out.println("Product Warranty        : " + product.getWarranty() + "  Year");
                    System.out.println("---------------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void DisplayProductByProductType(String productType) {
        String url = "jdbc:mysql://localhost:3306/Ecommerce";
        String user = "root";
        String password = "";

        String query = "SELECT * FROM Products WHERE Product_Type = ?";
        ArrayList<StockProducts> productsList = new ArrayList<>(); // Declare productsList

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, productType);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    TemporaryStock tempStock = new TemporaryStock();

                    tempStock.id = resultSet.getInt("Product_ID");
                    tempStock.name = resultSet.getString("Product_Name");
                    tempStock.model = resultSet.getString("Product_Model");
                    tempStock.price = resultSet.getDouble("Product_Price");
                    tempStock.quantity = resultSet.getInt("Product_Quantity");
                    tempStock.productType = resultSet.getString("Product_Type");
                    tempStock.brand = resultSet.getString("Product_Brand");
                    tempStock.ProductColor = resultSet.getString("Product_Color");
                    tempStock.warranty = resultSet.getInt("Product_Warranty");

                    productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price,
                            tempStock.quantity, tempStock.productType, tempStock.brand,
                            tempStock.ProductColor, tempStock.warranty));
                }

                // Display the sorted product list
                System.out.println("Product By ID");
                for (StockProducts product : productsList) {
                    System.out.println("---------------------------------");
                    System.out.println("Product ID              : " + product.getId());
                    System.out.println("Product Name            : " + product.getName());
                    System.out.println("Product Model           : " + product.getModel());
                    System.out.println("Product Price           : " + product.getPrice() + " $");
                    System.out.println("Product Quantity        : " + product.getQuantity());
                    System.out.println("Product Type            : " + product.getProductType());
                    System.out.println("Product Brand           : " + product.getBrand());
                    System.out.println("Product Color           : " + product.getProductColor());
                    System.out.println("Product Warranty        : " + product.getWarranty() + "  Year");
                    System.out.println("---------------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
