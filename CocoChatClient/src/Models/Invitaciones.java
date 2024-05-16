/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Jorge
 */
public class Invitaciones {
    int ID;
    int User1;
    int User2;
    String Name;
    boolean confirmed;

    public Invitaciones(int ID, int User1, int User2, boolean confirmed,String Name) {
        this.ID = ID;
        this.User1 = User1;
        this.User2 = User2;
        this.confirmed = confirmed;
        this.Name=Name;
    }

    public Invitaciones(int ID, int User1, String Name) {
        this.ID = ID;
        this.User1 = User1;
        this.Name = Name;
        this.confirmed=false;
    }
    
    

    public String getName() {
        return Name;
    }

    public int getID() {
        return ID;
    }

    public int getUser1() {
        return User1;
    }

    public int getUser2() {
        return User2;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
    
    
    
}
