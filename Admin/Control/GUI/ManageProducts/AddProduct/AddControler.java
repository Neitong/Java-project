package Admin.Control.GUI.ManageProducts.AddProduct;

import Admin.Control.Function.ManageProducts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class AddControler { // Fixed class name
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private TextField productIdField;
    @FXML private TextField productNameField;
    @FXML private TextField productModelField;
    @FXML private TextField productPriceField;
    @FXML private TextField productQuantityField;
    @FXML private TextField productTypeField;
    @FXML private TextField productColorField;
    @FXML private TextField productBrandField;
    @FXML private TextField productWarrantyField;
    @FXML
    private Label successLabel;

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
//    public void AddNewProduct(javafx.event.ActionEvent event) throws IOException {
//
//
//        if (productIdField.getText().isEmpty() || productNameField.getText().isEmpty()
//                || productModelField.getText().isEmpty() || productPriceField.getText().isEmpty()
//                || productQuantityField.getText().isEmpty() || productTypeField.getText().isEmpty()
//                || productColorField.getText().isEmpty() || productBrandField.getText().isEmpty()
//                || productWarrantyField.getText().isEmpty()) {
//
//            successLabel.setText("The Product Added Failed!!!");
//            return;
//        }
//
//        try {
//            ManageProducts.TemporaryStock tempStock  = new ManageProducts.TemporaryStock();
//            tempStock.id = Integer.parseInt(productIdField.getText());
//            tempStock.name = productNameField.getText();
//            tempStock.model = productModelField.getText();
//            tempStock.price = Double.parseDouble(productPriceField.getText());
//            tempStock.quantity = Integer.parseInt(productQuantityField.getText());
//            tempStock.productType = productTypeField.getText();
//            tempStock.brand = productBrandField.getText();
//            tempStock.ProductColor = productColorField.getText();
//            tempStock.warranty = Integer.parseInt(productWarrantyField.getText());
//
//            ManageProducts.AddProduct(tempStock);
//
//            successLabel.setText("Product Add Successful!!!");
//
//            productIdField.clear();
//            productNameField.clear();
//            productModelField.clear();
//            productPriceField.clear();
//            productQuantityField.clear();
//            productTypeField.clear();
//            productBrandField.clear();
//            productColorField.clear();
//            productWarrantyField.clear();
//
//        }catch (NumberFormatException e) {
//            successLabel.setText("The Product ID Entered Failed!!!");
//            return;
//        }catch (Exception e) {
//            successLabel.setText("The Product ID Entered Failed!!!");
//            return;
//        }
//    }
}
