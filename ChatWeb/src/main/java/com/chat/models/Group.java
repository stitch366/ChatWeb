/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.chat.utils.ChatConfig;
import com.chat.utils.ChatMiscUtil;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Sydney
 */
public class Group implements Comparable {
    private int id;
    private String groupname;
    private String owner;
    public Group(int i, String g, String o){
        this.id =i;
        this.groupname = g;
        this.owner = o;
    }
    public Group(String g, String o){
        this.groupname = g;
        this.owner = o;
        this.insert();
    }

    public int getId() {
        return id;
    }

    public String getGroupname() {
        return groupname;
    }

    public String getOwner() {
        return owner;
    }
    
    public static Group get(int id){
        String query = "Select * From groups where `id` = "+id+";";
        ResultSet grp = ChatConfig.DB.query(query);;
        Group g = null;
        try {
            if(grp != null){
                grp.next();
                g = ChatMiscUtil.loadGroup(grp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    }
    private void insert(){
        String query = "INSERT INTO `chatdb`.`group` (`group`, `owner`) VALUES (?,?);";
        String query2 = "Select id From groups where `group` = '"+this.groupname+"' and `owner`= '"+this.owner+"';";
        PreparedStatement stmnt;
        try {
            stmnt = ChatConfig.DB.makeStatment(query);
            stmnt.setString(1, this.groupname);
            stmnt.setString(2, this.owner);
            stmnt.execute();
            ResultSet resid = ChatConfig.DB.query(query2);
            if(resid != null){
                this.id = resid.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Group[] ownedBy(String uname){
        ArrayList<Group> grps = new ArrayList<Group>();
        Group[] grpsarr = null;
        String query = "Select * From groups where `owner`= '"+uname+"'";
        grps = ChatMiscUtil.groupList(query);
        grpsarr = new Group[grps.size()];
        grpsarr = grps.toArray(grpsarr);
        return grpsarr;
    }
    public static ArrayList<Group> all(){
        ArrayList<Group> grps = new ArrayList<Group>();
        String query = "Select * From groups;";
        grps = ChatMiscUtil.groupList(query);
        return grps;
    }
    public User[] inviteable(){
        ArrayList<User> canInvite = User.all();
        int[] vals = {-1,1};
        canInvite = ChatMiscUtil.MultiNotEqualFilter(canInvite, this.members(true));
        canInvite = ChatMiscUtil.MultiNotEqualFilter(canInvite, this.requested());
        canInvite = ChatMiscUtil.MultiNotEqualFilter(canInvite, this.invited());
        User[] users = new User[canInvite.size()];
        users = canInvite.toArray(users);
        ChatMiscUtil.bubbleSort(users, true);
        return users;
    }
    private User[] requestingUsers(String type){
        String query = String.format("Select users.* From users JOIN requests on users.`username` = requests.`user` Where requests.`type` = '%s' AND requests.`group` = '%s';", type, ""+this.id);;
        ArrayList<User> list = ChatMiscUtil.userList(query);
        User[] users = new User[list.size()];
        users = list.toArray(users);
        return users;
    }
    public void removeMember(String user){
        String query = String.format("Delete From `group_member` where `group` = '%s' AND `user`='%s';", ""+this.id, user);
        ChatMiscUtil.runStmt(query);
    }
    public void addMember(String user){
        String query = String.format("INSERT INTO `group_member` (`group`,`user`) VALUES ( %s, '%s' )", ""+this.id, user);
        ChatMiscUtil.runStmt(query);
    }
    public User[] requested(){
        return requestingUsers("REQUEST");
    }
    public User[] invited(){
        return requestingUsers("INVITE");
    }
    public User[] members(boolean withowner){
        String query = String.format("Select users.* From users JOIN group_member on users.`username` = group_member.`user` Where group_member.`group` = '%s'", ""+this.id );
        ArrayList<User> list = ChatMiscUtil.userList(query);
        if(withowner){
            list.add(User.get(this.owner));
        }
        User[] users = new User[list.size()];
        users = list.toArray(users);
        return users;
    }
    public static boolean namechk(String v){
        String query = String.format("Select Count(*) as Res From groups where  `group` = '%s'", v);
        ResultSet res = ChatConfig.DB.query(query);
        int cnt =0;
        try {
            if(res != null){
                res.next();
                cnt = res.getInt("Res");
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
       return (cnt <= 0);
    }
    public static Group[] whereMember(String uname, boolean withOwned){
        String query = "Select groups.* From groups JOIN group_member on groups.id = group_member.`group` where group_member.`user` = '"+uname+"';";
        ArrayList<Group> grps = new ArrayList<Group>();
        grps = ChatMiscUtil.groupList(query);
        Group[] grplist = null;
        if(withOwned){
            grplist = ownedBy(uname);
            if(grplist != null){
                grps.addAll(Arrays.asList(grplist));
                grplist = null;
            }
        }
        grplist = new Group[grps.size()];
        grplist = grps.toArray(grplist);
        return grplist;
    }
    public ChatRoom[] rooms(){
        return ChatRoom.groupChats(this.id);
    }
    @Override
    public int compareTo(Object o) {
        int com = 0;
        if(o instanceof String){
            com = this.groupname.compareTo((String)o);
        }
        else if (o instanceof Group){
            com = this.groupname.compareTo(((Group)o).getGroupname());
        }
        return com;
    }
    public String toString(){ 
        return String.format("Group( %s, %s, %s); ", ""+this.id, this.groupname, this.owner);
    }
    public Request[] sentInvites(){
        return Request.invitesTo(this.id);
    }
}
