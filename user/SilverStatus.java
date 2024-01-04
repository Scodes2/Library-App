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
public class SilverStatus implements Status {
    @Override
    public void next(Customer custState){
        custState.changeState(new GoldStatus() {
            @Override
            public String getStatus() {
            return "Gold";    
            }
        });
    }
    
    @Override
    public void prev(Customer custState){
        System.out.println("Status is already Silver");
    }
    
    @Override
    public String giveStatus(){
        return "Silver";
    }

    @Override
    public String getStatus() {
        return "Silver";
    }
    
}
