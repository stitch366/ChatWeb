/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.utils;

/**
 *
 * @author Sydney
 */
public class ComboOpt implements Comparable{
    String value;
    String text;

    public ComboOpt(String value, String text) {
        this.value = value;
        this.text = text;
    }
    
    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
    public String html(){
        return String.format("<li class='combo-opt'value='%s'>%s</li>", value,text);
    }
    @Override
    public String toString(){
       return this.value;
    }
    @Override
    public int compareTo(Object o) {
       return this.value.compareTo(o.toString());
    }
}
