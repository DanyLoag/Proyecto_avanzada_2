/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hilos;

import Models.Group;
import Models.Users;
import cocochatclient.Message;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */

public class HiloServidor extends Observable implements Runnable{
    DataInputStream in;
    private volatile boolean Running; 
    HashMap<Integer,Users> UserMap;
    public HiloServidor(DataInputStream in,HashMap<Integer,Users> UserMap) {
        this.in = in;
        this.Running=true;
        this.UserMap=UserMap;
    }
    public void stop(){
        this.Running=false;
    }

    @Override
    public void run() {
        while(this.Running){
            try {
                int Op=in.readInt();
                switch(Op){
                    case -2 ->{
                        int User=in.readInt();
                        Message MSG=new Message(Op,User);
                        this.setChanged();
                        this.notifyObservers(MSG);
                        this.clearChanged();
                    }
                    case -1 ->{
                        int User=in.readInt();
                        Message MSG=new Message(Op,User);
                        this.setChanged();
                        this.notifyObservers(MSG);
                        this.clearChanged();
                        
                    }
                    case 1 -> {
                        int User=in.readInt();
                        String MSG=in.readUTF();
                        System.out.println("\n"+User+": "+MSG);
                        Message message=new Message(Op,MSG,User);
                        this.setChanged();
                        this.notifyObservers(message);
                        this.clearChanged();
                    }
                    case 2->{
                        int Group=in.readInt();
                        int User=in.readInt();
                        String MSG=in.readUTF();
                        System.out.println("Group("+Group+")-"+User+": "+MSG);
                        Message message=new Message(Op,MSG,User,Group);
                        this.setChanged();
                        this.notifyObservers(message);
                        this.clearChanged();
                    }
                    case 3->
                    {
                        int FriendID=in.readInt();
                        
                        Message message=new Message(Op,FriendID);
                        this.setChanged();
                        this.notifyObservers(message);
                        this.clearChanged();
                    }
                    case 4->
                    {
                        int UserID=in.readInt();
                        int Friendshipid=in.readInt();
                        Message message=new Message(Op,UserID,Friendshipid);
                        this.setChanged();
                        this.notifyObservers(message);
                        this.clearChanged();
                    }
                    case 6->{
                        
                    }
                    case 8->{
                        int idGroup=in.readInt();
                        String Name=in.readUTF();
                        String Description=in.readUTF();
                        Group Group=new Group(idGroup, Name,Description );
                        int SizeGroupUsers=in.readInt();
                        for(int x=0;x<SizeGroupUsers;x++){
                        int idUserGroup=in.readInt();   
                        Group.addUser(UserMap.get(idUserGroup));
                        }
                        int size=in.readInt();
                        String messages="";
                        for(int x=0;x<size;x++){
                    
                        messages=in.readUTF();
                        
                        Group.AddMessage(messages);
                        }
                        
                        Message message=new Message(Op,Group);
                        this.setChanged();
                        this.notifyObservers(message);
                        this.clearChanged();
                    }
                    case 0 -> {
                        this.Running=false;
                    }
                }      
            } catch (IOException ex) {
                Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Hilo Terminado");
    }
}
