package auth.LogIn;

import auth.controller.Roles;

public class LogInAsGuest implements Login {


    public void login(Roles role) {
        System.out.println("Login as guest successfully");
        System.out.println("Login as " + role);
    }

    @Override
    public void login(String email, String password, Roles role ) {}






}
