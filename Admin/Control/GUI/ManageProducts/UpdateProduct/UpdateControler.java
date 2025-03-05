package Admin.Control.GUI.ManageProducts.UpdateProduct;

import Admin.Control.Function.StockProducts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import Admin.Control.Function.ManageProducts;


public class UpdateControler {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private Label oldProductID;
    @FXML private Label oldProductName;
    @FXML private Label oldProductModel;
    @FXML private Label oldProductPrice;
    @FXML private Label oldProductQuantity;
    @FXML private Label oldProductColor;
    @FXML private Label oldProductBrand;
    @FXML private Label oldProductType;
    @FXML private Label oldProductWarranty;

    @FXML private VBox HideBlock;
    @FXML private Label successLabel;
    @FXML private Button HideBtn;





    @FXML private TextField NewProductID;
    @FXML private TextField NewProductName;
    @FXML private TextField NewProductModel;
    @FXML private TextField NewProductPrice;
    @FXML private TextField NewProductQuantity;
    @FXML private TextField NewProductType;
    @FXML private TextField NewProductBrand;
    @FXML private TextField NewProductColor;
    @FXML private TextField NewProductWarranty;
    @FXML private TextField idSearch;


    public void initialize() {
        HideBlock.setVisible(false);
        HideBtn.setVisible(false);
    }

    public void loadAdd(ActionEvent event) {
        loadScene(event, "/Admin/Control/GUI/ManageProducts/AddProduct/AddProduct.fxml");
    }

    public void loadUpdate(ActionEvent event) {
        loadScene(event, "/Admin/Control/GUI/ManageProducts/UpdateProduct/UpdateProduct.fxml");
    }

    public void loadSearch(ActionEvent event) {
        loadScene(event, "/Admin/Control/GUI/ManageProducts/SearchProduct/SearchProduct.fxml");
    }

    public void loadSort(ActionEvent event) {
        loadScene(event, "/Admin/Control/GUI/ManageProducts/SortProduct/SortProduct.fxml");
    }

    public void loadDelete(ActionEvent event) {
        loadScene(event, "/Admin/Control/GUI/ManageProducts/DeleteProduct/DeleteProduct.fxml");
    }

    public void loadDisplay(ActionEvent event) {
        loadScene(event, "/Admin/Control/GUI/ManageProducts/DisplayProducts/DisplayProducts.fxml");
    }

    public void switchToMainPage(ActionEvent event) {
        loadScene(event, "/Admin/Control/GUI/MainPage/Main.fxml");
    }

    private void loadScene(ActionEvent event, String fxmlPath) {
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlPath));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void UpdateProduct(javafx.event.ActionEvent event) throws IOException {
        if (NewProductID.getText().isEmpty() || NewProductName.getText().isEmpty()
                || NewProductModel.getText().isEmpty() || NewProductPrice.getText().isEmpty()
                || NewProductQuantity.getText().isEmpty() || NewProductType.getText().isEmpty()
                || NewProductColor.getText().isEmpty() || NewProductBrand.getText().isEmpty()
                || NewProductWarranty.getText().isEmpty()) {

            successLabel.setText("Update Failed!!!");
            return;
        }
        try{
            ManageProducts.TemporaryStock updatedProduct = new ManageProducts.TemporaryStock();
            updatedProduct.id = Integer.parseInt(NewProductID.getText());
            updatedProduct.name = NewProductName.getText();
            updatedProduct.model = NewProductModel.getText();
            updatedProduct.price = Double.parseDouble(NewProductPrice.getText());
            updatedProduct.quantity = Integer.parseInt(NewProductQuantity.getText());
            updatedProduct.productType = NewProductType.getText();
            updatedProduct.brand = NewProductBrand.getText();
            updatedProduct.ProductColor = NewProductColor.getText();
            updatedProduct.warranty = Integer.parseInt(NewProductWarranty.getText());
            ManageProducts.UpdateProduct(updatedProduct);
            successLabel.setStyle("-fx-text-fill: green;");
            successLabel.setText("Update Successful!!!");

            ClearNewProductInfo();

        }catch (NumberFormatException e) {
            successLabel.setStyle("-fx-text-fill: red;");
            successLabel.setText("Invalid input format!");
        } catch (Exception e) {
            successLabel.setStyle("-fx-text-fill: red;");
            successLabel.setText("Update Failed!!!");
        }
    }
    public void searchProduct(javafx.event.ActionEvent event) throws IOException {

        if (idSearch.getText().isEmpty()) {
            successLabel.setText("No Product Found");
            return;
        }
        try {
            int IDSearch = Integer.parseInt(idSearch.getText());
            ArrayList<StockProducts> foundProducts = ManageProducts.SearchProduct(IDSearch);

            if(!foundProducts.isEmpty()){
                StockProducts product = foundProducts.get(0);
                HideBlock.setVisible(true);
                HideBtn.setVisible(true);

                oldProductID.setText("Product ID             :     " + product.getId());
                oldProductName.setText("Product Name         :     " + product.getName());
                oldProductModel.setText("Product Model       :     " + product.getModel());
                oldProductPrice.setText("Product Price       :     " + product.getPrice());
                oldProductQuantity.setText("Product Quantity :     " + product.getQuantity());
                oldProductType.setText("Product Type         :     " + product.getProductType());
                oldProductBrand.setText("Product Brand       :     " + product.getBrand());
                oldProductColor.setText("Product Color       :     " + product.getProductColor());
                oldProductWarranty.setText("Product Warranty :     " + product.getWarranty());

                idSearch.clear();


            }else{
                HideBlock.setVisible(false);
                HideBtn.setVisible(false);
                successLabel.setStyle("-fx-text-fill: red;");
                successLabel.setText("No Product Found");
                clearOldProductInfo();
            }

        }catch (NumberFormatException e) {
            HideBlock.setVisible(false);
            HideBtn.setVisible(false);
            successLabel.setStyle("-fx-text-fill: red;");
            successLabel.setText("Invalid Product ID");
            clearOldProductInfo();
        } catch (Exception e) {
            HideBlock.setVisible(false);
            HideBtn.setVisible(false);
            successLabel.setStyle("-fx-text-fill: red;");
            successLabel.setText("Error occurred");
            clearOldProductInfo();
        }
    }
    private void clearOldProductInfo() {

        oldProductID.setText("Product ID             :     ");
        oldProductName.setText("Product Name         :     ");
        oldProductModel.setText("Product Model       :     ");
        oldProductPrice.setText("Product Price       :     ");
        oldProductQuantity.setText("Product Quantity :     ");
        oldProductType.setText("Product Type         :     ");
        oldProductBrand.setText("Product Brand       :     ");
        oldProductColor.setText("Product Color       :     ");
        oldProductWarranty.setText("Product Warranty :     ");
    }

    private void ClearNewProductInfo() {

        NewProductID.setText("Product ID             :     ");
        NewProductName.setText("Product Name         :     ");
        NewProductModel.setText("Product Model       :     ");
        NewProductPrice.setText("Product Price       :     ");
        NewProductQuantity.setText("Product Quantity :     ");
        NewProductType.setText("Product Type         :     ");
        NewProductBrand.setText("Product Brand       :     ");
        NewProductColor.setText("Product Color       :     ");
        NewProductWarranty.setText("Product Warranty :     ");
    }


}
