/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.utils;

import java.util.ArrayList;

/**
 *
 * @author Sydney
 */
public class ScrollTable {
    private final String hfrmt = "<div class='scroll-head'>%s</div>";
    private final String bfrmt = "<div class='scroll-body'>%s</div>";
    private final String tfrmt = "<div id='%s' class='scroll-table %s'>%s</div>";
    private final String cfrmt = "<div class='scroll-cell'><span>%s</span></div>";
    private ArrayList<String> head;
    private ArrayList<String> rows;
    public ScrollTable(){
        this.head=new ArrayList<String>();
        this.rows=new ArrayList<String>();
    }
    public void header(String text){
        head.add(String.format(cfrmt, text));
    }
    public void row(ScrollRow r){
        rows.add(r.html());
    }
    private String concatdata(ArrayList<String> data){
        String str="";
        for(String d:data){
            str+=d;
        }
        return str;
    }
    public String html(String id, String css){
        String h = String.format(hfrmt, concatdata(head));
        String b = String.format(bfrmt, concatdata(rows));
        return String.format(tfrmt, id, css, (h+b));
    }
    
}
