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
    
    public void insertValue(int percentQ1,int percentQ2,int evId,long stdId){
        Statement s = null; 
        String sql;
        ConnectionBuilder cb = new ConnectionBuilder();
        cb.connecting(); //connect database
        try {
            s = cb.getConnect().createStatement();  // สร้าง Statement
            sql = "INSERT INTO tb_feedback (evId,stdId,q1,q2) VALUES ('" + evId + "','" + stdId + "','" + percentQ1 + "','" + percentQ2 + "') ";
                  
            s.executeUpdate(sql); // ส่งข้อมูลไป Database 
            
        } catch (Exception e) {
            
            System.out.println(percentQ1);
            System.out.println(percentQ2);
            System.out.println(evId);
            System.out.println(stdId);
            
            JOptionPane.showMessageDialog(null, e.getMessage());
            
            e.printStackTrace();
        }

        cb.logout(); 
    }
    
   
       
}
