/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.froms;

import com.chat.models.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Sydney
 */
public class PassFrm {
    public String old;
    public String confirm;
    public String newpass;

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }
    
    private boolean isempty(String value, String errval, RedirectAttributes ra){
        boolean test = false;
        if(value == null || value.equals("")|| value.equals(" ")){
            test = true;
            ra.addFlashAttribute(errval, "Feild may not be empty");
        }
        return test;
    }
    private boolean passCheck(RedirectAttributes ra){
        boolean passempty = isempty(this.newpass, "newerr", ra);
        boolean confirmempty = isempty(this.confirm, "confirmerr", ra);
        boolean test = true;
        if((!passempty) && (!confirmempty) ){
            if(!this.newpass.equals(this.confirm)){
                test = false;
                ra.addFlashAttribute("confirmerr", "Passwords Don't match");
            }
        }
        else{
            test = false;
        }
        return test;
    }
    private boolean oldCheck(String user, RedirectAttributes ra){
        User u = User.get(user);
        boolean test = true;
        boolean empty = isempty(this.old, "olderr", ra);
        if(!empty){
            if(!u.getPassword().equals(this.old)){
                test = false;
                ra.addFlashAttribute("olderr", "Incorrect password");
            }
        }
        else{
            test = false;
        }
        return test;
    }
    public boolean validate(String user, RedirectAttributes ra){
        boolean test = true;
        boolean[] tests = new boolean[]{passCheck(ra), oldCheck(user, ra)};
        for(boolean t:tests){
            test = t;
            if(!test){
                break;
            }
        }
        if(!test){
            ra.addFlashAttribute("old", this.old);
            ra.addFlashAttribute("newp", this.newpass);
            ra.addFlashAttribute("confirm", this.confirm);
        }
        return test;
    }
}
