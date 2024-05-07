/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochatclient;

import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public class Message {

    public int Option;
    public ArrayList<String> Content=new ArrayList<>();
    public int Origin;
    public Message(int Option,int Origin) {
        this.Option = Option;
        this.Origin=Origin;
    }
}
