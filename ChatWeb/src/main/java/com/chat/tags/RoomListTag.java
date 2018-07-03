/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.tags;

import com.chat.models.ChatRoom;
import com.chat.models.Group;
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
public class RoomListTag extends SimpleTagSupport{
    private ChatRoom[] rooms;
    private boolean showtype;
    private boolean showgroup;
    private boolean showowner;
    private ScrollTable table = new ScrollTable();
    private String css;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public ChatRoom[] getRooms() {
        return rooms;
    }

    public void setRooms(ChatRoom[] rooms) {
        this.rooms = rooms;
    }

    public boolean isShowtype() {
        return showtype;
    }

    public void setShowtype(boolean showtype) {
        this.showtype = showtype;
    }

    public boolean isShowgroup() {
        return showgroup;
    }

    public void setShowgroup(boolean showgroup) {
        this.showgroup = showgroup;
    }

    public boolean isShowowner() {
        return showowner;
    }

    public void setShowowner(boolean showowner) {
        this.showowner = showowner;
    }

    public ScrollTable getTable() {
        return table;
    }

    public void setTable(ScrollTable table) {
        this.table = table;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }
    
    private void header(){
       table.header("Room");
       if(showtype){
           table.header("Type");
       }
       if(showgroup){
           table.header("Group");
       }
       if(showowner){
           table.header("Owner");
       }
    }
    private void row(ChatRoom room){
        ScrollRow r = new ScrollRow();
        r.room(room);
        if(showtype){
           r.cell(room.getType());
       }
       if(showgroup){
           if(room.getGroup() == 0){
               r.cell("");
           }
           else{
               r.groupLink(Group.get(room.getGroup()), false);
           }
       }
       if(showowner){
           r.user(room.getOwner());
       }
       table.row(r);
    }
    @Override
    public void doTag() throws IOException{
        JspWriter out = getJspContext().getOut();
        this.header();
        for(ChatRoom room:rooms){
            row(room);
        }
        out.print(table.html(this.id, this.css));
    }
}
