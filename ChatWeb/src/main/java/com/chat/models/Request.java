/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.models;

import com.chat.utils.ChatConfig;
import com.chat.utils.ChatMiscUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sydney
 */
public class Request {
    private int group;
    private String user;
    private String type;

    public Request(int group, String user, String type) {
        this.group = group;
        this.user = user;
        this.type = type;
    }
    public void insert(){
        String query = String.format("INSERT INTO `requests` (`group`,`user`,`type`) VALUES ('%s', '%s', '%s');", ""+this.group, this.user, this.type);
        ChatMiscUtil.runStmt(query);
    }
    public void delete(){
        String query = String.format("Delete From `requests` where `group` = '%s' AND `user`='%s' AND `type` = '%s';", ""+this.group, this.user, this.type);
        ChatMiscUtil.runStmt(query);
    }
    public void accept(){
        Group g = Group.get(this.group);
        g.addMember(this.user);
        this.delete();
    }
    private static String makeQuery(boolean grpjoin, boolean count, String prop, String propval, String type){
        String sel = (count)?"count(*) AS 'num'": ((!grpjoin)?"*": "`requests`.*");
        String from = (!grpjoin)? "`requests`": "`requests` JOIN `groups` on groups.id = `requests`.`group`";
        String where = String.format("`type` = '%s' AND %s = '%s' ;", type, prop, propval);
        return String.format("Select %s From %s Where %s;", sel, from, where);
    }
    public static int invitesForCnt(String user){
        String set = makeQuery(false, true, "`user`", user, "INVITE");
        return ChatMiscUtil.count(set);
    }
    public static Request[] invitesFor(String user){
        String set = makeQuery(false, false, "`user`", user, "INVITE");
        ArrayList<Request> grps = new ArrayList<Request>();
        grps = ChatMiscUtil.requestList(set);
        Request[] grpsarr = null;
        grpsarr = new Request[grps.size()];
        grpsarr = grps.toArray(grpsarr);
        return grpsarr;
    }
    public static int requestForCnt(String user){
        String set = makeQuery(true, true, "`groups`.`owner`", user, "REQUEST");
        return ChatMiscUtil.count(set);
    }
    public static Request[] requestFor(String user){
        String set = makeQuery(true, false, "`groups`.`owner`", user, "REQUEST");
        ArrayList<Request> grps = new ArrayList<Request>();
        grps = ChatMiscUtil.requestList(set);
        Request[] grpsarr = null;
        grpsarr = new Request[grps.size()];
        grpsarr = grps.toArray(grpsarr);
        return grpsarr;
    }
    public static Request[] submittedBy(String user){
        String set = makeQuery(false, false, "`user`", user, "REQUEST");
        ArrayList<Request> grps = new ArrayList<Request>();
        grps = ChatMiscUtil.requestList(set);
        Request[] grpsarr = null;
        grpsarr = new Request[grps.size()];
        grpsarr = grps.toArray(grpsarr);
        return grpsarr;
    }
    public static int submittedByCnt(String user){
        String set = makeQuery(false, true, "`user`", user, "REQUEST");
        return ChatMiscUtil.count(set);
    }
    public static Request[] invitesTo(int group){
        String set = makeQuery(false, false, "`group`", ""+group, "INVITE");
        ArrayList<Request> grps = new ArrayList<Request>();
        grps = ChatMiscUtil.requestList(set);
        Request[] grpsarr = null;
        grpsarr = new Request[grps.size()];
        grpsarr = grps.toArray(grpsarr);
        return grpsarr;
    }
    public static int invitesToCnt(int group){
        String set = makeQuery(false, true, "`group`", ""+group, "INVITE");
        return ChatMiscUtil.count(set);
    }
    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
