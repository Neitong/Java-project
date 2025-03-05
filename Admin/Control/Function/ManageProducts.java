package Admin.Control.Function;

import java.io.File;
import  java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;


public class ManageProducts{

    public static void AddProduct(TemporaryStock tempStock) {
        ArrayList<StockProducts> productsList = new ArrayList<>();

        try (FileWriter file = new FileWriter("Stock.txt", true)) {

            StockProducts products = new StockProducts(tempStock.id, tempStock.name, tempStock.model,
                    tempStock.price, tempStock.quantity, tempStock.productType,
                    tempStock.brand, tempStock.ProductColor, tempStock.warranty);

            productsList.add(products);

            for (StockProducts product : productsList) {
                file.write(product.getId() + "\n");
                file.write(product.getName() + "\n");
                file.write(product.getModel() + "\n");
                file.write(product.getPrice() + "\n");
                file.write(product.getQuantity() + "\n");
                file.write(product.getProductType() + "\n");
                file.write(product.getBrand() + "\n");
                file.write(product.getProductColor() + "\n");
                file.write(product.getWarranty() + "\n");
            }

            System.out.println("Product added successfully!");

        } catch (IOException e) {
            System.out.println("No such file");
        }
    }



    public static void UpdateProduct(TemporaryStock updatedProduct) {

        ArrayList<StockProducts> productsList = new ArrayList<>();
        boolean productFound=false;
        Scanner obj= new Scanner(System.in);

        try {
            File file = new File("Stock.txt");
            Scanner fileReader = new Scanner(file);


            while (fileReader.hasNextLine()) {

                TemporaryStock tempStock = new TemporaryStock();
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
                if(tempStock.id == updatedProduct.id) {
                    productsList.add(new StockProducts(updatedProduct.id, updatedProduct.name, updatedProduct.model, updatedProduct.price, updatedProduct.quantity, updatedProduct.productType, updatedProduct.brand, updatedProduct.ProductColor, updatedProduct.warranty));
                }else {
                    productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price, tempStock.quantity, tempStock.productType, tempStock.brand, tempStock.ProductColor, tempStock.warranty));
                }
            }
            fileReader.close();
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
        } catch (IOException e) {
            System.out.println("The file is last dance");
        }
    }





    //not fix yet
    public static void DeleteProduct(int IdSearch) {
        ArrayList<StockProducts> productsList = new ArrayList<>();
        boolean productFound = false;


        try{
            File file = new File("Stock.txt");
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {

                TemporaryStock tempStock = new TemporaryStock();

                tempStock.id = Integer.parseInt(fileReader.nextLine());
                tempStock.name = fileReader.nextLine();
                tempStock.model = fileReader.nextLine();
                tempStock.price = Double.parseDouble(fileReader.nextLine());
                tempStock.quantity = Integer.parseInt(fileReader.nextLine());
                tempStock.productType = fileReader.nextLine();
                tempStock.brand = fileReader.nextLine();
                tempStock.ProductColor = fileReader.nextLine();
                tempStock.warranty = Integer.parseInt(fileReader.nextLine());

                if (tempStock.id == IdSearch) {
                    productFound = true;
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
            }
        }catch(IOException e){
            System.out.println("The file is last dance");
        }
    }


    public static ArrayList<StockProducts> SearchProduct(int IDSearch) {

        ArrayList<StockProducts> productsList = new ArrayList<>();

        try {
            File file = new File("Stock.txt");
            Scanner fileReader = new Scanner(file);

            while(fileReader.hasNextLine()){
                TemporaryStock tempStock = new TemporaryStock();
                tempStock.id= Integer.parseInt(fileReader.nextLine());
                tempStock.name= fileReader.nextLine();
                tempStock.model= fileReader.nextLine();
                tempStock.price= Double.parseDouble(fileReader.nextLine());
                tempStock.quantity= Integer.parseInt(fileReader.nextLine());
                tempStock.productType= fileReader.nextLine();
                tempStock.brand= fileReader.nextLine();
                tempStock.ProductColor= fileReader.nextLine();
                tempStock.warranty= Integer.parseInt(fileReader.nextLine());


                if (IDSearch ==tempStock.id) {
                    productsList.add(new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price, tempStock.quantity, tempStock.productType, tempStock.brand, tempStock.ProductColor, tempStock.warranty));
                }
            }
            fileReader.close();

        } catch (IOException e) {
            System.out.println("The file is last dance");
        }
        return productsList;
    }


    public void SortProduct(){
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





    public static void DisplayProduct(TemporaryStock displayProduct){
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
                displayProduct.id= Integer.parseInt(fileReader.nextLine());
                displayProduct.name= fileReader.nextLine();
                displayProduct.model= fileReader.nextLine();
                displayProduct.price= Double.parseDouble(fileReader.nextLine());
                displayProduct.quantity= Integer.parseInt(fileReader.nextLine());
                displayProduct.productType= fileReader.nextLine();
                displayProduct.brand= fileReader.nextLine();
                displayProduct.ProductColor= fileReader.nextLine();
                displayProduct.warranty= Integer.parseInt(fileReader.nextLine());

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
        boolean duplicateFound;
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
                //if the choice is invalid (doesn't match any case), the method will return null as the value for productType.
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

//                THe reason why I use id to check product also is that
//                It prevent of duplicated product to sort many times
//                By using this it will check the product id which search that the product id is already exist add
//                To productList or not, if not it will add, but if it already exists, it will break

                if (tempStock.productType.equalsIgnoreCase(productType)) {

                    duplicateFound = false;

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

//    public void UpdateAmountProduct(){
//
//    }


    //    use to store data from user input and point back to constructor
    //    user to store data from file to show

    public static class TemporaryStock{
        public int id;
        public String name;
        public String model;
        public double price;
        public int quantity;
        public String productType;
        public int warranty;
        public String brand;
        public String ProductColor;
    }
}

