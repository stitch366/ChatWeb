/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.tags;

import com.chat.models.CurrentUser;
import com.chat.models.Group;
import com.chat.utils.ScrollRow;
import com.chat.utils.ScrollTable;
import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Sydney
 */
public class SidebarGroupListTag extends SimpleTagSupport{
    private CurrentUser cu;
    private String set;
    private String title;
    private boolean inMailbox;
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
    public CurrentUser getCu() {
        return cu;
    }

    public void setCu(CurrentUser cu) {
        this.cu = cu;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getInMailbox() {
        return inMailbox;
    }

    public void setInMailbox(boolean inMailbox) {
        this.inMailbox = inMailbox;
    }
    
    private Group[] Grps(){
        Group[] grps;
        if(set.equals("owned")){
            grps = cu.ownedgroups();
        }
        else{
            grps = cu.membership(false);
        }
        return grps;
    }
    private void row(Group grp){
        ScrollRow r = new ScrollRow();
        r.groupLink(grp,inMailbox);
        table.row(r);
    }
    @Override
    public void doTag() throws IOException{
        JspWriter out = getJspContext().getOut();
        Group[] grps = this.Grps();
        table.header(this.title);
        for(Group g: grps){
            row(g);
        }
        out.print(table.html(id, "oneCol "+this.css));
    }
}
