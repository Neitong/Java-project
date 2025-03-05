package Admin.Control.GUI.ManageProducts.SortProduct;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SortControler {
    private Stage stage;
    private Scene scene;
    private Parent root;

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
}
