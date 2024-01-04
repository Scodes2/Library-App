/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import user.*;

/**
 *
 * @author Hao
 */
public class Bookstore extends Application {
    
    //CustomersFile custFile = CustomersFile.getInstance();
    
    @Override
    public void start(Stage stage) throws Exception {
        //custFile.read();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("/bookstore/bookstoreLogo.jpg"));
            stage.getIcons().add(icon);
            stage.setTitle("Bookstore App Lane 38");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
