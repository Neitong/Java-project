package Admin.Control;
import java.io.File;
import  java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

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


                //It put value back to constructor to specifies all the parameter
                StockProducts products = new StockProducts(tempStock.id, tempStock.name, tempStock.model, tempStock.price, tempStock.quantity, tempStock.productType);
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
        ArrayList<StockProducts> updataProduct= new ArrayList<>();

    }
    public void DeleteProduct(){

    }
    public void SearchProduct(){

    }
    public void SortProduct(){

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


                // Display the product details
                System.out.println("---------------------------------");
                System.out.println("Product ID          : " + tempStock.id);
                System.out.println("Product Name        : " + tempStock.name);
                System.out.println("Product Model        : " + tempStock.model);
                System.out.println("Product Price       : " + tempStock.price + " $");
                System.out.println("Product Quantity    : " + tempStock.quantity);
                System.out.println("Product Type        : " + tempStock.productType);
                System.out.println("---------------------------------");
            }
            fileReader.close();
        }catch (IOException e){
            System.out.println("File not found !");
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
    }
}

