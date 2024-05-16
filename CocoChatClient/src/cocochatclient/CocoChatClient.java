/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cocochatclient;

import Hilos.HiloServidor;
import Models.Group;
import Models.Invitaciones;
import Models.Users;
import Ventanas.PrincipalVentana;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class CocoChatClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
            Scanner sn=new Scanner (System.in);  
        try {
            Socket sc = new Socket("127.0.0.1",5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            /**
             * ola fer,aca iria el  login , en si ahorita te manda directo a la pantalla principal pero debe
             * de primero mostrar la pantalla de login antes de la de inicio, y todo esto de abajo de leer
             * los usuarios seria hasta que el usuario inicie sesion
             */
            String MSG=in.readUTF();
            System.out.println(MSG);
            int id=sn.nextInt();
            out.writeInt(id);
            String Name;
            boolean Online;
            int SizeUsers;
            boolean friend;
            ArrayList<Users> Users=new ArrayList<>();
            HashMap<Integer,Users> UserMap=new HashMap<>();
            SizeUsers=in.readInt()-1;
            for(int i=0;i<SizeUsers;i++){
                id=in.readInt();
                Name=in.readUTF();
                Online=in.readBoolean();
                friend=in.readBoolean();
                Users USR=new Users(id,Name,Online,friend);
                if(friend){
                    String messages="";
                    int size=in.readInt();
                    for(int x=0;x<size;x++){
                        messages=in.readUTF();
                        USR.AddMessage(messages);
                    }
                }
            
            
                Users.add(USR);
                UserMap.put(id, USR);
            }
            int GroupSize=in.readInt();
            ArrayList<Group> Groups=new ArrayList<Group>();
            HashMap<Integer,Group> IdGroups=new  HashMap<Integer,Group>();
            
            for (int i=0;i<GroupSize;i++){
                int IdGroup=in.readInt();
                String NameGroup=in.readUTF();
                String DescriptionGroup=in.readUTF();
                int SizeGroupUsers=in.readInt();
                Group Group=new Group(IdGroup, NameGroup,DescriptionGroup );
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
                Groups.add(Group);
                IdGroups.put(IdGroup, Group);
            }
            
            int PendingFriends =  in.readInt();
            HashMap<Integer,Invitaciones> AmigosPendientes=new  HashMap<Integer,Invitaciones>();
            for(int i=0;i<PendingFriends;i++){
                int idinv=in.readInt();
                int user1=in.readInt();
                String name=UserMap.get(user1).getName();
                Invitaciones inv=new Invitaciones(idinv,user1,name);
                AmigosPendientes.put(idinv, inv);
            }
            
            int PendingGroups = in.readInt();
            HashMap<Integer,Invitaciones> GroupPendientes=new  HashMap<Integer,Invitaciones>();
            for(int i=0;i<PendingGroups;i++){
                int idinv=in.readInt();
                String name=in.readUTF();
                int user1=in.readInt();
                
                Invitaciones inv=new Invitaciones(idinv,user1,name);
                GroupPendientes.put(idinv, inv);
            }
            HiloServidor HiloServidor=new HiloServidor(in,UserMap);
            PrincipalVentana Ventana=new PrincipalVentana(in,out,Users,HiloServidor,id,UserMap,Groups,IdGroups,AmigosPendientes,GroupPendientes);    
            Ventana.setVisible(true);           
            
        } catch (IOException ex) {
            Logger.getLogger(CocoChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.print("Hola");
    }
    
}
