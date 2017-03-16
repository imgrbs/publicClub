/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import java.sql.*;

/**
 *
 * @author Imagine
 */
public class ConnectionBuilder {
    /* SQL Class */
    Connection connect = null;
    Statement s = null;
    PreparedStatement ps = null;
    ResultSet result;
    
    /* Account and Host */
    String host = "sql12.freemysqlhosting.net";
    String dbName = "sql12164185";
    String user = "sql12164185";
    String password = "d38atiVHJj";
    String account = "?user="+user+"&password="+password+"&characterEncoding=UTF-8";
    String table = "";
    
    public void connectWithStatement(String command,int mode){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+ dbName + account);
            s = connect.createStatement();
            String sql = command;
            if(mode==1){
                s.executeUpdate(sql);
            }
            else if(mode==2){
                s.executeUpdate(sql);
            }
            else {
                s.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void connectWithPrepared(String command){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+ dbName + account);
            s = connect.prepareStatement(command);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void logout(){
        try {
            if(s!=null){
                s.close();
                if(connect!=null){
                    connect.close();  
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        
    }
}
