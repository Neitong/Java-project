package auth.controller;

import javax.management.relation.Role;

public class User extends Auth{
    public User(String username, String password, Roles role) {
        super(username, password, role);
    }

    @Override
    public void display() {
        System.out.println("User Dashboard for " + getUsername());
    }
}
