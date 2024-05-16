/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochatserver;

import BD.Models.Group;
import java.util.ArrayList;

/**
 *
 * Esta clase es usada para comunicar el hilo de cada cliente con el hilo controler , para que podamos empaquetar cada mensaje en un solo objeto
 */
public class Message {// esta clase nos sirve para comunicar mas facil entre todos los hilos, teniendo control de todo lo que necesitamos mandar
    
    public int Option;
    public ArrayList<String> Content;
    public int Origin;
    public int Addressee;
    public boolean accepted;
    public int UserFriendId;
    public Group group;
    public ArrayList<Integer> Users;
    public Message(int Option, ArrayList<String> Content,int Adresee,int Origin) {
        this.Option = Option;
        this.Content = Content;
        this.Addressee= Adresee;
        this.Origin=Origin;
    }

    public Message(int Option, int Origin, int Addressee,boolean accepted,int UserFriendId) {
        this.Option = Option;
        this.Origin = Origin;
        this.Addressee = Addressee;
        this.accepted=accepted;
        this.UserFriendId=UserFriendId;
    }

    public Message(int Option, int Origin, Group group) {
        this.Option = Option;
        this.Origin = Origin;
        this.group = group;
    }
    
    public Message(int Option, int Origin, int Addressee){
        this.Option = Option;
        this.Origin = Origin;
        this.Addressee = Addressee;
    }
    
    
}
