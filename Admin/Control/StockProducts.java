package Admin.Control;

public class StockProducts{
    private int id;
    private String name;
    private String model;
    private double price;
    private int quantity;
    private String productType;



    public StockProducts(int id, String name,String model, double price, int quantity, String productType){
        this.id = id;
        this.name = name;
        this.model= model;
        this.price = price;
        this.quantity = quantity;
        this.productType= productType;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name= name;
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
