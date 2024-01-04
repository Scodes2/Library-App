package user;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hao
 */
public class Owner extends User {
    
    private static Owner singleInstance = null;
    
    private Owner(){
        super("admin", "admin", "Owner");
    }
    
    public static Owner getInstance(){
        if(singleInstance == null){
            singleInstance = new Owner();
        }
        return singleInstance;
    }
}
