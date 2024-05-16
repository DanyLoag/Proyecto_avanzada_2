/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;

import Models.Group;
import Models.Invitaciones;
import Models.Item;
import Models.Users;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Jorge
 */
public class InvitacionesVentana extends JFrame  {
    private JLabel Amistades=new JLabel("Amigos");
    private JLabel Grupos=new JLabel("Grupos");
    private JComboBox ListUsers = new JComboBox();
    private JComboBox ListFrienRequest = new JComboBox();
    
    private JComboBox ListUsers2 = new JComboBox();
    private JComboBox ListGroupRequest = new JComboBox();
    
    private JTextField ID = new JTextField();
    private JTextField Nombre = new JTextField();
    private JTextField Descripcion = new JTextField();

    
    private JButton SendFriendShip = new JButton("Send Friendship");
    private JButton AccepFriendShip = new JButton("Accept");
    private JButton DeclineFriendShip = new JButton("Decline");
    
    private JButton SendGroup = new JButton("Send Group");
    private JButton AccepGroup = new JButton("Accept");
    private JButton DeclineGroup = new JButton("Decline");
    
    private JButton CreateGroup = new JButton("Create Group");
    private HashMap<Integer,Invitaciones> AmigosPendientes;
    private HashMap<Integer,Users> UserMap;
    HashMap<Integer,Group> IdGroups;
    private DataOutputStream Out;
    HashMap<Integer,Invitaciones> GruposPendientes;
    ListaVentana LS;
    public InvitacionesVentana( HashMap<Integer,Invitaciones> GruposPendientes,HashMap<Integer,Invitaciones> AmigosPendientes,DataOutputStream Out,ListaVentana LS,HashMap<Integer,Group> IdGroups,HashMap<Integer,Users> UserMap) {
        super("invitaciones");
        this.IdGroups=IdGroups;
        this.LS=LS;
        this.Out=Out;
        this.AmigosPendientes=AmigosPendientes;
        this.GruposPendientes=GruposPendientes;
        this.UserMap=UserMap;
        this.IdGroups=IdGroups;
        
        for(Invitaciones Inv: this.AmigosPendientes.values()){
            int id=Inv.getID();
            String Name=Inv.getName();
            Item Item=new Item(id,Name);
            this.ListFrienRequest.addItem(Item);
        }
        for(Invitaciones Inv: this.GruposPendientes.values()){
            int id=Inv.getID();
            String Name=Inv.getName();
            Item Item=new Item(id,Name);
            this.ListGroupRequest.addItem(Item);
        }
        
        
        for(Users usr:this.UserMap.values()){
            if(!usr.isFriend()){
            int id=usr.getId();
            String Name=usr.getName();
            Item item=new Item(id,Name);
            this.ListUsers.addItem(item);
        }}
        
         for(Users usr:this.UserMap.values()){

            int id=usr.getId();
            String Name=usr.getName();
            Item item=new Item(id,Name);
            this.ListUsers2.addItem(item);
        }
        
        this.AccepFriendShip.addActionListener(e->{
            try
            {
                Item item=(Item)  this.ListFrienRequest.getSelectedItem();
                Invitaciones inv=this.AmigosPendientes.get(item.getId());
                Out.writeInt(3);
                Out.writeInt(inv.getID());
                Out.writeBoolean(true);
                Out.writeInt(inv.getUser1());
                this.ListFrienRequest.removeItemAt(this.ListFrienRequest.getSelectedIndex());
                this.LS.UpdateFrined(inv.getUser1());
                
                
            } catch (IOException ex)
            {
                Logger.getLogger(InvitacionesVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        });
        
        this.DeclineFriendShip.addActionListener(e->{
            try
            {
                Item item=(Item)  this.ListFrienRequest.getSelectedItem();
                Invitaciones inv=this.AmigosPendientes.get(item.getId());
                Out.writeInt(3);
                Out.writeInt(inv.getID());
                Out.writeBoolean(false);
                Out.writeInt(inv.getUser1());
                this.ListFrienRequest.removeItemAt(this.ListFrienRequest.getSelectedIndex());  
            } catch (IOException ex)
            {
                Logger.getLogger(InvitacionesVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.SendFriendShip.addActionListener(e->{
            try
            {
                Item item=(Item)  this.ListUsers.getSelectedItem();
                Out.writeInt(4);
                Out.writeInt(item.getId());
            } catch (IOException ex)
            {
                Logger.getLogger(InvitacionesVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.AccepGroup.addActionListener(e->{
            try
            {
                Item item=(Item)  this.ListGroupRequest.getSelectedItem();
                Invitaciones inv=this.GruposPendientes.get(item.getId());
                Out.writeInt(6);
                Out.writeInt(inv.getID());
                Out.writeBoolean(true);
                Out.writeInt(inv.getUser1());
                this.ListGroupRequest.removeItemAt(this.ListGroupRequest.getSelectedIndex());
                //this.LS.UpdateFrined(inv.getUser1());
                
                
            } catch (IOException ex)
            {
                Logger.getLogger(InvitacionesVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.DeclineGroup.addActionListener(e->{ 
            try
            {
                Item item=(Item)  this.ListGroupRequest.getSelectedItem();
                Invitaciones inv=this.GruposPendientes.get(item.getId());
                Out.writeInt(6);
                Out.writeInt(inv.getID());
                Out.writeBoolean(false);
                Out.writeInt(inv.getUser1());
                this.ListGroupRequest.removeItemAt(this.ListGroupRequest.getSelectedIndex());
                //this.LS.UpdateFrined(inv.getUser1());
                
                
            } catch (IOException ex)
            {
                Logger.getLogger(InvitacionesVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.CreateGroup.addActionListener(e->{
            this.CreateGroup();
        });
        GroupLayout orden = new GroupLayout(this.getContentPane());
        orden.setHorizontalGroup
        (
                
                orden.createParallelGroup()
                .addComponent(Amistades)
                .addGroup
                (
                        orden.createSequentialGroup()
                        .addGap(10)
                        .addGroup
                        (
                                orden.createParallelGroup()
                                
                                .addComponent(ListUsers)
                                .addComponent(SendFriendShip)
                        )
                        .addGap(25)
                        .addGroup
                        (
                                 orden.createParallelGroup()
                                .addComponent(ListFrienRequest)
                                .addComponent(AccepFriendShip)
                                .addComponent(DeclineFriendShip)
                        )
                        .addGap(10)
                        
                )
                .addComponent(Grupos)
                .addComponent(ID)
                .addComponent(Nombre)
                .addComponent(Descripcion)
                .addComponent(CreateGroup)
                .addGroup
                (
                        orden.createSequentialGroup()
                        .addGap(10)
                        .addGroup
                        (
                                orden.createParallelGroup()
                                
                                .addComponent(ListUsers2)
                                .addComponent(SendGroup)
                        )
                        .addGap(25)
                        .addGroup
                        (
                                 orden.createParallelGroup()
                                .addComponent(ListGroupRequest)
                                .addComponent(AccepGroup)
                                .addComponent(DeclineGroup)
                        )
                        .addGap(10)
                        
                )
        );
        orden.setVerticalGroup
        (
                orden.createSequentialGroup()
                .addComponent(Amistades)
                .addGroup
                (
                        orden.createParallelGroup()
                        .addGroup
                        (
                                orden.createSequentialGroup()
                                .addComponent(ListUsers)
                                .addComponent(SendFriendShip)
                        )
                        .addGroup
                        (
                                 orden.createSequentialGroup()
                                .addComponent(ListFrienRequest)
                                .addComponent(AccepFriendShip)
                                .addComponent(DeclineFriendShip)
                        )
               )
               .addGap(25)
               .addComponent(Grupos)
                        .addGap(5)
               .addComponent(ID)
                        .addGap(5)
               .addComponent(Nombre)
                        .addGap(5)
               .addComponent(Descripcion)
                        .addGap(5)
               .addComponent(CreateGroup)
                .addGroup
                (
                        orden.createParallelGroup()
                        .addGroup
                        (
                                orden.createSequentialGroup()
                                .addComponent(ListUsers2)
                                .addComponent(SendGroup)
                        )
                        .addGroup
                        (
                                 orden.createSequentialGroup()
                                .addComponent(ListGroupRequest)
                                .addComponent(AccepGroup)
                                .addComponent(DeclineGroup)
                        )
               )
                .addGap(25)
        );
        setLayout(orden);
        this.pack();
    }
    public void addFriendship(int IdUser,int IdFrienship){
        Users usr = this.UserMap.get(IdUser);
        Invitaciones inv = new Invitaciones(IdFrienship,IdUser,usr.getName());
        this.AmigosPendientes.put(IdFrienship, inv);
        Item Item=new Item(inv.getID(),inv.getName());
        this.ListFrienRequest.addItem(Item);
    }
    public void CreateGroup(){
        int id=Integer.parseInt(this.ID.getText());
        String Name=this.Nombre.getText();
        String Descripcion=this.Descripcion.getText();
        try
        {
            Out.writeInt(5);
            Out.writeInt(id);
            Out.writeUTF(Name);
            Out.writeUTF(Descripcion);
        } catch (IOException ex)
        {
            Logger.getLogger(InvitacionesVentana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
