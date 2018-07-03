/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.models;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
/**
 *
 * @author Sydney
 */
public class CurrentUser {
    private User u;
    public CurrentUser(User u){
        this.u = u;
    }
    public String username(){
        return u.getUsername();
    }
    public String img(){
        String data="";
        UserImg img;
        try {
            img = new UserImg(u.getUsername());
            data = img.imgStr();
        } catch (IOException ex) {
            Logger.getLogger(CurrentUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public String first(){
        return u.getFirstname();
    }
    public String last(){
        return u.getLastname();
    }
    public String type(){
        return u.getAccounttype();
    }
    public String font(){
        return u.getFont();
    }
    public String fontColor(){
        return u.getFontColor();
    }
    public String email(){
        return u.getEmail();
    }
    public void setPassword(String pass){
        u.setPassword(pass);
    }
    public void setFontColor(String color){
        u.setFont(color);
    }
    public void setFont(String font){
        u.setFont(font);
    }
    public void update(){
        u.update();
    }
    public Group[] membership(boolean withOwned){
        return Group.whereMember(u.getUsername(),withOwned);
    }
    public Group[] ownedgroups(){
        return Group.ownedBy(u.getUsername());
    }
    public ChatRoom[] ownedRooms(){
        ChatRoom[] test = ChatRoom.ownedBy(u.getUsername());
        return ChatRoom.ownedBy(u.getUsername());
    }
    public Group[] canRequest(){
        return u.canRequest();
    }
    public Request[] requests(){
        return u.requests();
    }
    public Request[] submitted(){
        return u.submitted();
    }
    public Request[] invites(){
        return u.invites();
    }
    public int requestCnt(){
        return u.requestCnt();
    }
    public int submittedCnt(){
        return u.sumittedCnt();
    }
    public int invtieCnt(){
        return u.inviteCnt();
    }
    public boolean isMemberOf(int gid){
        boolean test = false;
        Group[] mem = this.membership(true);
        for(Group g:mem){
            if(g.getId() == gid){
                test = true;
                break;
            }
        }
        return test;
    }
    public boolean canVeiw(ChatRoom room){
        boolean test = false;
        if("PUBLIC".equals(room.getType())){
            test = true;
        }
        else if(this.isMemberOf(room.getGroup())){
            test = true;
        }
        return test;
    }
    public String toString(){
        return String.format("CurrentUser({ 'username':'%s', 'email':'%s','first':'%s','last':'%s', 'font':'%s', 'fontColor':'%s'", this.username(), this.email(), this.first(), this.last(), this.font(), this.fontColor());
    }
    
}
