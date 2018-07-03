/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.models;

import com.chat.utils.ChatConfig;
import com.chat.utils.ChatMiscUtil;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sydney
 */
public class ChatRoom implements Comparable{
    private int id;
    private String name;
    private String type;
    private int group;
    private String owner;

    public ChatRoom(int id, String name, String type, int group, String owner) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.group = group;
        this.owner = owner;
        File dir = new File(ChatConfig.CHAT_ROOM_STORE+"\\"+this.name);
        if (!dir.exists()){
            dir.mkdir();
        }
    }
    public ChatRoom(String name, String type, int group, String owner) {
        this.name = name;
        this.type = type;
        this.group = group;
        this.owner = owner;
        this.insert();
        new File(ChatConfig.CHAT_ROOM_STORE+"\\"+this.name).mkdirs();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    public int getGroup(){
        return this.group;
    }
    public String grpname(){
        String n="";
        if(this.group != 0){
            n=Group.get(this.group).getGroupname();
        }
        return n;
    }
    public static boolean check(String name){
        File store = new File(ChatConfig.CHAT_ROOM_STORE);
        File[] list = store.listFiles();
        boolean test = true;
        for (File f:list) {
          if(f.getName().equals(name)){
            test = false;
            break;
          }
        }
        return test;
    }
    private void insert(){
        String query =String.format("INSERT INTO `chatdb`.`rooms` (`name`,`type`,`group`,`owner`) VALUES ('%s','%s', %s , '%s' );", this.name, this.type, (""+this.group),this.owner);
        String query2 = "Select id From rooms where `name` = '"+this.name+"' and `owner`= '"+this.owner+"';";
        PreparedStatement stmnt;
        try {
            stmnt = ChatConfig.DB.makeStatment(query);
            stmnt.execute();
            ResultSet resid = ChatConfig.DB.query(query2);
            if(resid != null){
                this.id = resid.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ChatRoom get(int id){
        String query = "Select * From rooms where `id` = "+id+";";
        ResultSet grp = ChatConfig.DB.query(query);
        ChatRoom g = null;
        try {
            if(grp != null){
                grp.next();
                g = ChatMiscUtil.loadRoom(grp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    }
    private static <E> ChatRoom[] data(String prop, E val){
        String query = "Select * From rooms where rooms.`"+prop+"` = '"+val+"';";
        ArrayList<ChatRoom> grps = new ArrayList<ChatRoom>();
        grps = ChatMiscUtil.roomList(query);
        ChatRoom[] grpsarr = null;
        grpsarr = new ChatRoom[grps.size()];
        grpsarr = grps.toArray(grpsarr);
        return grpsarr;
    }
    public static ChatRoom[] ownedBy(String un){
        return data("owner", un);
    }
    
    public static ChatRoom[] groupChats(int gid){
        return data("group", gid);
    }
    public static ChatRoom[] publicChats(){
        ArrayList<ChatRoom> grps = new ArrayList<ChatRoom>();
        grps = ChatMiscUtil.roomList("select * from chatdb.rooms where rooms.type = 'PUBLIC';");
        ChatRoom[] grpsarr = null;
        grpsarr = new ChatRoom[grps.size()];
        grpsarr = grps.toArray(grpsarr);
        return grpsarr;
    }
    @Override
    public int compareTo(Object o) {
        int com = 0;
        if(o instanceof String){
            com = this.name.compareTo((String)o);
        }
        else if (o instanceof ChatRoom){
            com = this.name.compareTo(((ChatRoom)o).getName());
        }
        return com;
       
    }
}
