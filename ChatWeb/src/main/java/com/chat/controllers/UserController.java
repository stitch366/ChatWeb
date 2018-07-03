/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.controllers;

import com.chat.config.SessionData;
import com.chat.froms.FontFrm;
import com.chat.froms.GroupFrm;
import com.chat.froms.PassFrm;
import com.chat.froms.RoomFrm;
import com.chat.froms.StringFrm;
import com.chat.models.ChatRoom;
import com.chat.models.Group;
import com.chat.models.Request;
import com.chat.models.UserImg;
import com.chat.utils.ChatConfig;
import com.chat.utils.ChatMiscUtil;
import com.chat.utils.ComboOpt;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Sydney
 */
@Controller
@Scope("session")
@RequestMapping(value="/user")
public class UserController {
    @Autowired private SessionData sd;
    @RequestMapping(value="/")
    public String index(Model model){
        return "user/index";
    }
    @RequestMapping(value="/newpublic",  method=RequestMethod.GET)
    public String newPublic(@ModelAttribute("init") String init, Model model){
        model.addAttribute("roomFrm", new RoomFrm());
        model.addAttribute("init", init);
        return "user/newpubchat";
    }
    @RequestMapping(value="/newgrp",  method=RequestMethod.GET)
    public String grouplist(Model model){
        model.addAttribute("groupFrm", new GroupFrm());
        return "user/newgrp";
    }
    @RequestMapping(value="/creategroup",  method=RequestMethod.POST)
    public String groupmake(@ModelAttribute("groupFrm") GroupFrm groupFrm, RedirectAttributes ra){
       boolean avail = Group.namechk(groupFrm.getGroupname());
       String redirect="redirect:/user/";
       if(avail){
           new Group(groupFrm.getGroupname(), groupFrm.getOwner());
       }
       else{
           redirect="redirect:/user/newgrp";
           ra.addFlashAttribute("err", "A Group with this name Already Exsists");
       }
       return redirect;
    }
    @RequestMapping(value="/newprivate",  method=RequestMethod.GET)
    public String newPrivate(@ModelAttribute("init") String init, Model model){
        
        String un = sd.getCu().username();
        Group[] mem = Group.whereMember(un, true);
        ComboOpt[] grpsel = new ComboOpt[mem.length];
        int i =0;
        for(Group g:mem){
            grpsel[i] = new ComboOpt(""+g.getId(), g.getGroupname());
            i+=1;
        }
        model.addAttribute("roomFrm", new RoomFrm());
        model.addAttribute("grps", grpsel);
        model.addAttribute("init", init);
        return "user/newgrpchat";
    }
    @RequestMapping(value="/publicRooms")
    public String publicrooms(Model model){
        ChatRoom[] rooms = ChatRoom.publicChats();
        model.addAttribute("rooms", rooms);
        return "user/publicRooms";
    }
    @RequestMapping(value="/makeRequest", method=RequestMethod.GET)
    public String makerequest(Model model){
        model.addAttribute("groups", sd.getCu().canRequest());
        return "user/makeRequest";
    }
    @RequestMapping(value="/mail/{cat}", method=RequestMethod.GET)
    public String mail(@PathVariable String cat, Model model){
        ChatMiscUtil.mailboxModel(cat, sd.getCu(), model);
        return "user/mail";
    }
    @RequestMapping(value="/mail/{act:^accept|deny$}/grp{id}", method=RequestMethod.GET)
    public String inviteact(@PathVariable String act, @PathVariable String id, Model model){
        Request r = new Request(Integer.parseInt(id), sd.getCu().username(),"INVITE");
        if(act.equals("deny")){
            r.delete();
        }
        else{
            r.accept();
        }
        return "redirect:/user/mail/invites";
    }
    @RequestMapping(value="/mail/{act:^accept|deny$}/grp{id}/{user}", method=RequestMethod.GET)
    public String requesteact(@PathVariable String act, @PathVariable String id, @PathVariable String user, Model model){
        Request r = new Request(Integer.parseInt(id), user,"REQUEST");
        if(act.equals("deny")){
            r.delete();
        }
        else{
            r.accept();
        }
        return "redirect:/user/mail/requests";
    }
    @RequestMapping(value="/mail/cancel/grp{id}", method=RequestMethod.GET)
    public String cancelrequest(@PathVariable String act, @PathVariable String id,  Model model){
        Request r = new Request(Integer.parseInt(id), sd.getCu().username(),"REQUEST");
        r.delete();
        return "redirect:/user/mail/requests";
    }
    @RequestMapping(value="/request/grp{id}")
    public String createrequest(@PathVariable String id){
        Request req = new Request(Integer.parseInt(id), sd.getCu().username(),"REQUEST");
        req.insert();
        return "redirect:/user/makeRequest";
    }
    @RequestMapping(value="/changeFont", method=RequestMethod.GET)
    public String fontFrm(Model model){
        model.addAttribute("fontFrm", new FontFrm());
        ComboOpt[] opts = ChatConfig.FONTS.getFonts();
        model.addAttribute("fonts", opts);
        model.addAttribute("dcolor", sd.getCu().fontColor());
        model.addAttribute("dfont", sd.getCu().font());
        return "user/setFont";
    }
    @RequestMapping(value="/changeImg", method=RequestMethod.GET)
    public String avatarFrm(Model model){
        model.addAttribute("stringFrm", new StringFrm());
        return "user/setAvatar";
    }
    @RequestMapping(value="/setfont", method=RequestMethod.POST)
    public String fontFrm(@ModelAttribute("fontFrm") FontFrm fontFrm, RedirectAttributes ra){
        sd.getCu().setFont(fontFrm.getFont());
        sd.getCu().setFontColor(fontFrm.getColor());
        sd.getCu().update();
        return "redirect:/user/";
    }
    @RequestMapping(value="/passchange", method=RequestMethod.GET)
    public String passFrm(Model model){
        model.addAttribute("passFrm", new PassFrm());
        return "user/passchange";
    }
    @RequestMapping(value="/updatepass", method=RequestMethod.POST)
    public String fontFrm(@ModelAttribute("passFrm") PassFrm passFrm, RedirectAttributes ra){
        String redirect="redirect:/user/";
        if(!passFrm.validate(sd.getCu().username(), ra)){
            redirect+="passchange";
        }
        else{
            if(ChatConfig.ALLOW_ACCNT_MOD){
                sd.getCu().setPassword(passFrm.getNewpass());
                sd.getCu().update();
            }
        }
        return redirect;
    }
    @RequestMapping(value="/setimg", method=RequestMethod.POST)
    public String imgFrm(@ModelAttribute("stringFrm") StringFrm stringFrm, RedirectAttributes ra){
        UserImg img = new UserImg(stringFrm.getStr(), sd.getCu().username());
        try {
            img.save();
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/user/";
    }
    @RequestMapping(value="/createroom", method=RequestMethod.POST)
    public String createRoom(@ModelAttribute("roomFrm") RoomFrm roomFrm, RedirectAttributes ra){
        String redirect="redirect:/user/";
        boolean test = ChatRoom.check(roomFrm.getName().trim());
        if(!test){
            ra.addFlashAttribute("err", "A Chat Room with this name already exists.");
            if(roomFrm.getType().equals("PUBLIC")){
                redirect="redirect:/user/newpublic";
            }
            else{
                redirect="redirect:/user/newprivate";
            }
        }
        else{
            new ChatRoom(roomFrm.getName().trim(), roomFrm.getType(), roomFrm.getGroup(), sd.getCu().username());
        }
        return redirect;
    }
    
}
