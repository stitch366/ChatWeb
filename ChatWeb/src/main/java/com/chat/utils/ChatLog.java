/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.utils;

import com.chat.models.Message;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.chat.utils.ChatMiscUtil;

/**
 *
 * @author Sydney
 */
public class ChatLog {
   private File log;
   private ArrayList<Message> chat;
   public ChatLog(String file){
       this.log = new File(file+".csv");
       if(!this.log.exists()){
           try {
               this.log.createNewFile();
           } catch (IOException ex) {
               Logger.getLogger(ChatLog.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
   public void upadtechat(){
       this.chat = new ArrayList<Message>();
       String line="";
       int i =0;
       try{
            FileReader read = new FileReader(log.getAbsolutePath());
            BufferedReader bread = new BufferedReader(read);
            while((line = bread.readLine()) != null){
                if(!"".equals(line)){
                    this.chat.add(new Message(line, i));
                    i=i+1;
                }
            }
            bread.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FontSet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FontSet.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   public void send(Message msg){
       try {
           BufferedWriter writer = new BufferedWriter(new FileWriter(log.getAbsolutePath(), true));
           writer.write(msg.csv());
           writer.close();
       } catch (IOException ex) {
           Logger.getLogger(ChatLog.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   private String jsonify(Message[] list){
       int i =0;
       String json =  "[";
       for(Message msg: list){
           i=i+1;
           json+= msg.json();
           if(i!=list.length){
               json+=",";
           }
       }
       json+="]";
       return json;
   }
   public String RetriveAll(){
       Message[] msg = new Message[chat.size()];
       msg = chat.toArray(msg);
       return jsonify(msg);
   }
   public String filter(String time, int[] vals){
       ArrayList<Message> msgs = ChatMiscUtil.CompareTofilter(chat, new Message(time, "",""), vals);
       Message[] msg = new Message[msgs.size()];
       msg = msgs.toArray(msg);
       return jsonify(msg);
   }
   
}
