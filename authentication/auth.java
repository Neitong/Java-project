import java.util.Scanner;


public class auth {
    private String username;
    private String password;
    private String roles;
    // private String email;
    // private String phone;
    // private String address;
    
    public auth(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    
    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public void setterUsername(String username) {
        this.username = username;
    }
    
    public void setterPassword(String password) {
        this.password = password;
    }
    
    public void setterRoles(String roles) {
        this.roles = roles;
    }


    public static void main(String[] args) {
        auth user = new auth("admin", "123", "admin");
        Scanner input = new Scanner(System.in);
        
            // System.out.println("Enter your username: ");
            // String setUsername = System.console().readLine();
            // System.out.println("Enter your password: ");
            // String setPassword = System.console().readLine();

            System.out.println("Enter your username: ");
            String setUsername = input.nextLine();
            System.out.println("Enter your password: ");
            String setPassword = input.nextLine();
            if(setUsername.equals(user.getUsername()) && setPassword.equals(user.getPassword())){
                System.out.println("Welcome " + user.username +". You're " +user.getRoles());
            } else {
                System.out.println("Invalid username or password.");
            }
            
       
        
    }
}
