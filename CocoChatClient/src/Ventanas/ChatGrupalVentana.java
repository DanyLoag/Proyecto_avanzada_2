/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;

/**
 *
 * @author Jorge
 */

import Hilos.HiloServidor;
import Models.Group;
import cocochatclient.Message;
import java.awt.Color;
import java.awt.Dimension;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatGrupalVentana extends JFrame implements Observer {
    
    private JLabel Desc=new JLabel();
    private JButton enviar = new JButton("Enviar"); 
    private JTextArea Log=new JTextArea();
    private JScrollPane Pane= new JScrollPane(Log);
    private JLabel Chat=new JLabel();
    DataOutputStream Out;
    JTextField TextMesaage = new JTextField(200);
    public HiloServidor HiloServer;
    public Group Group;

    public ChatGrupalVentana(Group Group,DataOutputStream OutS,HiloServidor HiloServer) {
        super("Chat");
        
        this.HiloServer=HiloServer;
        this.HiloServer.addObserver(this);
        this.Chat.setText("Grupo: "+Group.getName());
        this.Desc.setText(Group.getDescription());
        this.Group=Group;
        this.Out=OutS;
        Log.setPreferredSize(new Dimension(500,400));
        Log.setBackground(Color.DARK_GRAY);
        Log.setForeground(new Color(0x99FFCC));
        Log.append(Group.getChat());
        
        enviar.addActionListener(e->{
            try
            {
                this.Out.writeInt(2);
                this.Out.writeInt(this.Group.getId());
                String mesaage=TextMesaage.getText();
                this.Out.writeUTF(mesaage);
                mesaage="Yo:"+mesaage;
                this.Group.AddMessage(mesaage);
                Log.append(mesaage+"\n");
            } catch (IOException ex)
            {
                Logger.getLogger(ChatDVenatana.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        GroupLayout orden = new GroupLayout(this.getContentPane());
        
        orden.setHorizontalGroup
        (
         orden.createSequentialGroup()
         .addGap(50)
         .addGroup
            (
                orden.createParallelGroup()
                .addComponent(Chat)
                .addComponent(Desc)
                .addComponent(Pane)
                .addComponent(TextMesaage)
                .addComponent(enviar)
            )
         .addGap(50)       
        );
        orden.setVerticalGroup
        (
          orden.createSequentialGroup()
                .addGap(20)
                .addComponent(Chat)
                .addComponent(Desc)
                .addComponent(Pane)
                .addComponent(TextMesaage)
                .addComponent(enviar)
                .addGap(20)               
        );
        setLayout(orden);
        this.pack();
    }

    @Override
    public void update(Observable o, Object arg) {
        Message MSG  = (Message) arg;
        int OP=MSG.Option;
        if(OP==2&&MSG.IdGroup==this.Group.getId()){
            String Message=this.Group.getUserName(MSG.Origin)+": "+MSG.Content;
            this.Log.append(Message+"\n");
        }
    }
    
}
