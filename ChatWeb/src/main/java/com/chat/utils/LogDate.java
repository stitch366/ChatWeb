/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.utils;

import java.util.Date;

/**
 *
 * @author Sydney
 */
public class LogDate implements Comparable{
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LogDate(String date) {
        this.date = date;
    }
    public Date asDate(){
        return ChatTime.parselogDate(date);
    }
    @Override
    public int compareTo(Object o) {
        int com =0;
        if (o instanceof LogDate){
            com = this.asDate().compareTo(((LogDate)o).asDate());
        }
        return com;
    }
    
}
