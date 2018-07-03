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
public class ChatConfig {
    public static final boolean ALLOW_ACCNT_MOD = true;
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String FONT_FILE = "../Fonts.txt";
    public static final DataQueryHandler DB = new DataQueryHandler(DB_DRIVER, "jdbc:mysql://localhost:3306/chatdb", "scott", "tiger");
    public static final FontSet FONTS = new FontSet(FONT_FILE);
    public static final String TSTAMPFRMT = "hh:mm a";
    public static final String CHAT_ROOM_STORE = "C:\\WebChat\\ChatRooms";
    public static final String USER_IMG_STORE = "C:\\WebChat\\UserImg";
    public static final String ServerFont = "Rockwell Condensed";
    public static final String SeverColor = "1d3f75";
}
