/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.tags;

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
public class GroupListTag extends SimpleTagSupport{
    private Group[] grps;
    private boolean btn;
    private boolean showowner;
    private boolean aslink;
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
    public Group[] getGrps() {
        return grps;
    }

    public void setGrps(Group[] grps) {
        this.grps = grps;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public boolean isShowowner() {
        return showowner;
    }

    public void setShowowner(boolean showowner) {
        this.showowner = showowner;
    }

    public boolean isAslink() {
        return aslink;
    }

    public void setAslink(boolean aslink) {
        this.aslink = aslink;
    }
    
    private void header(){
       table.header("Group");
       if(showowner){
           table.header("Owner");
       }
       if(btn){
           table.header("");
       }
    }
    private void row(Group grp){
        ScrollRow r = new ScrollRow();
        if(aslink){
            r.groupLink(grp, false);
        }
        else{
            r.cell(grp.getGroupname());
        }
       if(showowner){
           r.user(grp.getOwner());
       }
       if(btn){
           r.action("request/grp"+grp.getId(), "Request");
       }
       table.row(r);
    }
    @Override
    public void doTag() throws IOException{
        JspWriter out = getJspContext().getOut();
        this.header();
        for(Group g: grps){
            row(g);
        }
        out.print(table.html(this.id, this.css));
    }
}
