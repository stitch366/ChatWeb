/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.tags;

import com.chat.models.Group;
import com.chat.models.Request;
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
public class RequestListTag extends SimpleTagSupport{
    private Request[] requests;
    private boolean showgroup;
    private boolean showuser;
    private String btntext;
    private String btnaction;
    private String btntext2;
    private String btnaction2;
    private boolean twobtns = false;
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
    public Request[] getRequests() {
        return requests;
    }

    public void setRequests(Request[] requests) {
        this.requests = requests;
    }

    public boolean isShowuser() {
        return showuser;
    }

    public void setShowuser(boolean showuser) {
        this.showuser = showuser;
    }
    

    public boolean isShowgroup() {
        return showgroup;
    }

    public void setShowgroup(boolean showgroup) {
        this.showgroup = showgroup;
    }

    public String getBtntext() {
        return btntext;
    }

    public void setBtntext(String btntext) {
        this.btntext = btntext;
    }

    public String getBtnaction() {
        return btnaction;
    }

    public void setBtnaction(String btnaction) {
        this.btnaction = btnaction;
    }

    public String getBtntext2() {
        return btntext2;
    }

    public void setBtntext2(String btntext2) {
        this.btntext2 = btntext2;
    }

    public String getBtnaction2() {
        return btnaction2;
    }

    public void setBtnaction2(String btnaction2) {
        this.btnaction2 = btnaction2;
    }

    public boolean getTwobtns() {
        return twobtns;
    }

    public void setTwobtns(boolean twobtns) {
        this.twobtns = twobtns;
    }
    private void header(){
       if(showuser){
           table.header("User");
       }
       if(showgroup){
           table.header("Group");
       }
       table.header("");
       if(twobtns){
           table.header("");
       }
    }
    private void row(Request request){
       ScrollRow r = new ScrollRow();
       if(showuser){
           r.user(request.getUser());
       }
       if(showgroup){
           Group g = Group.get(request.getGroup());
           r.cell(g.getGroupname());
       }
       String action = this.btnaction;
       action = action.replace("GID", ""+request.getGroup());
       action = action.replace("USER", ""+request.getUser());
       r.action(action, btntext);
       if(twobtns){
           action = this.btnaction2;
           action = action.replace("GID", ""+request.getGroup());
           action = action.replace("USER", ""+request.getUser());
           r.action(action, btntext2);
       }
       table.row(r);

    }
    @Override
    public void doTag() throws IOException{
        JspWriter out = getJspContext().getOut();
        this.header();
        for(Request req:requests){
            row(req);
        }
        out.print(table.html(this.id, css));
    }
}
