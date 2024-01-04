/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Hao
 */
public class OwnerStartScreenController extends SceneController implements Initializable {
    
    
    @FXML
    public void changeToOwnerBooksScreen(ActionEvent ev) throws Exception{
        switchScene(ev, "OwnerBookScreen.fxml");
    }
    
    @FXML
    public void changeToOwnerCustomersScreen(ActionEvent ev) throws Exception{
        switchScene(ev, "OwnerCustomersScreen.fxml");
    }
    
    @FXML
    public void changeToLoginScreen(ActionEvent ev) throws Exception{
        switchScene(ev, "LoginScreen.fxml");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
