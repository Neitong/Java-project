package Admin.Control.Function;
import java.util.Scanner;

import static Admin.Control.Function.TrackHistory.getAllAdminActions;

public class AdminInterface {

    private String AdminPassword= "Jack";
    private String AdminEmail = "jack@gmail.com";


    public static void main(String[] args) {
        Scanner obj= new Scanner(System.in);

        ManageProducts invoke= new ManageProducts();

        System.out.println("\t\t\tWELCOME TO ADMIN BLOCK");

        while(true){
            System.out.println("-----------------------------");
            System.out.println("1. Add Products to Stock");
            System.out.println("2. Update Products");
            System.out.println("3. Display All Products");
            System.out.println("4. Delete Products");
            System.out.println("5. Search Products");
            System.out.println("6. Sorts Products");
            System.out.println("7. Track History");
            System.out.println("8. Exit the block");
            System.out.print("Choose number:");
            int n= obj.nextInt();
            obj.nextLine();

            switch (n){
                case 1:
                    invoke.AddProduct();
                    break;
                case 2:
                    invoke.UpdateProduct();
                    break;
                case 3:
                    invoke.DisplayProduct();
                    break;
                case 4:
                    invoke.DeleteProduct();
                    break;
                case 5:
                    invoke.SearchProduct();
                    break;
                case 6:
                    invoke.SortProduct();
                    break;
                case 7:
                    getAllAdminActions();
                    break;
                case 8:
                    System.out.println("Exit the program");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }

    }
}
