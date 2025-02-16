package Admin.Control;
import java.io.File;
import  java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class ManageProducts{

    public void AddProduct(){

        ArrayList<StockProducts> productsList= new ArrayList<>();
        TemporaryStock tempStock= new TemporaryStock();
        Scanner obj= new Scanner(System.in);

        try(FileWriter file = new FileWriter("Stock.txt", true)){
            while(true){

                System.out.print("Product ID:");
                tempStock.id= obj.nextInt();
                obj.nextLine();

                System.out.print("Product Name:");
                tempStock.name= obj.nextLine();

                System.out.print("Product Model:");
                tempStock.model= obj.nextLine();

                System.out.print("Product Price:");
                tempStock.price= obj.nextInt();
                obj.nextLine();

                System.out.print("Product Quantity:");
                tempStock.quantity= obj.nextInt();
                obj.nextLine();

                System.out.print("Product Type:");
                tempStock.productType= obj.nextLine();

                System.out.print("Product Brand:");
                tempStock.brand= obj.nextLine();

                System.out.print("Product Color:");
                tempStock.ProductColor= obj.nextLine();

                System.out.print("Product Warranty:");
                tempStock.warranty= obj.nextInt();
                obj.nextLine();

                //It put value back to constructor to specifies all the parameter
                StockProducts products = new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price, tempStock.quantity, tempStock.productType, tempStock.brand, tempStock.ProductColor, tempStock.warranty);
                productsList.add(products);
                //It stores obj of product to store in ArrayList one by one


                //It read one obj by one obj that productList has and point it to product to store it temporily
                for(StockProducts product : productsList){
                    file.write(product.getId()             + "\n");
                    file.write(product.getName()           + "\n");
                    file.write(product.getModel()          + "\n");
                    file.write(product.getPrice()          + "\n");
                    file.write(product.getQuantity()       + "\n");
                    file.write(product.getProductType()    + "\n");
                    file.write(product.getBrand()          + "\n");
                    file.write(product.getProductColor()   + "\n");
                    file.write(product.getWarranty()       + "\n");
                }

                System.out.println("That all of product you want to add ?  (Y|N):");
                char choice= obj.nextLine().charAt(0);

                if(choice=='Y' || choice== 'y'){
                    break;
                }
            }
        }catch (IOException e){
            System.out.println("No such a file");
        }

    }

    public void UpdateProduct(){

        ArrayList<StockProducts> productsList = new ArrayList<>();
        TemporaryStock tempStock= new TemporaryStock();
        boolean productFound=false;
        Scanner obj= new Scanner(System.in);
        int product_id;
        System.out.print("Enter product ID: ");
        product_id= obj.nextInt();
        obj.nextLine();

        try {
            File file = new File("Stock.txt");
            Scanner fileReader = new Scanner(file);

            // Loop through file until it do not have any obj

            while (fileReader.hasNextLine()) {

                // just get data from file and convert it to specific variables and data we have created
                // string => int, double...

                tempStock.id= Integer.parseInt(fileReader.nextLine());
                tempStock.name= fileReader.nextLine();
                tempStock.model= fileReader.nextLine();
                tempStock.price= Double.parseDouble(fileReader.nextLine());
                tempStock.quantity= Integer.parseInt(fileReader.nextLine());
                tempStock.productType= fileReader.nextLine();
                tempStock.brand= fileReader.nextLine();
                tempStock.ProductColor= fileReader.nextLine();
                tempStock.warranty= Integer.parseInt(fileReader.nextLine());


                // Find product id to match each other and give option to update products info
                if(tempStock.id == product_id){
                    productFound= true;
                    System.out.println("Current Product Details:");
                    System.out.println("---------------------------------");
                    System.out.println("Product ID          : " + tempStock.id);
                    System.out.println("Product Name        : " + tempStock.name);
                    System.out.println("Product Model       : " + tempStock.model);
                    System.out.println("Product Price       : " + tempStock.price + " $");
                    System.out.println("Product Quantity    : " + tempStock.quantity);
                    System.out.println("Product Type        : " + tempStock.productType);
                    System.out.println("Product Brand       : " + tempStock.brand);
                    System.out.println("Product Color       : " + tempStock.ProductColor);
                    System.out.println("Product Warranty    : " + tempStock.warranty + "  Year");
                    System.out.println("---------------------------------");

                    System.out.print("Enter new Product Name        : ");
                    tempStock.name= obj.nextLine();
                    System.out.print("Enter new Product Model       : ");
                    tempStock.model = obj.nextLine();
                    System.out.print("Enter new Product Price       : ");
                    tempStock.price = Double.parseDouble(obj.nextLine());
                    System.out.print("Enter new Product Quantity    : ");
                    tempStock.quantity = obj.nextInt();
                    obj.nextLine();
                    System.out.print("Enter new Product Type        : ");
                    tempStock.productType = obj.nextLine();
                    System.out.print("Enter new Product Brand       : ");
                    tempStock.brand = obj.nextLine();
                    System.out.print("Enter new Product Color       : ");
                    tempStock.ProductColor = obj.nextLine();
                    System.out.print("Enter new Product Warranty    : ");
                    tempStock.warranty = obj.nextInt();
                }
                //After user completed, assign it to constructor again which input them to file
                productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price, tempStock.quantity, tempStock.productType, tempStock.brand, tempStock.ProductColor, tempStock.warranty));
            }
                fileReader.close();
                if(productFound){
                    try (FileWriter fileWriter = new FileWriter("Stock.txt", false)) {
                        for (StockProducts product : productsList) {
                            fileWriter.write(product.getId()            + "\n");
                            fileWriter.write(product.getName()          + "\n");
                            fileWriter.write(product.getModel()         + "\n");
                            fileWriter.write(product.getPrice()         + "\n");
                            fileWriter.write(product.getQuantity()      + "\n");
                            fileWriter.write(product.getProductType()   + "\n");
                            fileWriter.write(product.getBrand()         + "\n");
                            fileWriter.write(product.getProductColor()  + "\n");
                            fileWriter.write(product.getWarranty()      + "\n");
                        }
                    }
                    System.out.println("Product updated successfully.");
                }else{
                    System.out.println("Product with ID " + product_id + " not found.");
                }

        } catch (IOException e) {
            System.out.println("The file is last dance");
        }
    }
    public void DeleteProduct(){
        ArrayList<StockProducts> productsList = new ArrayList<>();
        TemporaryStock tempStock = new TemporaryStock();
        boolean productFound = false;
        Scanner obj = new Scanner(System.in);

        System.out.print("Enter product ID to delete: ");
        int product_id = obj.nextInt();
        obj.nextLine();

        try{
            File file = new File("Stock.txt");
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                tempStock.id = Integer.parseInt(fileReader.nextLine());
                tempStock.name = fileReader.nextLine();
                tempStock.model = fileReader.nextLine();
                tempStock.price = Double.parseDouble(fileReader.nextLine());
                tempStock.quantity = Integer.parseInt(fileReader.nextLine());
                tempStock.productType = fileReader.nextLine();
                tempStock.brand = fileReader.nextLine();
                tempStock.ProductColor = fileReader.nextLine();
                tempStock.warranty = Integer.parseInt(fileReader.nextLine());

                if (tempStock.id == product_id) {
                    productFound = true;
                    System.out.println("This product gonna disappear now !!!");
                    continue;
                }
                productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price, tempStock.quantity, tempStock.productType, tempStock.brand, tempStock.ProductColor, tempStock.warranty));

            }
            fileReader.close();
            if (productFound) {
                try (FileWriter fileWriter = new FileWriter("Stock.txt", false)) {
                    for (StockProducts product : productsList) {

                        fileWriter.write(product.getId()            + "\n");
                        fileWriter.write(product.getName()          + "\n");
                        fileWriter.write(product.getModel()         + "\n");
                        fileWriter.write(product.getPrice()         + "\n");
                        fileWriter.write(product.getQuantity()      + "\n");
                        fileWriter.write(product.getProductType()   + "\n");
                        fileWriter.write(product.getBrand()         + "\n");
                        fileWriter.write(product.getProductColor()  + "\n");
                        fileWriter.write(product.getWarranty()      + "\n");

                    }
                }
                System.out.println("This Product finally deleted successfully.");
            } else {
                System.out.println("Product with ID " + product_id + " not found.");
            }

        }catch(IOException e){
            System.out.println("The file is last dance");
        }
    }
    public void SearchProduct(){
        ArrayList<StockProducts> productsList = new ArrayList<>();
        TemporaryStock tempStock= new TemporaryStock();
        boolean productFound=false;
        Scanner obj= new Scanner(System.in);
        int product_id;
        System.out.print("Enter product ID: ");
        product_id= obj.nextInt();
        obj.nextLine();

        try {
            File file = new File("Stock.txt");
            Scanner fileReader = new Scanner(file);

            // Loop through the file line by line
            while (fileReader.hasNextLine()) {

                tempStock.id= Integer.parseInt(fileReader.nextLine());
                tempStock.name= fileReader.nextLine();
                tempStock.model= fileReader.nextLine();
                tempStock.price= Double.parseDouble(fileReader.nextLine());
                tempStock.quantity= Integer.parseInt(fileReader.nextLine());
                tempStock.productType= fileReader.nextLine();
                tempStock.brand= fileReader.nextLine();
                tempStock.ProductColor= fileReader.nextLine();
                tempStock.warranty= Integer.parseInt(fileReader.nextLine());


                if (tempStock.id== product_id) {
                    System.out.println("-----------------------------");
                    System.out.println("Product ID              : " + tempStock.id);
                    System.out.println("Product Name            : " + tempStock.name);
                    System.out.println("Product Model           : " + tempStock.model);
                    System.out.println("Product Price           : " + tempStock.price + " $");
                    System.out.println("Product Quantity        : " + tempStock.quantity);
                    System.out.println("Product Type            : " + tempStock.productType);
                    System.out.println("Product Brand           : " + tempStock.brand);
                    System.out.println("Product Color           : " + tempStock.ProductColor);
                    System.out.println("Product Warranty        : " + tempStock.warranty + "  Year");
                    System.out.println("-----------------------------");
                    break;
                }else{
                    System.out.println("THis product Id is not found !!");
                    break;
                }
            }
            fileReader.close();

        } catch (IOException e) {
            System.out.println("The file is last dance");
        }
    }
    public void SortProduct(){
        ArrayList<StockProducts> productsList = new ArrayList<>();
        TemporaryStock tempStock= new TemporaryStock();
        Scanner obj= new Scanner(System.in);
        System.out.println("1. Sort Product by ID");
        System.out.println("2. Sort Product by price");
        System.out.println("3. Sort Product by Product Type");
        System.out.println("4. Exit this block");
        System.out.println("---------------------------------");
        System.out.println("Enter a number");
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
                System.exit(0);
            default:
                System.out.println("Invalid choice !!!");
        }

    }
    public void UpdateAmountProduct(){

    }

    //IT read data from file by one obj by one obj and go thorugh all of it one by one...
    public void DisplayProduct(){
        TemporaryStock tempStock= new TemporaryStock();
        try{
            File file = new File("Stock.txt");
            Scanner fileReader = new Scanner(file);


            if(!fileReader.hasNextLine()) {
                System.out.println("No product in the stock.");
                fileReader.close();
                return;
            }

            while(fileReader.hasNextLine()){

                tempStock.id= Integer.parseInt(fileReader.nextLine());
                tempStock.name= fileReader.nextLine();
                tempStock.model= fileReader.nextLine();
                tempStock.price= Double.parseDouble(fileReader.nextLine());
                tempStock.quantity= Integer.parseInt(fileReader.nextLine());
                tempStock.productType= fileReader.nextLine();
                tempStock.brand= fileReader.nextLine();
                tempStock.ProductColor= fileReader.nextLine();
                tempStock.warranty= Integer.parseInt(fileReader.nextLine());



                // Display the product details
                System.out.println("---------------------------------");
                System.out.println("Product ID              : " + tempStock.id);
                System.out.println("Product Name            : " + tempStock.name);
                System.out.println("Product Model           : " + tempStock.model);
                System.out.println("Product Price           : " + tempStock.price + " $");
                System.out.println("Product Quantity        : " + tempStock.quantity);
                System.out.println("Product Type            : " + tempStock.productType);
                System.out.println("Product Brand           : " + tempStock.brand);
                System.out.println("Product Color           : " + tempStock.ProductColor);
                System.out.println("Product Warranty        : " + tempStock.warranty + "  Year");
                System.out.println("---------------------------------");
            }
            fileReader.close();
        }catch (IOException e){
            System.out.println("File not found !");
        }
    }
    public void SortByPrice(){
        Scanner obj= new Scanner(System.in);
        int choice;
        System.out.println("1. Sort By Ascending");
        System.out.println("2. Sort By Descending");
        System.out.println("Choose a number");
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
    public void Ascending(){
        ArrayList<StockProducts> productsList = new ArrayList<>();
        TemporaryStock tempStock= new TemporaryStock();
        Scanner obj= new Scanner(System.in);
        try{
            File file = new File("Stock.txt");
            Scanner fileReader = new Scanner(file);

            if(!fileReader.hasNextLine()) {
                System.out.println("No product in the stock.");
                fileReader.close();
                return;
            }

            while(fileReader.hasNextLine()){

                tempStock.id= Integer.parseInt(fileReader.nextLine());
                tempStock.name= fileReader.nextLine();
                tempStock.model= fileReader.nextLine();
                tempStock.price= Double.parseDouble(fileReader.nextLine());
                tempStock.quantity= Integer.parseInt(fileReader.nextLine());
                tempStock.productType= fileReader.nextLine();
                tempStock.brand= fileReader.nextLine();
                tempStock.ProductColor= fileReader.nextLine();
                tempStock.warranty= Integer.parseInt(fileReader.nextLine());

                boolean isDuplicate = false;
                for (StockProducts product : productsList) {
                    if (product.getId() == tempStock.id) {
                        isDuplicate = true;
                        break;
                    }
                }

                // If not duplicate, add to the list
                if (!isDuplicate) {
                    productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price, tempStock.quantity, tempStock.productType, tempStock.brand, tempStock.ProductColor, tempStock.warranty));
                }
            }
            fileReader.close();
            Collections.sort(productsList, Comparator.comparingDouble(StockProducts::getPrice));

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

        }catch (IOException e){
            System.out.println("File not found !");
        }

    }
    public void Descending(){
        ArrayList<StockProducts> productsList = new ArrayList<>();
        TemporaryStock tempStock= new TemporaryStock();
        Scanner obj= new Scanner(System.in);

        try{
            File file = new File("Stock.txt");
            Scanner fileReader = new Scanner(file);

            if(!fileReader.hasNextLine()) {
                System.out.println("No product in the stock.");
                fileReader.close();
                return;
            }

            while(fileReader.hasNextLine()){

                tempStock.id= Integer.parseInt(fileReader.nextLine());
                tempStock.name= fileReader.nextLine();
                tempStock.model= fileReader.nextLine();
                tempStock.price= Double.parseDouble(fileReader.nextLine());
                tempStock.quantity= Integer.parseInt(fileReader.nextLine());
                tempStock.productType= fileReader.nextLine();
                tempStock.brand= fileReader.nextLine();
                tempStock.ProductColor= fileReader.nextLine();
                tempStock.warranty= Integer.parseInt(fileReader.nextLine());
                boolean isDuplicate = false;
                for (StockProducts product : productsList) {
                    if (product.getId() == tempStock.id) {
                        isDuplicate = true;
                        break;
                    }
                }

                // If not duplicate, add to the list
                if (!isDuplicate) {
                    productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price, tempStock.quantity, tempStock.productType, tempStock.brand, tempStock.ProductColor, tempStock.warranty));
                }

            }
            fileReader.close();
            //It takes obj product to store temp in productList
            //It put obj product to compare by price with library Collections.sort()
            Collections.sort(productsList, Comparator.comparingDouble(StockProducts::getPrice).reversed());

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

        }catch (IOException e){
            System.out.println("File not found !");
        }
    }
    public void SortByID(){
        ArrayList<StockProducts> productsList = new ArrayList<>();
        TemporaryStock tempStock= new TemporaryStock();
        try{
            File file = new File("Stock.txt");
            Scanner fileReader = new Scanner(file);

            if(!fileReader.hasNextLine()) {
                System.out.println("No product in the stock.");
                fileReader.close();
                return;
            }

            while(fileReader.hasNextLine()){

                tempStock.id= Integer.parseInt(fileReader.nextLine());
                tempStock.name= fileReader.nextLine();
                tempStock.model= fileReader.nextLine();
                tempStock.price= Double.parseDouble(fileReader.nextLine());
                tempStock.quantity= Integer.parseInt(fileReader.nextLine());
                tempStock.productType= fileReader.nextLine();
                tempStock.brand= fileReader.nextLine();
                tempStock.ProductColor= fileReader.nextLine();
                tempStock.warranty= Integer.parseInt(fileReader.nextLine());


                boolean duplicateFound = false;
                // Check if the product already exists in the list by matching product ID
                for (StockProducts product : productsList) {
                    if (product.getId() == tempStock.id) {
                        duplicateFound = true;
                        break;
                    }
                }

                if (!duplicateFound) {
                    // Add product to list if not a duplicate
                    productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price, tempStock.quantity, tempStock.productType, tempStock.brand, tempStock.ProductColor, tempStock.warranty));
                }

            }
            fileReader.close();
            //It takes obj product to store temp in productList
            //It put obj product to compare by price with library Collections.sort()
            Collections.sort(productsList, Comparator.comparingInt(StockProducts::getId));

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

        }catch (IOException e){
            System.out.println("File not found !");
        }
    }
    public void SortByProductType(){
        ArrayList<StockProducts> productsList = new ArrayList<>();
        TemporaryStock tempStock= new TemporaryStock();
        Scanner obj= new Scanner(System.in);
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
        System.out.println("Choose a number : ");
        choice= obj.nextInt();
        obj.nextLine();

        String productType= switch (choice){
            case 1 ->   "Laptop";
            case 2 ->   "Phone";
            case 3 ->   "Headphone";
            case 4 ->   "Charger";
            case 5 ->   "Adaptor";
            case 6 ->   "Headset";
            case 7 ->   "Speaker";
            case 8 ->   "Keyboard";
            case 9 ->   "Mouse";
            case 10 ->  "MousePad";
            default -> {
                System.out.println("Invalid choice!");
                yield null;
            }
        };
        if (productType == null){
            return;
        }
        try{
            File file = new File("Stock.txt");
            Scanner fileReader = new Scanner(file);

            if(!fileReader.hasNextLine()) {
                System.out.println("No product in the stock.");
                fileReader.close();
                return;
            }

            while(fileReader.hasNextLine()){
                tempStock.id= Integer.parseInt(fileReader.nextLine());
                tempStock.name= fileReader.nextLine();
                tempStock.model= fileReader.nextLine();
                tempStock.price= Double.parseDouble(fileReader.nextLine());
                tempStock.quantity= Integer.parseInt(fileReader.nextLine());
                tempStock.productType= fileReader.nextLine();
                tempStock.brand= fileReader.nextLine();
                tempStock.ProductColor= fileReader.nextLine();
                tempStock.warranty= Integer.parseInt(fileReader.nextLine());

                if (tempStock.productType.equalsIgnoreCase(productType)) {
                    boolean duplicateFound = false;

                    // Check for duplicate based on product ID
                    for (StockProducts product : productsList) {
                        if (product.getId() == tempStock.id) {
                            duplicateFound = true;
                            break;
                        }
                    }

                    if (!duplicateFound) {
                        productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price, tempStock.quantity, tempStock.productType, tempStock.brand, tempStock.ProductColor, tempStock.warranty));
                    }
                }
            }
            fileReader.close();
            Collections.sort(productsList, Comparator.comparingInt(StockProducts::getId));

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
        }catch (IOException e){
            System.out.println("File is a last dance !");
        }
    }


    //    use to store data from user input and point back to constructor
    //    user to store data from file to show

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
}

