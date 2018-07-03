/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.models;

import com.chat.utils.ChatConfig;
import com.chat.utils.ChatMiscUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sydney
 */
public class User implements Comparable{
    private String username="";
    private String email="";
    private String font="Times New Roman";
    private String fontColor = "000000";
    private String firstname ="";
    private String lastname="";
    private String password = "";
    private String accounttype="General";

    
    public User(String un, String e, String f, String fc, String fn, String ln, String p,String at){
        this.username = un;
        this.email =e;
        this.font = f;
        this.fontColor = fc;
        this.firstname = fn;
        this.lastname = ln;
        this.password =p;
        this.accounttype = at;
    }
    public User(String un, String e, String p, String fn, String ln){
        this.username = un;
        this.email =e;
        this.password =p;
        this.firstname = fn;
        this.lastname = ln;
        this.insert();
    }
    public void setFont(String font) {
        this.font = font;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFont() {
        return font;
    }

    public String getFontColor() {
        return fontColor;
    }

    public String getFirstname() {
        return firstname;
    }
    public String getPassword(){
        return this.password;
    }
    public String getLastname() {
        return lastname;
    }
    
    public String getAccounttype() {
        return accounttype;
    }
    public Group[] membership(){
        return Group.whereMember(this.getUsername(), true);
    }
    public Group[] ownedgroups(){
        return Group.ownedBy(this.getUsername());
    }
    public ChatRoom[] ownedRooms(){
        return ChatRoom.ownedBy(this.getUsername());
    }
    public boolean isMemberOf(int gid){
        boolean test = false;
        Group[] mem = this.membership();
        for(Group g:mem){
            if(g.getId() == gid){
                test = true;
                break;
            }
        }
        return test;
    }
    public static ArrayList<User> all(){
        ArrayList<User> grps = new ArrayList<User>();
        String query = "Select * From `users`;";
        ResultSet user = ChatConfig.DB.query(query);
        try {
            if(user != null){
                while(user.next()){
                    grps.add(new User(user.getString("username"), user.getString("email"),user.getString("font"),user.getString("font_color"), user.getString("first"), user.getString("last"), user.getString("password"),user.getString("account_type")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grps;
    }
    public static User get(String un){
       String query = "select * From `users` where username = \""+un+"\";";
       ResultSet user = ChatConfig.DB.query(query);
       User u = null;
        try {
            if(user != null){
                user.next();
                u = new User(user.getString("username"), user.getString("email"),user.getString("font"),user.getString("font_color"), user.getString("first"), user.getString("last"), user.getString("password"),user.getString("account_type"));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
       return u;
    }
    private void insert(){
        String query = "INSERT INTO `chatdb`.`users` (`email`, `username`, `first`, `last`,`font`,`font_color`,`password`, `account_type`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmnt;
        try {
            stmnt = ChatConfig.DB.makeStatment(query);
            stmnt.setString(1, this.email);
            stmnt.setString(2, this.username);
            stmnt.setString(3, this.firstname);
            stmnt.setString(4, this.lastname);
            stmnt.setString(5, this.font);
            stmnt.setString(6, this.fontColor);
            stmnt.setString(7, this.password);
            stmnt.setString(8, this.accounttype);
            stmnt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(){
        String query = "UPDATE users SET password = '"+this.password+"', font = '"+this.font+"', font_color='"+this.fontColor+"' where username = '"+this.username+"'";
        PreparedStatement stmnt;
        try {
            stmnt = ChatConfig.DB.makeStatment(query);
            stmnt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Group[] pendingJoin(){
        String query = String.format("Select `groups`.* From `groups` JOIN `requests` on `groups`.`id` = `requests`.`group` where `requests`.`user`  = '%s';", this.username);
        ArrayList<Group> grps = ChatMiscUtil.groupList(query);
        Group[] grplist = null;
        grplist = new Group[grps.size()];
        grplist = grps.toArray(grplist);
        return grplist;
    }
    public Group[] canRequest(){
        ArrayList<Group> grps = Group.all();
        int[] vals = {-1,1};
        grps = ChatMiscUtil.MultiNotEqualFilter(grps, Group.whereMember(this.getUsername(), true));
        grps = ChatMiscUtil.MultiNotEqualFilter(grps, this.pendingJoin());
        int i =0;
        Group[] grpsarr = null;
        grpsarr = new Group[grps.size()];
        grpsarr = grps.toArray(grpsarr);
        ChatMiscUtil.bubbleSort(grpsarr, true);
        return grpsarr;
    }
    public int inviteCnt(){
        return Request.invitesForCnt(username);
    }
    public int requestCnt(){
        return Request.requestForCnt(username);
    }
    public int sumittedCnt(){
        return Request.submittedByCnt(username);
    }
    public Request[] requests(){
        return Request.requestFor(username);
    }
    public Request[] invites(){
        return Request.invitesFor(username);
    }
    public Request[] submitted(){
        return Request.submittedBy(username);
    }
    public boolean loginchk(String pass){
        return this.password.equals(pass);
    }
    public static boolean availableCheck(String un){
        return datachk("username", un);
    }
    public static boolean emailCheck(String e){
        return datachk("email", e);
    }
    private static boolean datachk(String p, String v){
        String query = String.format("Select Count(*) as Res From users where %s = '%s'", p, v);
        ResultSet res = ChatConfig.DB.query(query);
        int cnt =0;
        try {
            if(res != null){
                res.next();
                cnt = res.getInt("Res");
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
       return (cnt <= 0);
    }
    public String toString(){ 
        return String.format("User( %s, %s, %s, %s, %s, %s, %s, %s); ", this.email, this.username, this.password, this.firstname,this.lastname, this.font, this.fontColor, this.accounttype);
    }
    
    @Override
    public int compareTo(Object o) {
        int com = 0;
        if(o instanceof String){
            com = this.username.compareTo((String)o);
        }
        else if (o instanceof User){
            com = this.username.compareTo(((User)o).getUsername());
        }
        return com;
       
    }
    
    
}
