/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.utils;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sydney
 */
public class DataQueryHandler {
    private Connection conn;
    public DataQueryHandler(String driver, String url, String username, String pass){
        try {
                Class.forName(driver);
		conn = DriverManager.getConnection( url , username, pass );
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return;
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataQueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public PreparedStatement makeStatment(String q) throws SQLException{
        return conn.prepareStatement(q);
    }
    public ResultSet query(String q){
        Statement stmt = null;
        ResultSet rs = null;
        try {
		stmt = conn.createStatement();
		rs = stmt.executeQuery(q);
	} catch (SQLException e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
	}
        return rs;
    }
    public void insert(String stmnt){
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt= conn.prepareStatement(stmnt);
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DataQueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
