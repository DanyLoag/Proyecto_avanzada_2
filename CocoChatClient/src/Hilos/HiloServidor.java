/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hilos;

import cocochatclient.Message;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */

public class HiloServidor extends Observable implements Runnable{
    DataInputStream in;
    private volatile boolean Running; 

    public HiloServidor(DataInputStream in) {
        this.in = in;
        this.Running=true;
    }
    public void stop(){
        this.Running=false;
    }

    @Override
    public void run() {
        while(this.Running){
            try {
                int Op=in.readInt();
                switch(Op){
                    case -2 ->{
                        int User=in.readInt();
                        Message MSG=new Message(Op,User);
                        this.setChanged();
                        this.notifyObservers(MSG);
                        this.clearChanged();
                    }
                    case -1 ->{
                        int User=in.readInt();
                        Message MSG=new Message(Op,User);
                        this.setChanged();
                        this.notifyObservers(MSG);
                        this.clearChanged();
                        
                    }
                    case 1 -> {
                        int User=in.readInt();
                        String MSG=in.readUTF();
                        System.out.println("\n"+User+": "+MSG);
                        Message message=new Message(Op,MSG,User);
                        this.setChanged();
                        this.notifyObservers(message);
                        this.clearChanged();
                    }
                    case 2->{
                        int Group=in.readInt();
                        int User=in.readInt();
                        String MSG=in.readUTF();
                        System.out.println("Group("+Group+")-"+User+": "+MSG);
                        Message message=new Message(Op,MSG,User,Group);
                        this.setChanged();
                        this.notifyObservers(message);
                        this.clearChanged();
                    }
                    case 0 -> {
                        this.Running=false;
                    }
                }      
            } catch (IOException ex) {
                Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Hilo Terminado");
    }
}
