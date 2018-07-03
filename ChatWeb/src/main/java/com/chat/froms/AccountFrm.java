/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.froms;

import com.chat.models.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Sydney
 */
public class AccountFrm {
    private String username="";
    private String email="";
    private String firstname ="";
    private String lastname="";
    private String password = "";
    private String confirmpass ="";

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        boolean passempty = isempty(this.password, "passerr", ra);
        boolean confirmempty = isempty(this.confirmpass, "confirmerr", ra);
        boolean test = true;
        if((!passempty) && (!confirmempty) ){
            if(!this.password.equals(this.confirmpass)){
                test = false;
                ra.addFlashAttribute("confirmerr", "Passwords Don't match");
            }
        }
        else{
            test = false;
        }
        return test;
    }
    private boolean usernamecheck(RedirectAttributes ra){
        boolean empty = isempty(this.username, "unerr", ra);
        boolean test = true;
        if(!empty){
            test = User.availableCheck(this.username);
            if(!test){
                ra.addFlashAttribute("unerr", "An account with this username already exists.");
            }
        }
        else{
            test =false;
        }
        return test;
    }
    private boolean emailcheck(RedirectAttributes ra){
        String regexstr = "^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern patt = Pattern.compile(regexstr);
        boolean empty = isempty(this.email, "emailerr", ra);
        boolean test = true;
        if(!empty){
            Matcher m = patt.matcher(this.email);
            test = m.matches();
            if(!test){
                ra.addFlashAttribute("emailerr", "Invaild value for email address entered.");
            }
            else{
                test = User.emailCheck(this.email);
                if(!test){
                    ra.addFlashAttribute("emailerr", "An account with this email address already exists.");
                }
            }
        }
        else{
            test =false;
        }
        return test;
    }
    
    public boolean validate(RedirectAttributes ra){
        boolean test = true;
        boolean[] tests = new boolean[]{passCheck(ra), usernamecheck(ra), emailcheck(ra), (! isempty(this.firstname, "firsterr", ra)),(! isempty(this.lastname, "lasterr", ra))};
        for(boolean t:tests){
            test = t;
            if(!test){
                break;
            }
        }
        if(!test){
            ra.addFlashAttribute("name", this.username);
            ra.addFlashAttribute("email", this.email);
            ra.addFlashAttribute("pass", this.password);
            ra.addFlashAttribute("confirm", this.confirmpass);
            ra.addFlashAttribute("first", this.firstname);
            ra.addFlashAttribute("last", this.lastname);
        }
        return test;
    }
    public void createUser(){
        new User(this.username, this.email, this.password, this.firstname, this.lastname);
    }
}
