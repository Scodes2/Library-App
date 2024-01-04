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
public abstract class User {
    
    private String userName, passWord, name;
    
    public User(String userName, String passWord, String name){
        this.userName = userName;
        this.passWord = passWord;
        this.name = name;
    }
    
    public String getUserName() {
        return this.userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public String getName() {
        return this.name;
    }
    
}
