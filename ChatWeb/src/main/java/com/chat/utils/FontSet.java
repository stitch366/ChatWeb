/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sydney
 */
public class FontSet {
    private String[] fonts;
    private ComboOpt[] font;
    public FontSet(String file){
        ArrayList<String> list = new ArrayList<String>();
        String line="";
        try{
            URL url = this.getClass().getClassLoader().getResource(file);
            File f = new File(url.toURI());
            //System.out.println("File "+f.getAbsolutePath()+" :");
            //System.out.println("Exists: "+ f.exists());
            //System.out.println("Readable: "+f.canRead());
            FileReader read = new FileReader(f.getAbsolutePath());
            BufferedReader bread = new BufferedReader(read);
            while((line = bread.readLine()) != null){
                if(!"".equals(line)){
                    list.add(line);
                }
            }
            bread.close();
            this.fonts = new String[list.size()];
            this.font = new ComboOpt[list.size()];
            this.fonts = list.toArray(fonts);
            int i=0;
            for(String c: this.fonts){
                font[i] = new ComboOpt(c,c);
                i+=1;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FontSet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FontSet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(FontSet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ComboOpt[] getFonts(){
        return this.font;
    }
}
