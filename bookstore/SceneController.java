/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import user.*;

/**
 *
 * @author Hao
 */
public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static User currentuser;
    //public static ArrayList<Books> bookStock = new ArrayList<>();
    
    public void switchScene(ActionEvent event, String fxmlScene) throws IOException{
        root = FXMLLoader.load(getClass().getResource(fxmlScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
