/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import publicizehub.club.model.*;
import publicizehub.club.view.*;

/**
 *
 * @author JIL
 */
public class JoinController {
    LogIn li = new LogIn();
    Join jn = new Join();
    
    
    ConnectionBuilder cb = new ConnectionBuilder();
    public void toJoinEvent(int eventId){
        ResultSet rs = jn.getGenCode(eventId);
        int tempId=0;
        long tempStdId=0;
        String tempEvCode="";
        try {
            
            if(rs.next()) {
                tempId = rs.getInt("evId");
                tempStdId = rs.getLong("stdId");
                tempEvCode = rs.getString("evCode");
                if(li.getStdId()==tempStdId){
                    new JoinClub(tempEvCode).setVisible(true);
                }             
            }else{
                GenerateCode gc = new GenerateCode(li.getStdId(),eventId);
                gc.pushCode();
                new Detail().setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
  
        cb.logout();
    }
}
