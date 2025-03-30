package auth.controller;


public class Admin extends Auth {
    public Admin(String username, String password, Roles role) {

        //Subclass of Auth's constructor
        super(username, password, role);
    }

    @Override //override from abstract methods
    public void display() {
        System.out.println("Admin Dashboard for " + this.getUsername() + "Roles: " + this.roles);
    }
}
