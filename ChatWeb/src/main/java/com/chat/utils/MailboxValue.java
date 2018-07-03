/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.utils;

import com.chat.models.CurrentUser;
import com.chat.models.Request;
import org.springframework.ui.Model;

/**
 *
 * @author Sydney
 */
public enum MailboxValue {
    INVITES("invites", true ,false, "accept/grpGID", "deny/grpGID", "Accept", "Decline", "threeCols1"), REQUESTS("requests", true ,true, "accept/grpGID/USER", "deny/grpGID/USER", "Allow", "Deny", "sixfour fourCols"), SUBMITTED("submitted", false, false, "cancel/grpGID", "", "Cancel", "", "twoCols2");
    private String veiw;
    private boolean twobtn;
    private boolean showuser;
    private String btnaction1;
    private String btnaction2;
    private String btntxt1;
    private String btntxt2;
    private String css;
    MailboxValue( String veiw, boolean twobtn, boolean showuser,String btnaction1, String btnaction2, String btntxt1, String btntxt2, String css) {
        this.veiw = veiw;
        this.twobtn = twobtn;
        this.showuser = showuser;
        this.btnaction1 = btnaction1;
        this.btnaction2 = btnaction2;
        this.btntxt1 = btntxt1;
        this.btntxt2 = btntxt2;
        this.css = css;
    }
    private Request[] mail(CurrentUser cu){
        Request[] items;
        if(this.veiw.equals("invites")){
            items = cu.invites();
        }
        else if(this.veiw.equals("requests")){
            items = cu.requests();
        }
        else{
            items = cu.submitted();
        }
        return items;
    }
    public void ConfigModel(Model m, CurrentUser cu){
        m.addAttribute("mail", this.mail(cu));
        m.addAttribute("veiw", this.veiw);
        m.addAttribute("twobtn", this.twobtn);
        m.addAttribute("showuser", this.showuser);
        m.addAttribute("btnact1", this.btnaction1);
        m.addAttribute("btnact2", this.btnaction2);
        m.addAttribute("btntxt1", this.btntxt1);
        m.addAttribute("btntxt2", this.btntxt2);
        m.addAttribute("css", this.css);
    }
}
