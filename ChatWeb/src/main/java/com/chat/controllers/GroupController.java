/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.controllers;

import com.chat.config.SessionData;
import com.chat.models.Group;
import com.chat.models.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Sydney
 */
@Controller
@Scope("session")
@RequestMapping(value="/group")
public class GroupController {
    @Autowired private SessionData sd;
    private Group grp;
    @RequestMapping(value="/grp{id}" , method=RequestMethod.GET)
    public String room(@PathVariable String id, Model model){
        this.grp = Group.get(Integer.parseInt(id));
        boolean isowner = (sd.getCu().username()).equals(this.grp.getOwner());
        model.addAttribute("isowner", isowner);
        model.addAttribute("group", this.grp);
        return "group/grp";
    }
    @RequestMapping(value="/kick/{user}")
    public String kick(@PathVariable("user") String user){
        grp.removeMember(user);
        return "redirect:/group/grp"+grp.getId();
    }
    @RequestMapping(value="/invite/{user}")
    public String inviteUser(@PathVariable("user") String user){
        Request r = new Request(this.grp.getId(),user,"INVITE");
        r.insert();
        return "redirect:/group/invite";
    }
    @RequestMapping(value="/invite" , method=RequestMethod.GET)
    public String invite(Model model){
        boolean isowner = (sd.getCu().username()).equals(this.grp.getOwner());
        model.addAttribute("isowner", isowner);
        model.addAttribute("group", this.grp);
        return "group/invite";
    }
    @RequestMapping(value="/rooms" , method=RequestMethod.GET)
    public String rooms(Model model){
        boolean isowner = (sd.getCu().username()).equals(this.grp.getOwner());
        model.addAttribute("isowner", isowner);
        model.addAttribute("group", this.grp);
        return "group/rooms";
    }
    @RequestMapping(value="/sent" , method=RequestMethod.GET)
    public String sent(Model model){
        boolean isowner = (sd.getCu().username()).equals(this.grp.getOwner());
        model.addAttribute("isowner", isowner);
        model.addAttribute("group", this.grp);
        return "group/sent";
    }
    @RequestMapping(value="/uninvite/{user}" , method=RequestMethod.GET)
    public String uninvite(@PathVariable String user){
        Request r = new Request(this.grp.getId(),user,"INVITE");
        r.delete();
        return "redirect:/group/sent";
    }
    
}
