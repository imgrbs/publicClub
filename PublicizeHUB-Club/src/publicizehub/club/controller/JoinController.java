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
    private long loginStdId = 59130500012l;
    Join jn = new Join();
    
    ConnectionBuilder cb = new ConnectionBuilder();
    public void toJoinEvent(int eventId){
        ResultSet rs = jn.getGenCode(eventId);
        int tempId;
        long tempStdId;
        String tempEvCode;
        try {
            
            while (rs.next()) {
                tempId = rs.getInt("evId");
                tempStdId = rs.getLong("stdId");
                tempEvCode = rs.getString("evCode");
                if(loginStdId==tempStdId){
                    new JoinClub(tempEvCode).setVisible(true);
                }
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
  
        cb.logout();
    }
}
