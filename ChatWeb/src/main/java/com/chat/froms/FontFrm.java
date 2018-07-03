/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.froms;

/**
 *
 * @author Sydney
 */
public class FontFrm {
    private String font;
    private String color;

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        
        this.color = color.replace("#", "");
    }
    
}
