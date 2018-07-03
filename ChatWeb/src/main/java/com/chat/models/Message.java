/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.models;

import com.chat.utils.ChatConfig;
import com.chat.utils.ChatTime;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sydney
 */
public class Message implements Comparable {
    String timestamp;
    String sender;
    String text;
    int id = 0;
    public Message(String sender, String txt){
        this.sender = sender;
        this.text = txt.replaceAll("\n", "<br/>");
        this.timestamp = ChatTime.timestamp();
    }
    public Message(String csv, int id){
        String[] data = csv.split(",", 3);
        this.sender = data[1];
        this.timestamp = data[0];
        this.text = data[2];
        this.id = id;
    }
    public Message(String time, String sender, String msg){
        this.timestamp = time;
        this.sender = sender;
        this.text = msg;
    }
    public String csv(){
        return this.timestamp+","+this.sender+","+this.text+"\n";
    }
    public String json(){
        String data="";
        String style="";
        if(!"Server".equals(sender)){
            User u = User.get(this.sender);
            UserImg ui = null;
            try {
                ui = new UserImg(this.sender);
                style = "\"color\": \"#"+u.getFontColor()+"\", \"font\": \""+u.getFont()+"\"";
                data="\"img\":\""+ui.imgStr()+"\", "+style;
            } catch (IOException ex) {
                Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            style = "\"color\": \"#"+ChatConfig.SeverColor+"\", \"font\": \""+ChatConfig.ServerFont+"\"";
            //style = "color: #"+ChatConfig.SeverColor+"; font-family: '"+ChatConfig.ServerFont+"'";
            data="\"img\":\"\", "+style;
        }
        
        return "{\"id\":"+this.id+", \"sender\":\""+this.sender+"\", \"time\":\""+this.timestamp+"\", \"msg\":\""+this.text+"\", "+data+"}";
    }
    public int compareTo(String time){
        return ChatTime.parseStamp(this.timestamp).compareTo(ChatTime.parseStamp(time));
    }
    public int compareTo(Message msg){
        return this.compareTo(msg.timestamp);
    }

    @Override
    public int compareTo(Object o) {
        int com = 0;
        if(o instanceof String){
            com = this.compareTo((String)o);
        }
        else if (o instanceof Message){
            com = this.compareTo((Message)o);
        }
        return com;
    }
}
