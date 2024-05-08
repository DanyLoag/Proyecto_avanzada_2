/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;

import Hilos.HiloServidor;
import Models.Users;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
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

    public ListaVentana(DataOutputStream Out, ArrayList<Users> Users,int IdUser, HiloServidor HiloServer) {
        super("Lista De Chats");
        this.HiloServer=HiloServer;
        this.IdUser=IdUser;
        this.Users=Users;
        this.Out = Out;
        for(Users US:this.Users){
            String UserState=US.getName();
            if(US.isFriend()){
              ListFriends.addItem(UserState);
            }
            if(US.isOnline()){
                UserState+="-Online";
            }else{
                UserState+="-Offline";
            }
            ListUsers.addItem(UserState);
            
        }
        BUsers.addActionListener(e->{
            int CurrentIndex=this.ListUsers.getSelectedIndex();
            Users UserChat=Users.get(CurrentIndex);
            if(UserChat.isFriend() || UserChat.isOnline()){
            ChatDVenatana Chat=new ChatDVenatana(UserChat,this.Out,this.HiloServer);
            Chat.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Este Usuario no esta conectado o no son amigos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
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
        for(int i=0;i<this.Users.size();i++){
            if(this.Users.get(i).getId()==User){
                IndexUser=i;
                break;
            }
        }
        String NewField=this.Users.get(IndexUser).getName();
        if(Op){
            
            NewField+="-Online";
        }else{
            NewField+="-Offline";
        }
        this.Users.get(IndexUser).setOnline(Op);
        this.ListUsers.removeItemAt(IndexUser);
        this.ListUsers.insertItemAt(NewField, IndexUser);
        this.ListUsers.revalidate();
        this.ListUsers.repaint();
    }
    
}
