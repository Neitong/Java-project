package addtocart.src.com.shopping.service;

public class DiscountManager {
    private double discountPercent;

    public DiscountManager() {
        this.discountPercent = 0.0;
    }

    public boolean applyDiscount(double percentOff) {
        if (percentOff < 0 || percentOff > 100) {
            return false;
        }
        this.discountPercent = percentOff;
        return true;
    }

    public void clearDiscount() {
        this.discountPercent = 0.0;
    }

    public double calculateDiscount(double amount) {
        return amount * (discountPercent / 100.0);
    }

    public double getDiscountPercent() {
        return discountPercent;
    }
}