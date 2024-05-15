/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochatserver;

import BD.Controlers.DmControler;
import BD.Controlers.FriendsControler;
import BD.Controlers.GmControler;
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
    HashMap<Integer, HiloCliente> UserClient = new HashMap<>();// aqui vamos a guardar los hilos de cada usuario , para que podamos tener a todos los usuarios que esten conectados 
    HashMap<Integer,UserModel> IdUser=new HashMap<>();// aqui guardamos la informmacion de todos los usuarios conectados con un modelo de la base de datos 
    UserControler User;
    DmControler DmControler;
    GroupControlers GPC;
    FriendsControler FC;
    GmControler GMC;
    /**
     * iniciamos nuestra clase , los controladores de la bd y agregamos que la terminal observe esta clase
     * @param T enviamos la ventana de la terminal ya que esta clase sera observada por la terminal
     */
    public Controler(Terminal T) {
    GPC=new GroupControlers();
    this.FC=new FriendsControler();
    this.addObserver(T);
    this.User=new UserControler();
    this.DmControler=new DmControler();
    this.GMC=new GmControler();
    }
    
    /**
     * se agrega el usuario conectado a los hash map 
     * @param Name id del usuario
     * @param Client modelo del usuario
     */
    public void addUser(int Name, HiloCliente Client){
        IdUser.put(Name, Client.User);
        UserClient.put(Name, Client);
    }
    
    /**
     * se notifica a la terminal de un nuevo mensaje 
     * @param S mensaje que se va a mandar
     */
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
                DmModel Dmesage=new DmModel(MSG.Origin,MSG.Addressee,MSG.Content.get(0));
                this.GMC.InsertMsg(Dmesage);
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
        /**
         * este es nuestro hilo que escucha a los usuarios que se van conectando 
         * este siempre estara corriendo y viendo que algun usuario se conecte
         */
             try
            {
            ServerSocket Server = new ServerSocket(5000);
            Socket sc=new Socket();
            System.out.println("Server Iniciado");
            this.setChanged();
            this.notifyObservers("Server Iniciado");
            this.clearChanged();
           //iniciamos nuestro server
            while(true){
                sc=Server.accept();//esperamos a que algun usuario se conecte , una vez que alguno se conecta vamos inciiando la comunicacion con el 
                DataInputStream IS=new DataInputStream(sc.getInputStream());
                DataOutputStream OS=new DataOutputStream(sc.getOutputStream());
                OS.writeUTF("Dame Tu id");// aqui le pedimos su id que seria su id en la bd 
                int IdUser=IS.readInt();
                /**
                 * TO DO @Fer aca va el login, register y preguntas de seguridad
                 * en el register ademas tienes que tener en cuenta que le debe de avisar a todos los  
                 * usuarios que hay un nuevo usuario en el sistema, igual eso lo podemos hacer hasta que 
                 * acabes todo lo demas. 
                 */
                UserModel USER=this.User.GetUser(IdUser);// obtenemos el modelo del usuario
                HiloCliente Cliente=new HiloCliente(IS,OS,USER); // le creamos un nuevo hilo al usuario, este hilo se encargara de enviar y recibir mensajes del usuario 
                Cliente.addObserver(this);//nuestro hilo controler estara observando a todos los hilos con los usuarios , esto para mantener comunicacion entre los hilos
                this.addUser(USER.ID, Cliente);//agregamos al usuario a los hashmap
                ArrayList<UserModel> Users=User.GetUsers();// aqui obtnermos todos los usuarios conectados y no conectados y se los vamosmandando 
                OS.writeInt(Users.size());//le digo cuantos usuarios hay 
                for(UserModel User: Users){// envio toda la informacion de cada usuario 
                    if(User.ID!=IdUser){
                        OS.writeInt(User.ID);
                        OS.writeUTF(User.Nombre);                        
                        OS.writeBoolean(this.IdUser.containsKey(User.ID));
                        if(this.FC.GetFrienShip(User.ID, IdUser)){
                            OS.writeBoolean(true);
                            ArrayList<String> Messages=DmControler.getMessages( IdUser,User.ID);
                            OS.writeInt(Messages.size());
                            for(String MS:Messages){
                                OS.writeUTF(MS);
                            }
                        }else{
                            OS.writeBoolean(false);
                        }
                    }
                }//le aviso a todos los usuarios conectados que se conecto un nuevo usuario 
                ArrayList<Group> Groups=GPC.GetGroups(IdUser);
                OS.writeInt(Groups.size());
                for(Group Group:Groups){
                    OS.writeInt(Group.getId());
                    OS.writeUTF(Group.getName());
                    OS.writeUTF(Group.getDescription());                 
                    ArrayList<Integer> Ids = GPC.GetUsers(IdUser, Group.getId());
                    OS.writeInt(Ids.size());
                    for(Integer id: Ids){
                        OS.writeInt(id);
                    }
                    ArrayList<String> Messages=this.GMC.getMsg(IdUser, Group.getId());
                    OS.writeInt(Messages.size());
                    for(String MS:Messages){
                        OS.writeUTF(MS);
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
                
                Thread MT= new Thread(Cliente);//inicia el hilo que esta escuchando al cliente nuevo
                MT.start();
            }}catch (IOException ex)
        {
            Logger.getLogger(CocoChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
}

}
