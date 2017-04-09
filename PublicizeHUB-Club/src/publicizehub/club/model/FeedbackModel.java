/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.model;


import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import publicizehub.club.controller.FeedbackController;

/**
 *
 * @author budsagorn_ss
 */
public class FeedbackModel {
    
    ArrayList <FeedbackStd> myArrList = new ArrayList<FeedbackStd>();
    FeedbackController fbc = new FeedbackController();
        
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
    
   
     public void setSumQ() {
        PreparedStatement ps = null;
        ResultSet result;
        ConnectionBuilder cb = new ConnectionBuilder();
        cb.connecting(); //เรียกใช้ method connecting()เพื่อ connect database
        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM std_feedback");
            result = ps.executeQuery();
            while (result.next()) {
                FeedbackStd fb = new FeedbackStd(result.getInt("evId"), result.getLong("stdId"),result.getInt("sumQ1"),result.getInt("sumQ2"),result.getInt("sumQ3"),result.getInt("sumQ4"), 
                                result.getInt("sumQ5"),result.getInt("sumQ6"), result.getInt("sumQ7"),  result.getInt("sumQ8"),result.getInt("sumQ9"),result.getInt("sumQ10"));
                
                myArrList.add(fb);
                
            }
            
            fbc.getArrayList(myArrList);
            
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

        cb.logout();
    }   
}
