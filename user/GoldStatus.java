/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author Hao
 */
public abstract class GoldStatus implements Status {
    @Override
    public void next(Customer custState){
        System.out.println("Status is already Gold");
    }
    
    @Override
    public void prev(Customer custState){
        custState.changeState(new SilverStatus());
    }
    
    @Override
    public String giveStatus(){
        return "Gold";
    }
    
}
