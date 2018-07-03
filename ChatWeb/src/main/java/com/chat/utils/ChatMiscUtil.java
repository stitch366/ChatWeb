/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.utils;

import com.chat.models.ChatRoom;
import com.chat.models.CurrentUser;
import com.chat.models.Group;
import com.chat.models.Request;
import com.chat.models.User;
import com.chat.models.UserImg;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.ui.Model;

/**
 *
 * @author Sydney
 */
public class ChatMiscUtil {
    //generic bubble sort
    public static <E extends Comparable<E>> void bubbleSort(E[] list, boolean isAscending){
        int len = list.length;
        E temp;
        for(int x =0; x < len-1; x++){
            for(int y =0; y < (len-x)-1; y++){
                //compareTo returns less than zero if passed arg is of greater value
                if(list[y].compareTo(list[y+1]) < 0){
                    //swaps value posistions
                    temp= list[y];
                    list[y]=list[y+1];
                    list[y+1]=temp;
                }
            }
        }
        if(isAscending){
            reverse(list);
        }
    }
    //reveses array
    public static <E extends Comparable<E>> void reverse(E[] list){
        E temp;
        int len = list.length;
        int ei;
        for(int x =0; x < len/2; x++){
            ei = (len-1)-x;
            temp = list[ei];
            list[ei]=list[x];
            list[x] = temp;
        }
        
    }
    public static <E extends Comparable<E>> ArrayList<E> MultiNotEqualFilter(ArrayList<E> data, E[] fvalues){
       ArrayList<E> filtered = data;
        for(E item: fvalues){
            filtered = NotEqualFilter(filtered, item);
        }
        return filtered;
    }
    public static <E extends Comparable<E>> ArrayList<E> NotEqualFilter(ArrayList<E> data, E fvalue){
        ArrayList<E> filtered = new ArrayList<E>();
        for(E item:data){
            if(item.compareTo(fvalue) != 0){
                filtered.add(item);
            }
        }
        return filtered;
    }
    public static <E extends Comparable<E>> ArrayList<E> MultiCompareTofilter(ArrayList<E> data, E[] fvalues, int[] dres){
        ArrayList<E> filtered = data;
        for(E item: fvalues){
            filtered = CompareTofilter(filtered, item, dres);
        }
        return filtered;
    }
    public static <E extends Comparable<E>> ArrayList<E> CompareTofilter(ArrayList<E> data, E fvalue, int[] dres){
        ArrayList<E> filtered = new ArrayList<E>();
        int compare;
        for(E item:data){
            compare = item.compareTo(fvalue);
            for(int i: dres){
                if(compare == i){
                    filtered.add(item);
                    break;
                }
            }  
        }
        return filtered;  
    }
    public static Group loadGroup(ResultSet rs) throws SQLException{
        return new Group(rs.getInt("id"), rs.getString("group"), rs.getString("owner"));
    }
    public static ChatRoom loadRoom(ResultSet rs) throws SQLException{
        return new ChatRoom(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getInt("group"), rs.getString("owner"));
    }
    public static User loadUser(ResultSet user) throws SQLException{
        return new User(user.getString("username"), user.getString("email"),user.getString("font"),user.getString("font_color"), user.getString("first"), user.getString("last"), user.getString("password"),user.getString("account_type"));
    }
    public static Request loadRequest(ResultSet rs) throws SQLException{
        return new Request(rs.getInt("group"), rs.getString("user"), rs.getString("type"));
    }
    public static ArrayList<Group> groupList(String query){
        ArrayList<Group> list = new ArrayList<Group>();
        ResultSet user = ChatConfig.DB.query(query);
        try {
            if(user != null){
                while(user.next()){
                    list.add(loadGroup(user));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static ArrayList<ChatRoom> roomList(String query){
        ArrayList<ChatRoom> list = new ArrayList<ChatRoom>();
        ResultSet user = ChatConfig.DB.query(query);
        try {
            if(user != null){
                while(user.next()){
                    list.add(loadRoom(user));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static ArrayList<User> userList(String query){
        ArrayList<User> list = new ArrayList<User>();
        ResultSet user = ChatConfig.DB.query(query);
        try {
            if(user != null){
                while(user.next()){
                    list.add(loadUser(user));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static ArrayList<Request> requestList(String query){
        ArrayList<Request> list = new ArrayList<Request>();
        ResultSet user = ChatConfig.DB.query(query);
        try {
            if(user != null){
                while(user.next()){
                    list.add(loadRequest(user));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static int count(String query){
        ResultSet grp = ChatConfig.DB.query(query);
        int cnt =0;
        try {
            if(grp != null){
                grp.next();
                cnt = grp.getInt("num");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnt;
    }
    public static void runStmt(String query){
        PreparedStatement stmnt;
        try {
            stmnt = ChatConfig.DB.makeStatment(query);
            stmnt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static String userId(String uname, boolean intable){
        UserImg img = null;
        String base = "";
        try {
            img = new UserImg(uname);
            base = String.format("<tr class='user-out'><td class='uimg'><img src='%s'></td><td class='%s'>%s</td></tr>", img.imgStr(), uname, uname);
        } catch (IOException ex) {
            Logger.getLogger(ChatMiscUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (intable)? "<table>"+base+"</table>" : base;
    }
    public static void mailboxModel(String view, CurrentUser cu, Model m){
        MailboxValue val = MailboxValue.valueOf(view.toUpperCase());
        val.ConfigModel(m, cu);
    }
    
}
