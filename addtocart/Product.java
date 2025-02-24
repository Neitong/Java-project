package com.shopping.model;

public class Product {
    private int id;
    private String name;
    private String model;
    private double price;
    private String productType;
    private String brand;
    private String productColor;
    private int warranty;
    private int stockQuantity;

    public Product(int id, String name, String model, double price, String productType,
                   String brand, String productColor, int warranty, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.price = price;
        this.productType = productType;
        this.brand = brand;
        this.productColor = productColor;
        this.warranty = warranty;
        this.stockQuantity = stockQuantity;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getModel() { return model; }
    public double getPrice() { return price; }
    public String getProductType() { return productType; }
    public String getBrand() { return brand; }
    public String getProductColor() { return productColor; }
    public int getWarranty() { return warranty; }
    public int getStockQuantity() { return stockQuantity; }

    public void decreaseStock(int quantity) {
        this.stockQuantity -= quantity;
    }

    public void increaseStock(int quantity) {
        this.stockQuantity += quantity;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s) - $%.2f [Stock: %d]",
                brand, name, model, price, stockQuantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}