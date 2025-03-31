package auth.controller;

public class Guest extends Auth {
    public Guest() {
        super();
    }

    @Override
    public void display() {
        System.out.println("Guest Dashboard");
    }
}
