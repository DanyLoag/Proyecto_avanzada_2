/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.HashMap;

/**
 *
 * @author Jorge
 */
public class Group {
    int id;
    String Name;
    String Description;
    HashMap<Integer,Users> IdUser;
    String Chat;
    public Group(int id, String Name, String Description) {
        this.id = id;
        this.Name = Name;
        this.Description = Description;
        this.IdUser = new HashMap<Integer,Users>() ;
        this.Chat="";
    }
    
    public String getUserName(int id){
        return this.IdUser.get(id).getName();
    }
    public void addUser(Users User){
        this.IdUser.put(User.getId(), User);
    }

    public String getName() {
        return Name;
    }
    
    public void AddMessage(String Message){
        this.Chat+=Message+"\n";
    }

    public String getDescription() {
        return Description;
    }

    public String getChat() {
        return Chat;
    }

    public int getId() {
        return id;
    }
    
    
    
}
