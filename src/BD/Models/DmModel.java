/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD.Models;

import java.sql.Timestamp;
/**
 *
 * @author Jorge
 */
public class DmModel {
    public int Origin;
    public int Adresser;
    public String Content;
    public Timestamp Fecha;

    public DmModel( int Origin, int Adresser,String Content) {
        this.Origin = Origin;
        this.Adresser = Adresser;
        this.Content=Content;
        this.Fecha=new Timestamp(System.currentTimeMillis());
        
    }
    
    
    
}
