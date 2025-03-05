package Admin.Control.GUI.ManageProducts.DisplayProducts;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DisplayControler {
    private Stage stage;
    private Scene scene;
    private Parent root;

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
    
}
