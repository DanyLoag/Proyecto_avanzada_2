/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;

import Hilos.HiloServidor;
import Models.Group;
import Models.Item;
import Models.Users;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Jorge
 */
public class ListaVentana extends JFrame {
    ArrayList<Users> Users;
    public JComboBox ListUsers=new JComboBox();
    public JComboBox ListFriends=new JComboBox();
    public JComboBox ListGroups=new JComboBox();
    public HiloServidor HiloServer;
    private JButton BUsers=new JButton("Abrir Chat");
    private JButton BFriends=new JButton("Abrir Chat");
    private JButton BGroups=new JButton("Abrir Chat");
    private JLabel Label=new JLabel("Chats");;
    private DataOutputStream Out;
    private int IdUser;
    private ArrayList<Group> Groups;
    private HashMap<Integer,Group> IdGroups;
    private HashMap<Integer,Users> UserMap;

    public ListaVentana(DataOutputStream Out, ArrayList<Users> Users,int IdUser, HiloServidor HiloServer,HashMap<Integer,Users> UserMap,ArrayList<Group> Groups, HashMap<Integer,Group> IdGroups) {
        super("Lista De Chats");
        this.UserMap=UserMap;
        this.HiloServer=HiloServer;
        this.IdUser=IdUser;
        this.Users=Users;
        this.Out = Out;
        this.Groups=Groups;
        this.IdGroups=IdGroups;
        for(Users US:this.Users){
            Item item=new Item(US.getId(),US.getName());
            if(US.isFriend()){
              Item item2=new Item(US.getId(),US.getName());
              ListFriends.addItem(item2);
            }
            if(US.isOnline()){
                item.nombre+="-Online";
            }else{
                item.nombre+="-Offline";
            }
            ListUsers.addItem(item);         
        }
        
        for(Group Group:this.IdGroups.values()){
            Item Item=new Item(Group.getId(),Group.getName());
            this.ListGroups.addItem(Item);
        }
        
        BUsers.addActionListener(e->{
            Item CurrentIndex=(Item) this.ListUsers.getSelectedItem();
            Users UserChat=this.UserMap.get(CurrentIndex.getId());
            if(UserChat.isFriend() || UserChat.isOnline()){
            ChatDVenatana Chat=new ChatDVenatana(UserChat,this.Out,this.HiloServer);
            Chat.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Este Usuario no esta conectado o no son amigos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });
        BFriends.addActionListener(e->{
            Item CurrentIndex=(Item) this.ListFriends.getSelectedItem();
            Users UserChat=this.UserMap.get(CurrentIndex.getId());
            ChatDVenatana Chat=new ChatDVenatana(UserChat,this.Out,this.HiloServer);
            Chat.setVisible(true);
        });
        BGroups.addActionListener(e->{
            Item CurrentIndex=(Item) this.ListGroups.getSelectedItem();
            Group CurrentGroup=this.IdGroups.get(CurrentIndex.getId());
            ChatGrupalVentana CGV=new ChatGrupalVentana(CurrentGroup,this.Out,this.HiloServer);
            CGV.setVisible(true);
        });
        
        
        GroupLayout orden = new GroupLayout(this.getContentPane());
        orden.setHorizontalGroup
        (
                orden.createParallelGroup()
                .addComponent(Label)
                .addGroup
                (
                        orden.createSequentialGroup()
                        .addGroup
                        (
                                orden.createParallelGroup()
                                .addComponent(ListGroups)
                                .addComponent(BGroups)
                        )
                                .addGap(25)
                        .addGroup
                        (
                                orden.createParallelGroup()
                                .addComponent(ListFriends)
                                .addComponent(BFriends)
                        )
                                .addGap(25)
                        .addGroup
                        (
                                orden.createParallelGroup()
                                .addComponent(ListUsers)
                                .addComponent(BUsers)
                        )
                )
        );
        orden.setVerticalGroup
        (
                orden.createSequentialGroup()
                .addGap(50)
                .addComponent(Label)
                .addGap(25)
                .addGroup
                (
                        orden.createParallelGroup()
                        .addGroup
                        (
                                orden.createSequentialGroup()
                                .addComponent(ListGroups)
                                .addComponent(BGroups)
                        )
                        .addGroup
                        (
                                orden.createSequentialGroup()
                                .addComponent(ListFriends)
                                .addComponent(BFriends)
                        )    
                        .addGroup
                        (
                                orden.createSequentialGroup()
                                .addComponent(ListUsers)
                                .addComponent(BUsers)
                        )   
                )
                .addGap(25)
        );
        setLayout(orden);
        this.pack();
    }
    
    public void UpdateUsers(int User,boolean Op){
        int IndexUser=0;
        
        for(int i=0;i<this.ListUsers.getItemCount();i++){
            Item item =(Item) this.ListUsers.getItemAt(i);
            if(item.getId()== User){
                IndexUser=i;
                break;
            }
        }
        
        Item item = new Item(User,this.UserMap.get(User).getName());
        if(Op){
            
            item.nombre+="-Online";
        }else{
            item.nombre+="-Offline";
        }
        this.UserMap.get(User).setOnline(Op);
        this.ListUsers.removeItemAt(IndexUser);
        this.ListUsers.insertItemAt(item, IndexUser);
        this.ListUsers.revalidate();
        this.ListUsers.repaint();
    }
    public void UpdateFrined(int id){
        Users usr=this.UserMap.get(id);
        usr.setFriend(true);
        Item item=new Item(usr.getId(),usr.getName());
        this.ListFriends.addItem(item);
    }
    public void newGroup(Group g){
        this.IdGroups.put(g.getId(), g);
        Item Item=new Item(g.getId(),g.getName());
        this.ListGroups.addItem(Item);
        
    }
    
}
