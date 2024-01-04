/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author nsmut
 */

  
import java.io.File;
import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import user.Customer;

public class Book {
   private String name;
   private double Price;
   private CheckBox select; 
    
    public Book(String name, double Price){
        this.name = name;
        this.Price = Price;
        this.select = new CheckBox();  
        
    }

    public Book(String name, double Price, CheckBox select){
        this.name = name;
        this.Price = Price;
        this.select = select;  
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }
    
    public CheckBox getSelect() {
        return select;
   }

   public void setSelect(CheckBox select) {
        this.select = select;
    }
    
    @Override
    public String toString(){
        
        
        return (this.name +": $" + this.Price);
    }
    
    
    
    
}
