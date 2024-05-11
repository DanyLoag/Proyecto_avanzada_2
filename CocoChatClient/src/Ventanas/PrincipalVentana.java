/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;

import Hilos.HiloServidor;
import Models.Users;
import cocochatclient.Message;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Jorge
 */
public class PrincipalVentana  extends JFrame implements Observer {
    
    private JButton Lista=new JButton("Lista de usuarios\nconectados");
    private JButton GruposAmistades=new JButton("Grupos/Amigos");
    private JLabel Label1=new JLabel("Coco-Chat");
    private JButton Salir=new JButton("Cerrar Sesion");
    private int IdUser;
    private DataInputStream In;
    private DataOutputStream Out;
    public HiloServidor HiloServer;
    ArrayList<Users> Users;
    HashMap<Integer,Users> UserMap;
    ListaVentana LS;

    public PrincipalVentana(DataInputStream IN,DataOutputStream OUT,ArrayList<Users> User,HiloServidor HiloServidor, int IdUser,HashMap<Integer,Users> UserMap ) {
        super("Menu");
        this.UserMap=UserMap;
        this.HiloServer=HiloServidor;
        this.HiloServer.addObserver(this);
        this.IdUser=IdUser;
        Thread TS=new Thread(this.HiloServer);
        TS.start();
        this.Users=User;
        this.In=IN;
        this.Out=OUT;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GroupLayout orden = new GroupLayout(this.getContentPane());
        LS=new ListaVentana(Out,Users,this.IdUser,this.HiloServer);
        Salir.addActionListener(e->{
            try
            {
                Out.writeInt(0);
                dispose();
                System.exit(0);
            } catch (IOException ex)
            {
                Logger.getLogger(PrincipalVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        Lista.addActionListener(e->{
            LS.setVisible(true);
        });
        
        orden.setHorizontalGroup
        (
                orden.createParallelGroup()
                .addGroup
                (
                        orden.createSequentialGroup()
                                .addGap(175)
                        .addComponent(Label1)
                                .addGap(75)
                        .addComponent(Salir)
                )
                .addGroup
                (
                        orden.createSequentialGroup()
                                .addGap(50)
                        .addComponent(Lista)
                        .addGap(150)
                        .addComponent(GruposAmistades)
                        .addGap(50)
                )              
        );
        orden.setVerticalGroup
        (
                orden.createSequentialGroup()
                        .addGap(50)
                .addGroup
                (
                        orden.createParallelGroup()
                        .addComponent(Label1)
                        .addComponent(Salir)
                )
                        .addGap(50)
                .addGroup
                (
                        orden.createParallelGroup()
                        .addComponent(Lista)
                        .addComponent(GruposAmistades)
                )  
                .addGap(25)
        );
        setLayout(orden);
        this.pack();
    }

    @Override
    public void update(Observable o, Object arg) {
    Message MSG  = (Message) arg;
    int OP=MSG.Option;
    switch(OP){
        case -2 ->{
            this.LS.UpdateUsers(MSG.Origin, true);
        }
        case -1 ->{   
            this.LS.UpdateUsers(MSG.Origin, false);
        }
        case 1 ->{
            Users tempUser=this.UserMap.get(MSG.Origin);
            String fullmsg=tempUser.getName()+": "+MSG.Content;
            tempUser.AddMessage(fullmsg);
        }
        
    }
    }
    
    
    
}
