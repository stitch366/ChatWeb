/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.utils;

import com.chat.models.ChatRoom;
import com.chat.models.Group;
import java.util.ArrayList;

/**
 *
 * @author Sydney
 */
public class ScrollRow {
    private ArrayList<String> cells;
    private final String cell = "<div class='scroll-cell'><span>%s</span></div>";
    private final String row = "<div class='scroll-row'>%s</div>";
    private final String grplink = "<a href='%s'>%s</a>";
    private final String croom = "<a href='../chat/room%s'>%s</a>";
    private final String act = "<a class='crtbtn' href='%s'>%s</a>";
    public ScrollRow(){
        this.cells=new ArrayList<String>();
    }
    public void cell(String content){
        this.cells.add(String.format(cell, content));
    }
    public void user(String uname){
        cell(ChatMiscUtil.userId(uname, true));
    }
    public void groupLink(Group g, boolean inmailbox){
        String href = (inmailbox)?"../../group/grp"+g.getId(): "../group/grp"+g.getId();
        cell(String.format(grplink, href, g.getGroupname()));
    }
    public void room(ChatRoom room){
        cell(String.format(croom, ""+room.getId(), room.getName()));
    }
    public void action(String action, String text){
        cell(String.format(act, action, text));
    }
    public String html(){
        String rstr="";
        for(String c:this.cells){
            rstr+=c;
        }
        return String.format(row, rstr);
    }
}
