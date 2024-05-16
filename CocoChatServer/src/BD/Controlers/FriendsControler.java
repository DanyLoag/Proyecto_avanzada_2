/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Controlers;


import BD.Conection;
import BD.Models.FrienshipModel;
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
          ps= getCon().prepareStatement("Select COUNT(*) FROM amigos where(confirmacion=1 AND  ((Usuario_Amigo1=? AND Usuario_Amigo2=?) OR (Usuario_Amigo1=? AND Usuario_Amigo2=?)))");
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
        ArrayList<FrienshipModel> Pending = new  ArrayList<FrienshipModel>();
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            ps=getCon().prepareStatement("select ID_Amigo,Usuario_Amigo1,Usuario_Amigo2,confirmacion from amigos where Usuario_amigo2=(?) AND confirmacion=0 ");
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while(rs.next()){
                int idF = rs.getInt("ID_Amigo");
                int idUser1=rs.getInt("Usuario_Amigo1");
                boolean confirmed=rs.getBoolean("confirmacion");
                FrienshipModel FS=new FrienshipModel(idF,idUser1,confirmed);
                Pending.add(FS);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(FriendsControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Pending;
    }
    
    public void updateFriendship(int id){
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            ps=getCon().prepareStatement("update amigos set confirmacion = 1 where ID_Amigo=(?)");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            Logger.getLogger(FriendsControler.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public void DeleteFriendship(int id){
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            ps=getCon().prepareStatement("delete from amigos where ID_Amigo=(?)");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            Logger.getLogger(FriendsControler.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public int insertFriendShip(int id1,int id2){
        PreparedStatement ps;
        ResultSet rs;
        int id=0;
        try
        {
            ps=getCon().prepareStatement("insert into amigos (Usuario_Amigo1,Usuario_Amigo2,confirmacion) values (?,?,false);");
            ps.setInt(1, id1);
            ps.setInt(2, id2);
            ps.executeUpdate();
            ps=getCon().prepareStatement(" select ID_Amigo from amigos where (Usuario_Amigo1=? and Usuario_Amigo2=?)");
            ps.setInt(1, id1);
            ps.setInt(2, id2);
            rs=ps.executeQuery();
            rs.next();
            id=rs.getInt("ID_Amigo");
        } catch (SQLException ex)
        {
            Logger.getLogger(FriendsControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    

    
}
