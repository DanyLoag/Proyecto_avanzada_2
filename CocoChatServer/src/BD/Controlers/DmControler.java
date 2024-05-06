/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Controlers;

import BD.Conection;
import BD.Models.DmModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            int ID;
            ID=(Dm.Origin)+(Dm.Adresser*10)+((int)Dm.Fecha.getTime());
            ps.setInt(1,Dm.Origin );
            ps.setInt(2, Dm.Adresser);
            ps.setString(3, Dm.Content);
            ps.setTimestamp(4, Dm.Fecha);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DmControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
