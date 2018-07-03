/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.controllers;

import com.chat.config.SessionData;
import com.chat.models.ChatRoom;
import com.chat.models.Group;
import com.chat.models.Message;
import com.chat.utils.ChatLog;
import com.chat.utils.ChatRepo;
import com.chat.utils.ChatTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Sydney
 */
@Controller
@RequestMapping(value="/chat")
public class ChatController {
    @Autowired private SessionData sd;
    private String prev;
    private ChatRoom room;
    private ChatRepo repo;
    private ChatLog live;
    
   
    @RequestMapping(value="/room{id}" , method=RequestMethod.GET)
    public String room(@PathVariable String id, Model model){
        this.room = ChatRoom.get(Integer.parseInt(id));
        this.repo = new ChatRepo(room.getName());
        this.live = this.repo.current();
        this.live.send(new Message("Server", sd.getCu().username()+" has joined the chat."));
        model.addAttribute("name", room.getName());
        model.addAttribute("id", room.getId());
        return "chat/room";
    }
    @RequestMapping(value="/leaving", method=RequestMethod.POST)
    @ResponseBody
    public String leave(HttpServletRequest req, HttpServletResponse res) {
        this.live.send(new Message("Server", sd.getCu().username()+" has left the chat."));
        return "";
    }
    @RequestMapping(value="/logs" , method=RequestMethod.GET)
    public String logs(Model model){
        model.addAttribute("name", room.getName());
        model.addAttribute("id", room.getId());
        model.addAttribute("logs", repo.loglist());
        return "chat/logs";
    }
    @RequestMapping(value="/log{date}" , method=RequestMethod.GET)
    String show(@PathVariable String date, Model model){
        ChatLog log = repo.getLog(date);
        log.upadtechat();
        model.addAttribute("data", log.RetriveAll());
        model.addAttribute("name", room.getName());
        model.addAttribute("log", date);
        return "chat/showlog";
    }
   
    @RequestMapping(value="/chatfeed", method=RequestMethod.POST)
    @ResponseBody
    public String chatfeed(HttpServletRequest req, HttpServletResponse res) {
        String time;
        int[] vals;
        int hours = Integer.parseInt(req.getParameter("hr"));
        if(prev != null){
            time = prev;   
            vals = new int[]{0,1};
        }
        else{
            time = ChatTime.HoursAgo(hours);
            vals = new int[]{0,1};
        }
        this.prev = ChatTime.timestamp();
        this.live.upadtechat();
        return this.live.filter(time, vals);
    }
    @RequestMapping(value="/sendmsg", method=RequestMethod.POST)
    @ResponseBody
    public String send(HttpServletRequest req, HttpServletResponse res) {
        String msg = req.getParameter("msg");
        String time = prev;
        int[] vals = vals = new int[]{0,1};
        this.live.send(new Message(sd.getCu().username(), msg));
        this.prev = ChatTime.timestamp();
        this.live.upadtechat();
        return this.live.filter(time, vals);
    }
}
