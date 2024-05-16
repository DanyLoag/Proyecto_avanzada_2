/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Models;

import java.util.HashMap;

/**
 *
 * @author Jorge
 */
public class Group {
    public int id;
    public String Name;
    public String Description;
    public Group(int id, String Name, String Description) {
        this.id = id;
        this.Name = Name;
        this.Description = Description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return Description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Group() {
    }


    public String getName() {
        return Name;
    }
    
}
