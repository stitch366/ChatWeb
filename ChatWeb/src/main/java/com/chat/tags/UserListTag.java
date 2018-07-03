/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.tags;

import com.chat.models.User;
import com.chat.utils.ChatMiscUtil;
import com.chat.utils.ScrollRow;
import com.chat.utils.ScrollTable;
import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Sydney
 */
public class UserListTag extends SimpleTagSupport{
    private User[] users;
    private boolean btn1;
    private boolean btn2;
    private String css;
    private ScrollTable table = new ScrollTable();
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }
    
    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public boolean isBtn1() {
        return btn1;
    }

    public void setBtn1(boolean btn1) {
        this.btn1 = btn1;
    }

    public boolean isBtn2() {
        return btn2;
    }

    public void setBtn2(boolean btn2) {
        this.btn2 = btn2;
    }
    
    private void header(){
       table.header("User");
       if(btn1){
           table.header("");
       }
       if(btn2){
           table.header("");
       }
    }
    private void row(User u){
        ScrollRow r = new ScrollRow();
        r.user(u.getUsername());
       if(btn1){
           r.action("invite/"+u.getUsername(), "Invite");
       }
       if(btn2){
           r.action("kick/"+u.getUsername(), "Kick");
       }
       table.row(r);
    }
    @Override
    public void doTag() throws IOException{
        JspWriter out = getJspContext().getOut();
        this.header();
        for(User g: users){
            row(g);
        }
        out.print(table.html(this.id, "sixfour "+this.css));
    }
}
