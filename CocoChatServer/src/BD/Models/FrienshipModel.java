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
    int user1;
    int user2;
    boolean accept;

    public FrienshipModel(int id, int user1, boolean accept) {
        this.id = id;
        this.user1 = user1;
        this.accept = accept;
    }

    public int getId() {
        return id;
    }

    public int getUser1() {
        return user1;
    }

    public int getUser2() {
        return user2;
    }

    public boolean isAccept() {
        return accept;
    }
    
}
