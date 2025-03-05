package Admin.Control.Function;

public class StockProducts{
    private int id;
    private String name;
    private String model;
    private double price;
    private int quantity;
    private String productType;
    private String brand;
    private String ProductColor;
    private int warranty;


    //default constructor
    public StockProducts(){
        this.id = 0;
        this.name = "";
        this.model= "";
        this.price = 0;
        this.quantity = 0;
        this.productType= "";
        this.warranty= 0;
        this.brand= "";
        this.ProductColor= "";
    }

    public StockProducts(int id, String name,String model, double price, int quantity, String productType,String brand,String ProductColor,int warranty){
        this.id = id;
        this.name = name;
        this.model= model;
        this.price = price;
        this.quantity = quantity;
        this.productType= productType;
        this.warranty= warranty;
        this.brand= brand;
        this.ProductColor= ProductColor;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name= name;
    }
    public String getBrand(){
        return brand;
    }

    public void setBrand(String Brand){
        this.brand= Brand;
    }
    public String getProductColor(){
        return ProductColor;
    }

    public void setProductColor(String productColor){
        this.ProductColor= ProductColor;
    }

    public String getModel(){
        return model;
    }

    public void setModel(String model){
        this.model= model;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id= id;
    }
    public int getWarranty(){
        return warranty;
    }

    public void setWarranty(int warranty){
        this.warranty= warranty;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity= quantity;
    }

    public String getProductType(){
        return productType;
    }

    public void setProductType(String productType){
        this.productType= productType;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price= price;
    }
}
