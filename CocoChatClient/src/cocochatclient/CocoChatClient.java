/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cocochatclient;

import Hilos.HiloServidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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
            Socket sc = new Socket("192.168.137.1",5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            String MSG=in.readUTF();
            System.out.println(MSG);
            int id=sn.nextInt();
            out.writeInt(id);
            HiloServidor HiloServidor=new HiloServidor(in);
            Thread TS=new Thread(HiloServidor);
            TS.start();
            outerLoop:while(true){
            System.out.print("Menu:\n[1]EnviarMensaje\n[2]Mensaje Grupo\n[0]Salir");
            int OP=sn.nextInt();
            out.writeInt(OP);
            int Destino;
            switch(OP){
                case 1:
                    System.out.print("Destino: ");
                    sn=new Scanner (System.in);
                    Destino=sn.nextInt();
                    out.writeInt(Destino);    
                    System.out.print("MSG: ");
                    sn=new Scanner (System.in);
                    MSG=sn.nextLine();
                    out.writeUTF(MSG);
                    break;
                case 2:
                    System.out.print("Grupo: ");
                    sn=new Scanner (System.in);
                    Destino=sn.nextInt();
                    out.writeInt(Destino);    
                    System.out.print("MSG: ");
                    sn=new Scanner (System.in);
                    MSG=sn.nextLine();
                    out.writeUTF(MSG);
                    break;
                case 0:
                    System.out.println("ConexionTerminada");
                    break outerLoop;
                default:
                    System.out.println("Opcion no valida");
            }
              
            }
            HiloServidor.stop();
                try {
                    TS.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(CocoChatClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            System.out.print('A');
        } catch (IOException ex) {
            Logger.getLogger(CocoChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}