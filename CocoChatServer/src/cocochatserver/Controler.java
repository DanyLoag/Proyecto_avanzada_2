/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochatserver;

import BD.Controlers.DmControler;
import BD.Controlers.FriendsControler;
import BD.Controlers.GroupControlers;
import BD.Controlers.UserControler;
import BD.Models.*;
import Ventanas.Terminal;
import cocochatserver.Hilos.HiloCliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class Controler extends Observable implements Observer,Runnable{
    HashMap<Integer, HiloCliente> UserClient = new HashMap<>();
    HashMap<Integer,UserModel> IdUser=new HashMap<>();
    UserControler User;
    DmControler DmControler;
    GroupControlers GPC;
    FriendsControler FC;
    
    public Controler(Terminal T) {
    GPC=new GroupControlers();
    this.FC=new FriendsControler();
    this.addObserver(T);
    this.User=new UserControler();
    this.DmControler=new DmControler();
    }
    
    public void addUser(int Name, HiloCliente Client){
        IdUser.put(Name, Client.User);
        UserClient.put(Name, Client);
    }
    
    public void UpdateTXT(String S){
        this.setChanged();
        this.notifyObservers(S);
        this.clearChanged();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        Message MSG=(Message) arg;
        
        switch(MSG.Option){
            case 0 ->{
                this.UpdateTXT("Usuario "+MSG.Content.get(0)+"-Desconectado");
                UserClient.get(MSG.Addressee).SendMSG(MSG);
                UserClient.remove(MSG.Addressee);
                IdUser.remove(MSG.Addressee);
                MSG.Option=-1;
                for(HiloCliente Client:this.UserClient.values()){
                    Client.SendMSG(MSG);
                }
                
            }
            case 1 ->{
            DmModel Dmesage=new DmModel(MSG.Origin,MSG.Addressee,MSG.Content.get(0));
            if(FC.GetFrienShip(MSG.Addressee, MSG.Origin)){
               this.DmControler.InsertMSG(Dmesage); 
               if(!(UserClient.containsKey(MSG.Addressee))){
                   return;
               }
            }
            this.UpdateTXT(IdUser.get(MSG.Origin).Nombre+":'"+MSG.Content.get(0)+"' To:"+IdUser.get(MSG.Addressee).Nombre);
            UserClient.get(MSG.Addressee).SendMSG(MSG);
            }
            case 2 ->
            {
                ArrayList<Integer> ID=GPC.GetUsers(MSG.Origin, MSG.Addressee);
                String ChatName=GPC.GetGroupName(MSG.Addressee);
                this.UpdateTXT(ChatName+"-"+IdUser.get(MSG.Origin).Nombre+": "+MSG.Content.get(0));
                for(int i:ID){
                    if(UserClient.containsKey(i)){
                        UserClient.get(i).SendMSG(MSG);
                    }
                }
            }
            case 3->{
                /*
                String Users="";
                for(String us:this.UserClient.keySet()){
                    Users+=us+"-";
                }
                String[] ARR={MSG[0],Users};
                UserClient.get(MSG[1]).SendMSG(ARR);
                */
            }
        }
        
    }

    @Override
    public void run() {
             try
            {
            ServerSocket Server = new ServerSocket(5000);
            Socket sc=new Socket();
            System.out.println("Server Iniciado");
            this.setChanged();
            this.notifyObservers("Server Iniciado");
            this.clearChanged();
           //Controler Controler=new Controler();
            while(true){
                sc=Server.accept();
                DataInputStream IS=new DataInputStream(sc.getInputStream());
                DataOutputStream OS=new DataOutputStream(sc.getOutputStream());
                OS.writeUTF("Dame Tu id");
                int IdUser=IS.readInt();
                UserModel USER=this.User.GetUser(IdUser);
                HiloCliente Cliente=new HiloCliente(IS,OS,USER);
                Cliente.addObserver(this);
                this.addUser(USER.ID, Cliente);
                ArrayList<UserModel> Users=User.GetUsers();
                OS.writeInt(Users.size());
                for(UserModel User: Users){
                    if(User.ID!=IdUser){
                        OS.writeInt(User.ID);
                        OS.writeUTF(User.Nombre);
                        if(this.IdUser.containsKey(User.ID)){
                            OS.writeBoolean(true);
                        }else{
                            OS.writeBoolean(false);
                        }
                        if(this.FC.GetFrienShip(User.ID, IdUser)){
                            OS.writeBoolean(true);
                        }else{
                            OS.writeBoolean(false);
                        }
                    }
                }
                for(HiloCliente Client:this.UserClient.values()){
                    if(Client.User.ID!=IdUser){
                    Message msg=new Message(-2,IdUser,IdUser);
                    Client.SendMSG(msg);
                    }
                }
                this.setChanged();
                this.notifyObservers("Cliente: "+USER.Nombre+" Conectado");
                this.clearChanged();   
                
                Thread MT= new Thread(Cliente);
                MT.start();
            }}catch (IOException ex)
        {
            Logger.getLogger(CocoChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
}

}
