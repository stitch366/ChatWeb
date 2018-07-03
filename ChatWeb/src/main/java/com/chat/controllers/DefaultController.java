/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.controllers;

import com.chat.config.SessionData;
import com.chat.froms.AccountFrm;
import com.chat.froms.LoginFrm;
import com.chat.models.CurrentUser;
import com.chat.models.User;
import com.chat.models.UserImg;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import com.chat.utils.ChatConfig;
import com.chat.utils.ChatTime;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author john
 */
@Controller
public class DefaultController {
   @Autowired private SessionData sd;
   @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(@ModelAttribute("uinit") String uinit, Model model){
        model.addAttribute("loginFrm", new LoginFrm());
        model.addAttribute("FontList", ChatConfig.FONTS.getFonts());
        model.addAttribute("uinit", uinit);
        return "home/index";
    }
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@ModelAttribute("loginFrm") LoginFrm loginFrm, RedirectAttributes ra){
        String uname = loginFrm.getUname();
        String p = loginFrm.getPass();
        String redirect="redirect:/user/";
        User u = User.get(uname);
        boolean test = true;
        if(u == null){
            test = false;
        }
        else{
            test = u.loginchk(p);
        }
        if(!test){
            ra.addFlashAttribute("un", uname);
            ra.addFlashAttribute("err", "Username or Password is Incorrect.");
            redirect="redirect:/";
        }
        else{
            sd.setCu(new CurrentUser(u));
        }
        return redirect;
    }
    @RequestMapping(value="/newuser", method=RequestMethod.GET)
    public String newuser(Model model){
        model.addAttribute("accntFrm", new AccountFrm());
        return "home/newuser";
    }
    @RequestMapping(value="/adduser", method=RequestMethod.POST)
    public String adduser(@ModelAttribute("accntFrm") AccountFrm accntFrm, RedirectAttributes ra){
        String redirect="redirect:/";
        if(!accntFrm.validate(ra)){
            redirect+="newuser";
        }
        else{
            if(ChatConfig.ALLOW_ACCNT_MOD){
                accntFrm.createUser();
            }
        }
        return redirect;
    }
}
