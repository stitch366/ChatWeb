/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.tags;

import com.chat.models.CurrentUser;
import com.chat.utils.ComboOpt;
import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Sydney
 */
public class InboxTag extends SimpleTagSupport{
    private CurrentUser cu;
    private boolean inMailbox = false;
    public CurrentUser getCu() {
        return cu;
    }

    public void setCu(CurrentUser cu) {
        this.cu = cu;
    }
    private String row(String base, String link, int amt){
        String text = String.format(base+"&nbsp;&nbsp;<span class='mail-amt'>(%d)</span>", amt);
        String row = String.format("<tr><td><a href='%s'>%s</a></td></tr>", link, text);
        return row;
    }

    public boolean getInMailbox() {
        return inMailbox;
    }

    public void setInMailbox(boolean inMailbox) {
        this.inMailbox = inMailbox;
    }
    private String[] setLinks(){
        String add = (!this.inMailbox)? "../user/":"../";
        String[] baselinks = new String[]{"mail/invites", "mail/requests", "mail/submitted"};
        for(int x =0; x <baselinks.length; x++){
            baselinks[x] = add+baselinks[x];
        }
        return baselinks;
    }
    @Override
    public void doTag() throws IOException{
        JspWriter out = getJspContext().getOut();
        String innards = "<table class='mailbox'><thead><tr><th>Mailbox</th></tr></thead><tbody>";
        String[] links = this.setLinks();
        innards += row("Invites", links[0], cu.invtieCnt());
        innards += row("Requests", links[1], cu.requestCnt());
        innards += row("Your Requests", links[2], cu.submittedCnt());
        innards += "</tbody></table>";
        out.print(innards);
        
    }
}
