/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.config;

import com.chat.models.CurrentUser;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sydney
 */
@Component(value="sd")
@Scope(value="session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class SessionData {
    private CurrentUser cu;
    
    
    public CurrentUser getCu() {
        return cu;
    }

    public void setCu(CurrentUser cu) {
        this.cu = cu;
    }
    
    public String toString(){
        return String.format("{'cu':%s}", this.cu.toString());
    }
    
}
