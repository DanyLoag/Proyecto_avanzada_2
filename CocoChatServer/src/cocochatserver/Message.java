/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochatserver;

import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public class Message {
    public int Option;
    public ArrayList<String> Content;
    public int Origin;
    public int Addressee;
    public Message(int Option, ArrayList<String> Content,int Adresee,int Origin) {
        this.Option = Option;
        this.Content = Content;
        this.Addressee= Adresee;
        this.Origin=Origin;
    }
    
}
