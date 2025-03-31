package User.model;

public class CartItem {
    private int productId;
    private int quantity;
    private String productName;
    private double productPrice;
    private Product product;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.productName = product.getName();
        this.productPrice = product.getPrice();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getSubtotal() {
        return productPrice * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s - Price: $%.2f, Quantity: %d, Subtotal: $%.2f",
                product.getName(), product.getPrice(), quantity, getSubtotal());
    }
}