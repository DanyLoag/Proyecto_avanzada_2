/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Models;

/**
 *
 * @author Jorge
 */
public class FrienshipModel {
    int id;
    int user2;
    boolean accept;

    public FrienshipModel(int id, int user2, boolean accept) {
        this.id = id;
        this.user2 = user2;
        this.accept = accept;
    }
    
}
