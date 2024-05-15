/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Controlers;


import BD.Conection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class FriendsControler extends Conection {

    public FriendsControler() {
    }
    
    public boolean GetFrienShip(int ID1,int ID2){
        boolean FriendShip=false;
        PreparedStatement ps;
        ResultSet rs;
        try
        {
          ps= getCon().prepareStatement("Select COUNT(*) FROM amigos where((Usuario_Amigo1=? AND Usuario_Amigo2=?) OR (Usuario_Amigo1=? AND Usuario_Amigo2=?))");
          ps.setInt(1, ID1);
          ps.setInt(2, ID2);
          ps.setInt(3, ID2);
          ps.setInt(4, ID1);
          rs=ps.executeQuery();
          rs.next();
          if(rs.getInt("COUNT(*)") == 1){
              return true;
          }
        } catch (SQLException ex)
        {
            Logger.getLogger(FriendsControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FriendShip;
    }
    
    public ArrayList<FrienshipModel> getPendingFrienships(int id){
        ArrayList<
    }
    
}
