/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;

import Models.Invitaciones;
import Models.Item;
import Models.Users;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author Jorge
 */
public class InvitacionesVentana extends JFrame  {
    private JComboBox ListUsers = new JComboBox();
    private JComboBox ListFrienRequest = new JComboBox();
    private JButton SendFriendShip = new JButton("Send Friendship");
    private JButton AccepFriendShip = new JButton("Accept");
    private JButton DeclineFriendShip = new JButton("Decline");
    private ArrayList<Invitaciones> Friendships;
    private HashMap<Integer,Users> UserMap;
    public InvitacionesVentana(ArrayList<Invitaciones> Friendships,HashMap<Integer,Users> UserMap) {
        this.Friendships=Friendships;
        this.UserMap=UserMap;
        for(Invitaciones Inv: this.Friendships){
            int id=Inv.getID();
            String Name=Inv.getName();
            Item Item=new Item(id,Name);
        }
        for(Users usr:this.UserMap.values()){
            int id=usr.getId();
            String Name=usr.getName();
            Item item=new Item(id,Name);
            this.ListUsers.addItem(item);
        }
        GroupLayout orden = new GroupLayout(this.getContentPane());
        orden.setHorizontalGroup
        (
                orden.createParallelGroup()
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
        );
        orden.setVerticalGroup
        (
                orden.createSequentialGroup()
                .addGap(50)
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
        );
        setLayout(orden);
        this.pack();
        
    }
    
}
