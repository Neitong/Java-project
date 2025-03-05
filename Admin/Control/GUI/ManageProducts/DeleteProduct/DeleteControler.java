package Admin.Control.GUI.ManageProducts.DeleteProduct;

import Admin.Control.Function.ManageProducts;
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

public class DeleteControler {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private Label ProductID;
    @FXML private Label ProductName;
    @FXML private Label ProductModel;
    @FXML private Label ProductPrice;
    @FXML private Label ProductQuantity;
    @FXML private Label ProductType;
    @FXML private Label ProductColor;
    @FXML private Label ProductBrand;
    @FXML private Label ProductWarranty;


    @FXML private TextField IdSearch;

    @FXML private Label sucessTemp;
    @FXML private Button hidebtnDelete;
    @FXML private VBox HideDeleteBlock;

    public void initialize() {
        HideDeleteBlock.setVisible(false);
        hidebtnDelete.setVisible(false);
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

    public void searchProduct (ActionEvent event) {
        if (IdSearch.getText().isEmpty()) {
            return;
        }

        try {
            int idSearch = Integer.parseInt(IdSearch.getText());
            ArrayList<StockProducts> foundProducts = ManageProducts.SearchProduct(idSearch);

            if (!foundProducts.isEmpty()) {
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

                HideDeleteBlock.setVisible(true);
                hidebtnDelete.setVisible(true);
            } else {
                sucessTemp.setText("No Product Found");
                clearProductInfo();
                HideDeleteBlock.setVisible(false);
                hidebtnDelete.setVisible(false);
            }

        } catch (NumberFormatException e) {
            sucessTemp.setText("No Product Found");
            clearProductInfo();
            HideDeleteBlock.setVisible(false);
            hidebtnDelete.setVisible(false);
        } catch (Exception e) {
            sucessTemp.setText("No Product Found");
            clearProductInfo();
            HideDeleteBlock.setVisible(false);
            hidebtnDelete.setVisible(false);
        }
    }


    private void clearProductInfo() {
        ProductID.setText("Product ID             :     ");
        ProductName.setText("Product Name         :     ");
        ProductModel.setText("Product Model       :     ");
        ProductPrice.setText("Product Price       :     ");
        ProductQuantity.setText("Product Quantity :     ");
        ProductType.setText("Product Type         :     ");
        ProductBrand.setText("Product Brand       :     ");
        ProductColor.setText("Product Color       :     ");
        ProductWarranty.setText("Product Warranty :     ");
    }

    public void DeleteProduct(ActionEvent event) {
        clearProductInfo();
        if (IdSearch.getText().isEmpty()) {
            return;
        }
        try {
            int idSearch = Integer.parseInt(IdSearch.getText());
            ArrayList<StockProducts> foundProducts = ManageProducts.SearchProduct(idSearch);

            if (!foundProducts.isEmpty()) {
                ManageProducts.DeleteProduct(idSearch);
                clearProductInfo();
                sucessTemp.setText("Product Deleted Successfully");
                HideDeleteBlock.setVisible(false);
            } else {
                sucessTemp.setText("No Product Found");
            }

        } catch (Exception e) {
            sucessTemp.setText("Can not Delete Product");
            clearProductInfo();
        }
    }

}
