/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import user.*;


/**
 * FXML Controller class
 *
 * @author Sam
 */
public class CustomerStartScreenController extends SceneController implements Initializable {

    
    private ArrayList<String> bookInventory; 
    
    @FXML
    private TableView<Book> BookTable;
    
    @FXML
    private TableColumn<Book,String > BookTitle;

    @FXML
    private TableColumn<Book, Double> Price;
    
    @FXML
    private TableColumn<Book, CheckBox> selected;
    
    @FXML
    private TableColumn CheckBox = new TableColumn("Select");
 
    @FXML
    private Label Welcome;
   
    @FXML
    private Button Buy;
    
    @FXML
    private Button RedeemPoints;
     
    @FXML
    private Button LogOut;

  
    
    private ObservableList<Book> obtainList(){
        ObservableList<Book> Books = FXCollections.observableArrayList();
        try{
            RandomAccessFile locRead = new RandomAccessFile("./src/user/books.txt", "r");
            String[] info;
            String examineLine;
            while((examineLine = locRead.readLine()) != null){
                info = examineLine.split("\t");
                if(info[0] != null || !info[0].isEmpty() || !info[0].equals("")){
                    Books.add(new Book(info[0], Double.parseDouble(info[1])));

                }
            }
            locRead.close();
        }
        catch(IOException | NumberFormatException err){
            err.printStackTrace();
        }
        return Books;
    }
    
    public void buy(ActionEvent ev) throws Exception{
        boolean chosen = false;
        double total = 0;
        for(int i = 0; i < BookTable.getItems().size(); i++){
            if(BookTable.getItems().get(i).getSelect().isSelected()){
                total += BookTable.getItems().get(i).getPrice();
                chosen = true;
            }
        }
        if(chosen){
            try{
                RandomAccessFile locRead = new RandomAccessFile("./src/user/buyingBooks.txt", "rw");
                FileWriter fileWrite = new FileWriter("./src/user/buyingBooks.txt");
                String inputFormat = String.format("noPoints\t" + total);
                locRead.writeBytes(inputFormat);
                locRead.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            switchScene(ev, "CustomerCostScreen.fxml");  
        }
    }
    
    public void redeemPoints(ActionEvent ev) throws Exception{
        boolean chosen = false;
        double total = 0;
        for(int i = 0; i < BookTable.getItems().size(); i++){
            if(BookTable.getItems().get(i).getSelect().isSelected()){
                total += BookTable.getItems().get(i).getPrice();
                chosen = true;
            }
        }
        if(chosen){
            try{
                RandomAccessFile locRead = new RandomAccessFile("./src/user/buyingBooks.txt", "rw");
                FileWriter fileWrite = new FileWriter("./src/user/buyingBooks.txt");
                String inputFormat = String.format("yesPoints\t" + total);
                locRead.writeBytes(inputFormat);
                locRead.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            switchScene(ev, "CustomerCostScreen.fxml");  
        }
    }
    
    /*
    public void welcome() throws IOException{
       
       RandomAccessFile locRead = new RandomAccessFile("./src/user/customers.txt", "r");
       String[] cust;
       String examineLine;
       while((examineLine = locRead.readLine()) != null){
            cust = examineLine.split("\t");
            if(parseInt(cust[3]) < 1000){   
              Welcome.setText("Welcome " + ((Customer) currentuser).getName() + "." + " You have " + ((Customer) currentuser).getPoints() + " points. Your current status is Silver");
            }else if(parseInt(cust[3]) > 1000){ 
              Welcome.setText("Welcome " + ((Customer) currentuser).getName() + "." + " You have " + ((Customer) currentuser).getPoints() + " points. Your current status is Gold");
            }         
    }
      
     
    }*/
 
    @FXML
    public boolean addToCart(ActionEvent ev) throws FileNotFoundException, IOException{
        ArrayList<Book>customerCart = new ArrayList<Book>();
        for(Book selBook : obtainList()){
           if(selBook.getSelect().isSelected()){
                customerCart.add(selBook);
           }

        }
        
        return true;
    }

    
    @FXML
    public void logout(ActionEvent ev) throws Exception{
        switchScene(ev, "LoginScreen.fxml");
    }
    
    @FXML
    public void checkBox(ActionEvent ev) throws Exception{
        switchScene(ev, "LoginScreen.fxml");
    }
    
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Welcome.setText("Welcome " + ((Customer) currentuser).getName() + "." + " You have " + ((Customer) currentuser).getPoints() + " points. Your current status is " + ((Customer) currentuser).getState().giveStatus() + ".");
        //BookTable.getColumns().addAll(BookTitle, Price, CheckBox);
        
        BookTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<Book,Double>("Price"));
        CheckBox.setCellValueFactory(new PropertyValueFactory<Book,String>("select"));
        BookTable.setItems(obtainList());  
        //selected.setCellValueFactory(new PropertyValueFactory<Book, CheckBox>("CheckBox"));
        // BACK.setOnAction(this::backButton);
    }    
    
}
