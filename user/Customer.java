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
public class Customer extends User{
    private int points;
    private Status currentStatus = new SilverStatus();
    
    public Customer(String userName, String passWord, String name, int points){
        super(userName, passWord, name);
        this.currentStatus = currentStatus;
        this.points = points;
        if(this.getPoints() >= 0 && this.getPoints() < 1000){
            this.previousState();
        }
        else if(this.getPoints() >= 1000){
            this.nextState();
        }
    }
    
    public int getPoints() {
        return points;
    }

    public Status getState() {
        return currentStatus;
    }
    
    public void changeState(Status currentStatus) {
        this.currentStatus = currentStatus;
    }
    
    public void previousState(){
        currentStatus.prev(this);
    }
    
    public void nextState(){
        currentStatus.next(this);
    }
    
    @Override
    public String toString(){
        return getUserName() + "\t" + getPassWord() + "\t" + getName() + "\t" + getPoints();
    }

    public Iterable<Book> getSelectedBooks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPoints(double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
