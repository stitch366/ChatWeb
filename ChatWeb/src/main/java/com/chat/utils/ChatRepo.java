/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.utils;

import java.io.File;

/**
 *
 * @author Sydney
 */
public class ChatRepo {
    private String basepath;
    public ChatRepo(String room){
        this.basepath = ChatConfig.CHAT_ROOM_STORE+"\\"+room;
    }
    public ChatLog current(){
        return new ChatLog(this.basepath+"\\"+ChatTime.today());
    }
    public ChatLog getLog(String logdate){
        return new ChatLog(this.basepath+"\\"+logdate);
    }
    private String[] pastlogs(){
        File store = new File(this.basepath);
        File[] list = store.listFiles();
        String[] logs = new String[list.length-1];
        String tday = ChatTime.today();
        String temp="";
        int i =0;
        for (File f:list) {
          temp = f.getName().replace(".csv", "");
          if(!temp.equals(tday)){
            logs[i] = temp;
            i+=1;
          }
        }
        return logs;
    }
    
    public String[] loglist(){
        String[] logs = pastlogs();
        LogDate[] dates = new LogDate[logs.length];
        int i =0;
        for(String l: logs){
            dates[i]= new LogDate(l);
            i+=1;
        }
        ChatMiscUtil.bubbleSort(dates, false);
        logs = new String[dates.length];
        i=0;
        for(LogDate l:dates){
            logs[i]= l.getDate();
            i+=1;
        }
        return logs;
    }
}
