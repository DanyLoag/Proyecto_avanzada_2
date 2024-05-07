/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;

import Models.Users;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Jorge
 */
public class ChatDVenatana extends JFrame {
    private JButton enviar = new JButton("Enviar"); 
    private JTextArea Log=new JTextArea();
    private JScrollPane Pane= new JScrollPane(Log);
    JTextField TextMesaage = new JTextField(200);
    Users User;

    public ChatDVenatana(Users User) {
        super("Chat");
        this.User=User;
        Log.setPreferredSize(new Dimension(500,400));
        Log.setBackground(Color.DARK_GRAY);
        Log.setForeground(new Color(0x99FFCC));
        GroupLayout orden = new GroupLayout(this.getContentPane());
        
        orden.setHorizontalGroup
        (
         orden.createSequentialGroup()
         .addGap(50)
         .addGroup
            (
                orden.createParallelGroup()
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
                .addComponent(Pane)
                .addComponent(TextMesaage)
                .addComponent(enviar)
                .addGap(20)               
        );
        setLayout(orden);
        this.pack();
    }
    
    
}
