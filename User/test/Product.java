package User.test;

public class Product {
    // Fields (attributes)
    private String name;
    private double price;
    private String description;
    private String category;
    private String imageUrl;

    // Constructor to initialize the Product object
    public Product(String name, double price, String description, String category, String imageUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters (for retrieving and updating attributes)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Override toString method for easy printing of product details
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + ", description='" + description + "', category='" + category + "', imageUrl='" + imageUrl + "'}";
    }
}
