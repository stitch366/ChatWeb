/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sydney
 */
public class ChatTime {
    private static final SimpleDateFormat datefrmt = new SimpleDateFormat("yyyy-MM-dd");
    public static String today(){
          Calendar cal = Calendar.getInstance();
          return datefrmt.format(cal.getTime());
    }
    public static Date parselogDate(String date){
        Date d = null;
        try {
            d = datefrmt.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(ChatTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    public static String timestamp(){
        SimpleDateFormat tstmp = new SimpleDateFormat(ChatConfig.TSTAMPFRMT);
        Calendar cal = Calendar.getInstance();
        return tstmp.format(cal.getTime());
    }
    public static Date parseStamp(String date){
        Date d = null;
        SimpleDateFormat tstmp = new SimpleDateFormat(ChatConfig.TSTAMPFRMT);
        try {
            d = tstmp.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(ChatTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    public static String HoursAgo(int hr){
        SimpleDateFormat tstmp = new SimpleDateFormat(ChatConfig.TSTAMPFRMT);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1*hr);
        Date date = calendar.getTime();
        return tstmp.format(date);
    }
}
