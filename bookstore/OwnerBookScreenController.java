/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import user.*;
import javafx.scene.control.TextField;  
import javafx.scene.control.Button;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * FXML Controller class
 *
 * @author nsmut
 */
public class OwnerBookScreenController extends SceneController implements Initializable {

    
    @FXML
    private TableView<Book> Table;
      @FXML
    private TableColumn<Book,String > BookTitle;

    @FXML
    private TableColumn<Book, Double> Price;

     @FXML
    private Button AddButton;
     
    @FXML
    private TextField PriceField;
    
     @FXML
    private TextField TitleField;
     
     @FXML
    private Button DELETEBUTTON;
     
       @FXML
    private Button BACK;
    
    /**
     * Initializes the controller class.
     */
    ObservableList<Book> BookListDisplay = FXCollections.observableArrayList(
   
            );
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        try {
            // TODO
            read();
        } catch (IOException ex) {
            Logger.getLogger(OwnerBookScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        BookTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<Book,Double>("Price"));
        
        
        Table.setItems(BookListDisplay);
         AddButton.setOnAction(this::handle);
         DELETEBUTTON.setOnAction(this::handleDelete);
        // BACK.setOnAction(this::backButton);
    } 
    
   @FXML
    private void backButton(ActionEvent ev) throws IOException{
        switchScene(ev, "OwnerStartScreen.fxml");
    }      
       
        private void handle(ActionEvent t){
        
        BookListDisplay.add(new Book(TitleField.getText(), Double.parseDouble(PriceField.getText())));
        TitleField.clear();
        PriceField.clear();
        
        try {
            write();
        } catch (IOException ex) {
            Logger.getLogger(OwnerBookScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        private void handleDelete(ActionEvent t){
    BookListDisplay.remove(Table.getSelectionModel().getSelectedItem());
        try {
            write();
        } catch (IOException ex) {
            Logger.getLogger(OwnerBookScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
       
        
        private void write() throws IOException{
            
            
FileWriter writer = new FileWriter("src/user/books.txt"); 
for(int i = 0; i <BookListDisplay.size(); i++) {

   
 writer.write(BookListDisplay.get(i).getName() + "\t" +BookListDisplay.get(i).getPrice() + System.lineSeparator());
}
writer.close();

}
        
        private void read()throws IOException{
            BufferedReader reader = new BufferedReader(new FileReader("src/user/books.txt"));
            
            String line;
           String store="";
           double holdDouble=0;
            while((line = reader.readLine()) != null  ){
               
                String[] temp = line.split("\t");
                
                 for (String a : temp){
                    try
                        {
                          Double.parseDouble(a);
                         holdDouble = Double.parseDouble(a);
                        }
                        catch(NumberFormatException e)
                        {
                         store = a;
                        }
                     
                     
                     
                     
                     
                 }  
                 BookListDisplay.add(new Book(store,holdDouble));
                
            }
            
            
        }
        
    }
    


    
    
   

