/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Jorge
 */
public class Users {
    int id;
    String Name;
    boolean Online;

    public Users(int id, String Name, boolean Online) {
        this.id = id;
        this.Name = Name;
        this.Online = Online;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public boolean isOnline() {
        return Online;
    }
    
    
    
    
}
