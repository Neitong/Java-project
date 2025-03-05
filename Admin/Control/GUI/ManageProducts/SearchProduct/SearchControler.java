package Admin.Control.GUI.ManageProducts.SearchProduct;

import Admin.Control.Function.ManageProducts;
import Admin.Control.Function.StockProducts;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class SearchControler {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML public TextField IdSearch;
    @FXML public Label ProductID;
    @FXML public Label ProductName;
    @FXML public Label ProductPrice;
    @FXML public Label ProductModel;
    @FXML public Label ProductQuantity;
    @FXML public Label ProductType;
    @FXML public Label ProductColor;
    @FXML public Label ProductBrand;
    @FXML public Label ProductWarranty;
    @FXML public Label resultBlock;

    public void loadAdd(javafx.event.ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin/Control/GUI/ManageProducts/AddProduct/AddProduct.fxml")); // Fixed path
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Debugging output
        }
    }

    public void loadUpdate(javafx.event.ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin/Control/GUI/ManageProducts/UpdateProduct/UpdateProduct.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSearch(javafx.event.ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin/Control/GUI/ManageProducts/SearchProduct/SearchProduct.fxml")); // Fixed path
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSort(javafx.event.ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin/Control/GUI/ManageProducts/SortProduct/SortProduct.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDelete(javafx.event.ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin/Control/GUI/ManageProducts/DeleteProduct/DeleteProduct.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDisplay(javafx.event.ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin/Control/GUI/ManageProducts/DisplayProducts/DisplayProducts.fxml")); // Fixed path
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToMainPage(javafx.event.ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin/Control/GUI/MainPage/Main.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void searchProduct(javafx.event.ActionEvent event) throws IOException {


        if (IdSearch.getText().isEmpty()) {
            resultBlock.setText("No Product Found");
            return;
        }
        try {
            int idSearch = Integer.parseInt(IdSearch.getText());
            ArrayList<StockProducts> foundProducts = ManageProducts.SearchProduct(idSearch);

            if(!foundProducts.isEmpty()){
                StockProducts product = foundProducts.get(0);

                ProductID.setText("Product ID             :     " + product.getId());
                ProductName.setText("Product Name         :     " + product.getName());
                ProductModel.setText("Product Model       :     " + product.getModel());
                ProductPrice.setText("Product Price       :     " + product.getPrice());
                ProductQuantity.setText("Product Quantity :     " + product.getQuantity());
                ProductType.setText("Product Type         :     " + product.getProductType());
                ProductBrand.setText("Product Brand       :     " + product.getBrand());
                ProductColor.setText("Product Color       :     " + product.getProductColor());
                ProductWarranty.setText("Product Warranty :     " + product.getWarranty());

                IdSearch.setText("");
            }else{
                resultBlock.setText("No Product Found");
                ClearValue();
            }

        }catch (NumberFormatException e) {
            resultBlock.setText("Invalid Product ID");
            ClearValue();
        } catch (Exception e) {
            resultBlock.setText("Error occurred");
            ClearValue();
        }
    }
    public void ClearValue(){
        ProductID.setText("Product ID             :     " );
        ProductName.setText("Product Name         :     " );
        ProductModel.setText("Product Model       :     " );
        ProductPrice.setText("Product Price       :     " );
        ProductQuantity.setText("Product Quantity :     " );
        ProductType.setText("Product Type         :     " );
        ProductBrand.setText("Product Brand       :     " );
        ProductColor.setText("Product Color       :     ");
        ProductWarranty.setText("Product Warranty :     " );
    }

}
