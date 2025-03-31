package auth.LogIn;

import auth.controller.Roles;

public class LogInAsUser {
    public void login(String email, String password, Roles role) {
        System.out.println("Login as user successfully");
        System.out.println("User: " + email + " " + password);

    }
}
