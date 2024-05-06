/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Controlers;

import BD.Conection;
import BD.Models.UserModel;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class UserControler extends Conection{

    public UserControler() {
    }
    
    public UserModel GetUser(int ID){
        UserModel User=new UserModel();
        PreparedStatement ps;
        
        try
        {
            ResultSet rs;
            ps = getCon().prepareStatement("Select * FROM perfil WHERE ID_Perfil=(?)");
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            rs.next();
            
            User.ID=rs.getInt("ID_Perfil");
            User.Nombre=rs.getString("Nombre");
            User.Apem=rs.getString("APEM");
            User.Apep=rs.getString("APEP");
            User.Mail=rs.getString("Mail");;
            return User;
        } catch (SQLException ex)
        {
            Logger.getLogger(UserControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return User;
    }
    
}
