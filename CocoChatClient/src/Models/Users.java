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
    String Chat;
    boolean Online;
    boolean friend;
    
    public Users(int id, String Name, boolean Online,boolean friend) {
        this.id = id;
        this.Name = Name;
        this.Online = Online;
        this.Chat="";
        this.friend=friend;
    }

    public void setChat(String Chat) {
        this.Chat = Chat;
    }

    public void setOnline(boolean Online) {
        this.Online = Online;
    }
    
    
    
    public void AddMessage(String Message){
        this.Chat+=Message+"\n";
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

    public boolean isFriend() {
        return friend;
    }
    
}
