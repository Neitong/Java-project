package User.model;

public class Product {
    private int productId;
    private String name;
    private double price;
    private String description;
    private String category;
    private String imageUrl;

    public Product(String name, double price, String description, String category, String imageUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Product(int productId, String name, double price, String description, String category, String imageUrl) {
        this(name, price, description, category, imageUrl);
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return String.format("ID: %d - %s - $%.2f - %s", productId, name, price, description);
    }
}