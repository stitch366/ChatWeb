/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.models;

import com.chat.utils.ChatConfig;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Sydney
 */
public class UserImg {
    private BufferedImage img;
    private String user;
    private static final String URI_PRE ="data:image/png;base64,";
    private static final String DEFAULT = ChatConfig.USER_IMG_STORE+"\\default.png";
    public UserImg(String un) throws IOException{
        this.user = un;
        File f = new File(ChatConfig.USER_IMG_STORE+"\\"+un+".png");
        if(!f.exists()){
            f = new File(DEFAULT);
        }
        this.img = ImageIO.read(f);
    }
    public UserImg(String uri, String un){
        try {
            this.user = un;
            fromImgStr(uri);
        } catch (IOException ex) {
            Logger.getLogger(UserImg.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public int height(){
        return img.getHeight();
    }
    public int width(){
        return img.getWidth();
    }
    public void save() throws IOException{
        File out = new File(ChatConfig.USER_IMG_STORE+"\\"+this.user+".png");
        if(out.exists()){
            out.delete();
        }
        ImageIO.write(img, "png", out);
    }
    private void fromImgStr(String uri) throws IOException{
        String data = uri.replace(URI_PRE, "");
        byte[] bytes = DatatypeConverter.parseBase64Binary(data);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        this.img = ImageIO.read(bis);
        bis.close();
    }
    public String imgStr() throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        baos.flush();
        String data = DatatypeConverter.printBase64Binary(baos.toByteArray());
        baos.close();
        String imgstr=URI_PRE+data;
        return imgstr;
    }
}
