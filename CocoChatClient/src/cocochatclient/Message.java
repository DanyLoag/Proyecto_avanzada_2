/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochatclient;

import Models.Group;
import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public class Message {

    public int Option;
    public String Content;
    public int Origin;
    public int IdGroup;
    public int IdFriendShip;
    public Group group;

    public Message(int Option, String Content, int Origin, int IdGroup) {
        this.Option = Option;
        this.Content = Content;
        this.Origin = Origin;
        this.IdGroup = IdGroup;
    }
    public Message(int Option,int Origin) {
        this.Option = Option;
        this.Origin=Origin;
    }

    public Message(int Option, int Origin, int IdFriendShip) {
        this.Option = Option;
        this.Origin = Origin;
        this.IdFriendShip = IdFriendShip;
    }

    public Message(int Option, Group group) {
        this.Option = Option;
        this.group = group;
    }
    
    
    

    public Message(int Option, String Content, int Origin) {
        this.Option = Option;
        this.Content = Content;
        this.Origin = Origin;
    }
    
    
    
}
