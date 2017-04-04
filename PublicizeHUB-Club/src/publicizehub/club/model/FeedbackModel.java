/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.model;


import java.sql.*;
import javax.swing.*;

/**
 *
 * @author budsagorn_ss
 */
public class FeedbackModel {
    
    public void insertValue(int q1,int q2,int evId,long stdId){
        Statement s = null; 
        String sql;
        ConnectionBuilder cb = new ConnectionBuilder();
        cb.connecting(); //connect database
        try {
            sql = "INSERT INTO tb_feedback"
             + "(evId,stdId,q1,q2)"
             + "VALUES ('"+ evId +"','" +stdId+ "','" +q1+ "','" +q2+ "') ";
                  
            s.executeUpdate(sql); // ส่งข้อมูลไป Database 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

        cb.logout(); 
    }
    
    public void updateValue(int q1,int q2,int evId,long stdId){
        // update
        ConnectionBuilder cb = new ConnectionBuilder();
        cb.connecting();
        PreparedStatement ps = null; 
        String sql;
        try {
            sql = "UPDATE tb_feedback set evID = '"+evId+"' , "
                    + "stdId = '"+stdId+"' , q1 = '"+q1+"' , "
                    + "q2 = '"+q2;
            ps = cb.getConnect().prepareStatement(sql); 
            System.out.println("Before Update");
            ps.executeUpdate();
            System.out.println("Success Update");
        }
        catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL ERROR at updateEvent()");
        }
        cb.logout();
    }
    
}
