/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Models;

/**
 *
 * @author Jorge
 */
public class UserModel {
    
    public int ID;
    public String Nombre;
    public String Apep;
    public String Apem;
    public String Mail;

    public UserModel(int ID, String Nombre, String Apep, String Apem, String Mail) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Apep = Apep;
        this.Apem = Apem;
        this.Mail = Mail;
    }

    public UserModel() {
    }
    
    
   
    
}
