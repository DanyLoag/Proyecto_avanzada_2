/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochatserver.Hilos;

import BD.Models.UserModel;
import cocochatserver.Message;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Jorge
 */
public class HiloCliente extends Observable implements Runnable {
    DataInputStream IS;
    DataOutputStream OS;
    public UserModel User;

    public HiloCliente(DataInputStream IS, DataOutputStream OS,UserModel USR) {
        this.IS = IS;
        this.OS = OS;
        this.User=USR;
    }
     

    @Override
    public void run() {//hilo encargado de escuchar la info del cliente y enviar info al cliente
        System.out.println("Usuario "+this.User.Nombre+" Inicio");
        Outerloop:while(true){      
            try
            {            
                int OP=IS.readInt();
                Message Msg;
                ArrayList<String> Content=new ArrayList<String>();
                switch(OP){
                    case 1 ->//opcion 1 el usuario envia un mensaje directo
                    {
                        int User=IS.readInt();
                        String MSG = IS.readUTF();      
                        Content.add(MSG);
                        Msg=new Message(OP,Content,User,this.User.ID);
                        this.setChanged();
                        this.notifyObservers(Msg);
                        this.clearChanged();
                    }
                    case 2->{//opcion 2 el usuario envia un mensaje a grupos
                        int Group=IS.readInt();
                        String MSG = IS.readUTF();      
                        Content.add(MSG);
                        Content.add(this.User.Nombre);
                        Msg=new Message(OP,Content,Group,this.User.ID);
                        this.setChanged();
                        this.notifyObservers(Msg);
                        this.clearChanged();
                    }
                    case 0 ->//el usuario se desconecta y se termina el hilo
                    {
                        this.setChanged();
                        Content.add(this.User.Nombre);
                        Msg=new Message(OP,Content,this.User.ID,this.User.ID);
                        this.notifyObservers(Msg);
                        this.clearChanged();
                        break Outerloop;
                    }
                }
                
            } catch (IOException ex)
            {
                Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    public void SendMSG(Message Message){//metodo con el que enviamos info al server
        try
        {
            OS.writeInt(Message.Option);
            switch(Message.Option){
                case -2://se conecto un nuevo usuario
                    OS.writeInt(Message.Origin);
                    break;
                case -1:// se desconecto un usuario
                    OS.writeInt(Message.Addressee);
                    break;
                case 0: // confirmacion de que se va a desconectar el cliente
                    System.out.println("Usuario "+Message.Content.get(0)+" Desconectado");
                    end();
                    break;
                case 1://mensaje directo para el usuario
                    OS.writeInt(Message.Origin);
                    OS.writeUTF(Message.Content.get(0));
                break;
                case 2://mensaje de grupo para el  usuario
                    OS.writeInt(Message.Addressee);
                    OS.writeInt(Message.Origin);
                    OS.writeUTF(Message.Content.get(0));
                    
                break;
            }
        } catch (IOException ex)
        {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void end(){// cerramos la conexion con el usuario
        try
        {
            this.IS.close();
            this.OS.close();
        } catch (IOException ex)
        {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    


    
    
}
