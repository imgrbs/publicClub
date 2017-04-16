/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import java.sql.ResultSet;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import publicizehub.club.model.*;
import publicizehub.club.view.*;

/**
 *
 * @author JIL
 */
public class JoinController {
    LoginController li = new LoginController();
    Join jn = new Join();
    Event ev = new Event();
    
    Alert comfirm = new Alert(Alert.AlertType.CONFIRMATION);
    Alert warning = new Alert(Alert.AlertType.ERROR);
    
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
                    callShowCode(tempEvCode,eventId);
                }             
            }else{                    
                comfirm.setTitle("ยืนยันการจอง");
                comfirm.setHeaderText("ยืนยันว่าจะจองกิจกรรมนี้");
                comfirm.setContentText("คุณยืนยันที่จะจองใช่หรือไม่");
                Optional<ButtonType> result = comfirm.showAndWait();
                if (result.get() == ButtonType.OK){
                    ResultSet CheckTicket = ev.getSelect(eventId);
                    if(CheckTicket.next()){
                        if(CheckTicket.getInt("currentMember")<CheckTicket.getInt("evTicket")){
                            GenerateCode gc = new GenerateCode(li.getStdId(),eventId);
                            gc.pushCode(eventId);
                            callShowCode(gc.getEvCode(),eventId);
                        }
                        else{
                            warning.setTitle("Error !");
                            warning.setHeaderText("กิจกรรมนี้เต็มแล้ว");
                            warning.setContentText("ขออภัย, กิจกรรมเต็มแล้วไม่สามารถจองได้");
                            warning.showAndWait();
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        cb.logout();
    }
    
    public void callShowCode(String evCode,int eventId){
        Stage stage= new Stage();
        Parent root=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ShowCode.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        ShowCodeController controller = fxmlLoader.<ShowCodeController>getController();
        controller.setCodeText(evCode);
        controller.setEventId(eventId);
        controller.setShowCodeStage(stage);
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        stage.show();
    }
}
