/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.tags;

import com.chat.models.Request;
import com.chat.utils.ScrollRow;
import com.chat.utils.ScrollTable;
import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Sydney
 */
public class LogListTag extends SimpleTagSupport{
    private String[] set;
    private ScrollTable table = new ScrollTable();
    public String[] getSet() {
        return set;
    }

    public void setSet(String[] logs) {
        this.set = logs;
    }
    private void header(){
        table.header("Date");
        table.header("");
    }
    private void row(String Date){
        ScrollRow r= new ScrollRow();
        r.cell(Date);
        r.cell("<a class='crtbtn logbtn' act='log"+Date+"'>View</a>");
        table.row(r);
    }
    @Override
    public void doTag() throws IOException{
        JspWriter out = getJspContext().getOut();
        this.header();
        for(String req:set){
            row(req);
        }
        out.print(table.html("logs", "twoCols2"));
    }
}
