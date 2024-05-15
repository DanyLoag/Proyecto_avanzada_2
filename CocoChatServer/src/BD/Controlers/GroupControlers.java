/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Controlers;

import BD.Conection;
import BD.Models.Group;
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
public class GroupControlers extends Conection {

    public GroupControlers() {
    }
    
    public ArrayList<Integer> GetUsers(int IdUser,int IdGroup){
        ArrayList<Integer> IdUsers= new ArrayList<Integer>();
        PreparedStatement ps;
        try{
            ResultSet rs;
            ps = getCon().prepareStatement("SELECT  Usuario FROM grupos WHERE (Grupo=? AND Usuario != ?)");
            ps.setInt(1, IdGroup);
            ps.setInt(2, IdUser);
            rs = ps.executeQuery();
            while(rs.next()){
                IdUsers.add(rs.getInt("Usuario"));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(UserControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IdUsers;
    }
    public String GetGroupName(int id){
        String Name="";
        PreparedStatement ps;
        ResultSet rs;
        try
        {
          ps = getCon().prepareStatement("SELECT Nombre FROM chat_grupal WHERE (ID_Grupo=?)");
          ps.setInt(1, id);
          rs=ps.executeQuery();
          rs.next();
          Name=rs.getString("Nombre");
        } catch (SQLException ex)
        {
            Logger.getLogger(GroupControlers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Name;
    }
    
    
    public ArrayList<Group> GetGroups(int id){
        ArrayList<Group> Groups = new ArrayList<Group>();
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            ps = getCon().prepareStatement("select ID, Usuario , Grupo,chat_grupal.Nombre,chat_grupal.Descripcion from grupos left JOIN chat_grupal ON chat_Grupal.ID_Grupo=Grupos.Grupo where usuario = (?)");
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while(rs.next()){
                Group Group=new Group();
                Group.setId(rs.getInt("Grupo"));
                Group.setName(rs.getString("Nombre"));
                Group.setDescription(rs.getString("Descripcion"));
                Groups.add(Group);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GroupControlers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Groups;
    }
    
}
