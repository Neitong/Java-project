package auth.controller.role;

import Admin.Control.Function.ManageProducts;
import auth.controller.AdminAccountManager;
import auth.controller.Authentication;


import java.util.Scanner;

import static Admin.Control.Function.TrackHistory.getAllAdminActions;

public class Admin {
    private String username;
    private String email;
    private String password;
    private Roles role;

    public Admin(String username, String email, String password, Roles role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }


    //override from abstract methods
    public void superAdminDisplay() {
        Scanner input = new Scanner(System.in);
        ManageProducts products = new ManageProducts();
        System.out.println("Super Admin Dashboard for " + this.getUsername() + "Roles: " + this.getRole());
        while(true){
            System.out.println("-----------------------------");
            System.out.println("1. Add Products to Stock");
            System.out.println("2. Update Products");
            System.out.println("3. Display All Products");
            System.out.println("4. Delete Products");
            System.out.println("5. Search Products");
            System.out.println("6. Sorts Products");
            System.out.println("7. Track History");
            System.out.println("8. Create Admin Account");
            System.out.println("9. Exit the block");
            System.out.print("Choose number:");
            int n= input.nextInt();
            input.nextLine();

            switch (n){
                case 1:
                    products.AddProduct();
                    break;
                case 2:
                    products.UpdateProduct();
                    break;
                case 3:
                    products.DisplayProduct();
                    break;
                case 4:
                    products.DeleteProduct();
                    break;
                case 5:
                    products.SearchProduct();
                    break;
                case 6:
                    products.SortProduct();
                    break;
                case 7:
                    getAllAdminActions();
                    break;
                case 8:
                    //Create Admin Account
                    AdminAccountManager.viewAdminAccount(input);
                    break;
                case 9:
                    System.out.println("Exit the program");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void adminDisplay() {
        Scanner input = new Scanner(System.in);
        ManageProducts products = new ManageProducts();
        System.out.println("Admin Dashboard for " + this.getUsername() + "Roles: " + this.getRole());
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
            int n= input.nextInt();
            input.nextLine();

            switch (n){
                case 1:
                    products.AddProduct();
                    break;
                case 2:
                    products.UpdateProduct();
                    break;
                case 3:
                    products.DisplayProduct();
                    break;
                case 4:
                    products.DeleteProduct();
                    break;
                case 5:
                    products.SearchProduct();
                    break;
                case 6:
                    products.SortProduct();
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
