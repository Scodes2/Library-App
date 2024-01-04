/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import user.*;

/**
 * FXML Controller class
 *
 * @author Hao
 */
public class LoginScreenController extends SceneController implements Initializable {
    
    @FXML
    private TextField usernameInput;
    
    @FXML
    private PasswordField passwordInput;
    
    @FXML
    private Label label;
    
    
    public void checkUser(ActionEvent ev) throws IOException{

        if((usernameInput.getText() != null && !usernameInput.getText().isEmpty()) && 
                (passwordInput.getText() != null && !passwordInput.getText().isEmpty()) ){
            if(usernameInput.getText().equals("admin") && passwordInput.getText().equals("admin")){
                try{
                    currentuser = Owner.getInstance();
                    switchScene(ev, "OwnerStartScreen.fxml");
                }
                catch (Exception err){
                    err.printStackTrace();
                }
            }
            else{
                try{
                    RandomAccessFile locRead = new RandomAccessFile("./src/user/customers.txt", "r");
                    String[] info;
                    String examineLine;
                    while((examineLine = locRead.readLine()) != null){
                        info = examineLine.split("\t");
                        if(info[0].equals(usernameInput.getText()) && info[1].equals(passwordInput.getText())){
                            currentuser = new Customer(info[0], info[1], info[2], Integer.parseInt(info[3]));
                            switchScene(ev, "CustomerStartScreen.fxml");
                        }
                    }
                    locRead.close();
                }
                catch(Exception err){
                    err.printStackTrace();
                }
                label.setText("Username/password is incorrect, please try again!");
                usernameInput.clear();
                passwordInput.clear();
            }
        }
        else {
            label.setText("There is no username or password input!");
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}