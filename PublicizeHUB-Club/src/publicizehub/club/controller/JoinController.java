/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import java.sql.ResultSet;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    
    Alert comfirm = new Alert(Alert.AlertType.CONFIRMATION);
 
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
                comfirm.setTitle("ยืนยันการจอง");
                comfirm.setHeaderText("ยืนยันว่าจะจองกิจกรรมนี้");
                comfirm.setContentText("คุณยืนยันที่จะจองใช่หรือไม่");
                Optional<ButtonType> result = comfirm.showAndWait();
                if (result.get() == ButtonType.OK){
                    GenerateCode gc = new GenerateCode(li.getStdId(),eventId);
                    gc.pushCode();
                    new JoinClub(gc.getEvCode()).setVisible(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
  
        cb.logout();
    }
    
    
}
