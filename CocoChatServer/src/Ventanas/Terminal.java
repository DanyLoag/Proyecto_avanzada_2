/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;

import cocochatserver.Controler;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Jorge
 */
public class Terminal  extends JFrame implements Observer{
    private JButton Boton1=new JButton("Cargar Registro");
    private JButton Boton2=new JButton("Guardar Registro");
    private JTextArea Log=new JTextArea();
    private JScrollPane Pane= new JScrollPane(Log);
    File Register;
    
    /**
     * aqui todo normal iniciamos los componentes de nuestra view
     */
    public Terminal() {
        super("Terminal");
        Register=new File("C:\\Users\\Jorge\\OneDrive\\Documents\\NetBeansProjects\\CocoChatServer\\ServerLog.txt");
        
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(0x123456));
        setContentPane(contentPane);
        
        Log.setPreferredSize(new Dimension(500,400));
        Log.setBackground(Color.DARK_GRAY);
        Log.setForeground(new Color(0x99FFCC));
        Pane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GroupLayout orden = new GroupLayout(this.getContentPane());
        
        Boton1.addActionListener(e->{
            this.RecordLog();
        });
        
        Boton2.addActionListener(e->{
            this.SaveLog();
        });
        orden.setHorizontalGroup
        (
        orden.createParallelGroup()
                .addComponent(Pane)
                .addComponent(Boton1)
                .addComponent(Boton2)
        );
        orden.setVerticalGroup
        (
        orden.createSequentialGroup()
                .addComponent(Pane)
                .addGap(100)
                .addComponent(Boton1)
                .addComponent(Boton2)
        );
        setLayout(orden);
        this.pack();
        Controler CTR=new Controler(this);// aqui creamos nuestro hilo controler , este es el que se encarga de esuchar a los usuarios que se van conectando 
        Thread t = new Thread(CTR);
        t.start();// iniciamos el hilo y la ventana se abre 
    }

    @Override
    public void update(Observable o, Object arg) {
        String Text=(String) arg;
        Log.append(Text+"\n");// cuando el hilo controler llama el update se muesta el texto que mando en el registro 
    }
    
    public void RecordLog(){// leemos todo el archivo de texto y le vamos poniendo cada linea al log 
        try
        {
            Scanner sc = new Scanner(this.Register);
             while (sc.hasNextLine()){
            Log.append(sc.nextLine()+"\n");
            }
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SaveLog() {// abrimos nuestro archivo de texto y le ponemos el texto que contenga el log 
        FileWriter FLW;
        try
        {
            FLW = new FileWriter(this.Register);
            String LOG=this.Log.getText();
            FLW.append(LOG);
            FLW.close();
        } catch (IOException ex)
        {
            Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
}
