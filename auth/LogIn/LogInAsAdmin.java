package auth.LogIn;

import auth.controller.Roles;

public class LogInAsAdmin implements Login {

    public void login(String email, String password, Roles role) {
        System.out.println("Login as Admin successful.");
        System.out.println("Admin: " + email + " & " + password + " role: " + role);
    }

    @Override
    public void login(Roles role) {

    }

}
