package User.model;

public class Product {
    private int id;
    private int productId;
    private String name;
    private double price;
    private String description;
    private String category;
    private String imageUrl;
    private int Quantity;
    private String model;
    private String productType;
    private String brand;
    private String productColor;
    private int warrenty;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Product(String name, double price, String description, String category, String imageUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Product(int productId, String name, String model, double price, String productType,
               String brand, String productColor, int warranty, int Quantity) {
    // Constructor implementation
        this.productId = productId;
        this.name = name;
        this.model = model;
        this.price = price;
        this.productType = productType;
        this.brand = brand;
        this.productColor = productColor;
        this.warrenty = warranty;
        this.Quantity = Quantity;
    }

    public Product(int productId, String name, double price, String description, String category, String imageUrl) {
        this(name, price, description, category, imageUrl);
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product(int id, String name, double price) {
        this.productId = id;
        this.name = name;
        this.price = price;
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("ID: %d - %s - $%.2f - %s", productId, name, price, description);
    }
}