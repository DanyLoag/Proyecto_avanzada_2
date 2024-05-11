/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cocochatserver;
import Ventanas.Terminal;
import cocochatserver.Hilos.HiloCliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jorge
 */
public class CocoChatServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Terminal Terminal=new Terminal();
        Terminal.setVisible(true);// iniciamos nuestra ventana de terminal e inicia nuestro programa 
       /* try
        {
            // TODO code application logic here
            
            ServerSocket Server = new ServerSocket(5000);
            Socket sc=new Socket();
            System.out.println("Server Iniciado");
            Controler Controler=new Controler();
            while(true){
                sc=Server.accept();
                DataInputStream IS=new DataInputStream(sc.getInputStream());
                DataOutputStream OS=new DataOutputStream(sc.getOutputStream());
                OS.writeUTF("Dame Tu Nombre");
                String Name=IS.readUTF();
                System.out.println("Cliente: "+Name+" Conectado");
                HiloCliente Cliente=new HiloCliente(IS,OS,Name);
                Cliente.addObserver(Controler);
                Controler.addUser(Name, Cliente);
                Thread MT= new Thread(Cliente);
                MT.start();
            }
            
        } catch (IOException ex)
        {
            Logger.getLogger(CocoChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
}
