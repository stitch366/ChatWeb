/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.tags;

import com.chat.utils.ChatConfig;
import com.chat.utils.ComboOpt;
import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Sydney
 */
public class ComboTag extends SimpleTagSupport{
    private String id;
    private String name;
    private ComboOpt[] items;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(ComboOpt[] items) {
        this.items = items;
    }
    @Override
    public void doTag() throws IOException{
        JspWriter out = getJspContext().getOut();
        
        String innards = "<div id='"+this.id+"' class='combo'><input id='"+this.id+"-in' name='"+this.name+"' type='text'><ul>";
        for(ComboOpt v: this.items){
            innards+=v.html();
        }
        innards+="</ul></div>";
        out.print(innards);
        
    }
    
}
