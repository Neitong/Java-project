import java.util.Scanner;

import java.lang.*;
import java.io.*;
import java.util.*;

import auth.controller.Login;
import auth.controller.Register;

public class app {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("\n**** E-Commerce Authentication System ****");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try{
                int choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 1:
                        Login.performLogin(input);
                        break;
                    case 2:
                        Register.performRegistration(input);
                        break;
                    case 3:
                        System.out.println("Exiting system...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }catch (Exception e){
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
