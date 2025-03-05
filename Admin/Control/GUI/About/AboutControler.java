package Admin.Control.GUI.About;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutControler { // Corrected class name to MainController
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToProductsPage(javafx.event.ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin/Control/GUI/Products/Products.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Prints the stack trace for debugging
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

    public void switchToManageProducts(javafx.event.ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin/Control/GUI/ManageProducts/AddProduct/AddProduct.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToPayments(javafx.event.ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin/Control/GUI/Payments/Payments.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToAbout(javafx.event.ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin/Control/GUI/About/About.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
