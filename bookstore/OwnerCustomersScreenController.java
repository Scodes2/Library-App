/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import user.*;

/**
 * FXML Controller class
 *
 * @author Hao
 */
public class OwnerCustomersScreenController extends SceneController implements Initializable {
    
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private TableView<Customer> viewCustomers;
    @FXML
    private Label label;
    @FXML
    private TableColumn<Customer, String> usernameColumn;
    @FXML
    private TableColumn<Customer, String> passwordColumn;
    @FXML
    private TableColumn<Customer, String> pointsColumn;
    
    
    private ObservableList<Customer> obtainList(){
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try{
            RandomAccessFile locRead = new RandomAccessFile("./src/user/customers.txt", "r");
            String[] info;
            String examineLine;
            while((examineLine = locRead.readLine()) != null){
                info = examineLine.split("\t");
                if(info[0] != null || !info[0].isEmpty() || !info[0].equals("")){
                    customerList.add(new Customer(info[0], info[1], info[2] , Integer.parseInt(info[3])));
                }
            }
            locRead.close();
        }
        catch(IOException | NumberFormatException err){
            err.printStackTrace();
        }
        return customerList;
    }
    
    @FXML
    private void addCustomer(ActionEvent ev){
        boolean existingUser = false;
        try{
            RandomAccessFile locRead = new RandomAccessFile("./src/user/customers.txt", "rw");
            String[] info;
            String examineLine;
            while((examineLine = locRead.readLine()) != null){
                info = examineLine.split("\t");
                if(usernameInput.getText().trim().equals("admin") || usernameInput.getText().equals(info[0]) ||
                        usernameInput.getText().equals("") || (passwordInput.getText().trim().equals(""))){
                    existingUser = true;
                    usernameInput.clear();
                    passwordInput.clear();
                    label.setText("Invalid username/password");
                }
            }
            if(!existingUser){
                try{
                    locRead.seek(locRead.length());
                    String inputFormat = String.format(usernameInput.getText() + "\t" + passwordInput.getText() + "\t" + usernameInput.getText() + "\t" + "0\n");
                    locRead.writeBytes(inputFormat);
                    locRead.close();
                    usernameInput.clear();
                    passwordInput.clear();
                    viewCustomers.setItems(obtainList());
                }
                catch(Exception err){
                    err.printStackTrace();
                }
            }
        }
        catch(Exception err){
            err.printStackTrace();
        }
    }
    
    @FXML
    private void deleteCustomer(ActionEvent ev) throws IOException{
        if(viewCustomers.getSelectionModel().getSelectedItem() != null){
            Customer deletingUser = viewCustomers.getSelectionModel().getSelectedItem();
            viewCustomers.getItems().removeAll(viewCustomers.getSelectionModel().getSelectedItem());
            
            File file = new File("./src/user/customers.txt");
            File filetemp = new File("./src/user/tempCustomers.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filetemp));
            
            String toDelete = deletingUser.getUserName();
            String examineLine;
            
            while((examineLine = reader.readLine()) != null){
                String[] info = examineLine.trim().split("\t");
                if(info[0].equals(toDelete)){
                    continue;
                }
                writer.write(examineLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            try{
                Files.copy(Paths.get("./src/user/tempCustomers.txt"), Paths.get("./src/user/customers.txt"), StandardCopyOption.REPLACE_EXISTING);
            }
            catch(IOException err){
                err.printStackTrace();
            }
        }
        viewCustomers.setItems(obtainList());
    }
    
    @FXML
    private void backButton(ActionEvent ev) throws IOException{
        switchScene(ev, "OwnerStartScreen.fxml");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("passWord"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        viewCustomers.setItems(obtainList());
    }    
    
}
