/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Controlers;

import BD.Conection;
import BD.Models.DmModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author Jorge
 */
public class DmControler extends Conection{

    public DmControler() {
    }
    
    public void  InsertMSG(DmModel Dm){
        PreparedStatement ps;
         try {
        ps = getCon().prepareStatement("Insert into mensajes_directos (Remitente,Destinatario,Contenido,Fecha)values (?,?,?,?)");
            ps.setInt(1,Dm.Origin );
            ps.setInt(2, Dm.Adresser);
            ps.setString(3, Dm.Content);
            ps.setTimestamp(4, Dm.Fecha);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DmControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> getMessages(int user,int friend){
     ArrayList<String> messages=new ArrayList<>(); 
     PreparedStatement ps;
     ResultSet rs; 
        try
        {
            ps=getCon().prepareStatement("SELECT Remitente.Nombre AS Nombre_Remitente, Remitente, Destinatario.Nombre AS Nombre_Destinatario, Destinatario, mensajes_directos.contenido, mensajes_directos.fecha FROM mensajes_directos INNER JOIN Perfil AS Remitente ON Remitente.ID_perfil = mensajes_directos.Remitente INNER JOIN Perfil AS Destinatario ON Destinatario.ID_perfil = mensajes_directos.Destinatario WHERE mensajes_directos.Remitente IN (?,?) OR mensajes_directos.Destinatario IN (?,?) ORDER BY fecha;");
            ps.setInt(1, user);
            ps.setInt(2, friend);
            ps.setInt(3, user);
            ps.setInt(4, friend);
            rs=ps.executeQuery();
            while(rs.next()){
             String Message="";
             if(rs.getInt("Remitente")==user){
                 Message+="yo: "+rs.getString("contenido");
             }else{
                 Message+=rs.getString("Nombre_Remitente")+": "+rs.getString("contenido");   
             }
             messages.add(Message);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DmControler.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     return messages;
    }
}
