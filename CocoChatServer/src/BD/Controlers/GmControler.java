/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Controlers;

import BD.Conection;
import BD.Models.DmModel;
import BD.Models.Group;
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
public class GmControler  extends Conection{

    public GmControler() {
    }
    
    public void InsertMsg(DmModel Dm){
         PreparedStatement ps;
         try {
        ps = getCon().prepareStatement("Insert into mensajes_grupales (Remitente,Destinatario,Contenido,Fecha)values (?,?,?,?)");
            ps.setInt(1,Dm.Origin );
            ps.setInt(2, Dm.Adresser);
            ps.setString(3, Dm.Content);
            ps.setTimestamp(4, Dm.Fecha);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DmControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> getMsg(int IdUser,int IdGroup){
        ArrayList<String> Messages=new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs; 
        try
        {
            ps=getCon().prepareStatement("select ID_Mensaje,Remitente,Destinatario,contenido,Nombre from mensajes_grupales INNER JOIN perfil ON Mensajes_Grupales.Remitente=perfil.ID_Perfil where Destinatario =(?)");
            ps.setInt(1, IdGroup);
            rs=ps.executeQuery();
            while(rs.next()){
                String Msg="";
                if(rs.getInt("Remitente")==IdUser){
                    Msg+="yo: ";
                }else{
                    Msg+=rs.getString("Nombre")+": ";
                }
                Msg+=rs.getString("contenido");
                Messages.add(Msg);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GmControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Messages;
    }
    public void InsertGroup(Group Group,int id){
        PreparedStatement ps;
        try
        {
            ps=getCon().prepareStatement("insert into chat_grupal (ID_Grupo,Nombre,Descripcion) values (?,?,?)");
            ps.setInt(1, Group.getId());
            ps.setString(2, Group.getName());
            ps.setString(3, Group.getDescription());
            ps.executeUpdate();
            ps=getCon().prepareStatement("insert into grupos (Usuario,Grupo,confirmacion) values (?,?,?)");
            ps.setInt(1, id);
            ps.setInt(2, Group.getId());
            ps.setBoolean(3, true);
        } catch (SQLException ex)
        {
            Logger.getLogger(GmControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void UpdateUser (int IdUser,int IdGroup){
        
    }
    
}
