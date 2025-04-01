package auth.controller.role;

import auth.controller.*;
import auth.controller.Authentication;

import java.util.Scanner;


public class User {
    private String username;
    private String email;
    private String password;
    private Roles role;


    public User(String username,String email, String password, Roles role) {
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

    public void display() {
        System.out.println("User Dashboard for " + getUsername());

    }
}
