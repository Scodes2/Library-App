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
public interface Status {
    void next(Customer custState);
    void prev(Customer custState);
    String giveStatus();

    public String getStatus();
}
