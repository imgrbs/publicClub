package publicizehub.club.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.logging.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import publicizehub.club.model.*;

/**
 *
 * @author JIL
 */
public class JoinController {
    private static final Logger LOGGER = Logger.getLogger( FormSumActivityController.class.getName() );
    private final LoginController li = new LoginController();
    private final JoinModel jn = new JoinModel();
    private final EventModel ev = new EventModel();
    private LoginModel profile;
    
    private Alert comfirm = new Alert(Alert.AlertType.CONFIRMATION);
    private Alert warning = new Alert(Alert.AlertType.ERROR);
    
    private long stdId;
    
    private ConnectionBuilder cb = new ConnectionBuilder();

    public JoinController() {
    }

    public JoinController(long stdId) {
        this.stdId = stdId;
    }

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }

    public LoginModel getProfile() {
        return profile;
    }

    public void setProfile(LoginModel profile) {
        this.profile = profile;
    }
    
    
    
    
    public void toJoinEvent(int eventId){
        System.out.println(profile.getStdId());
        ResultSet rs = jn.getGenCode(eventId,profile.getStdId());
        long tempStdId=0;
        String tempEvCode="";
        try {
            if(rs.next()) {
                tempStdId = rs.getLong("stdId");
                tempEvCode = rs.getString("evCode");
                if(profile.getStdId()==tempStdId){
                    callShowCode(tempEvCode,eventId);
                }             
            }else{                    
                comfirm.setTitle("ยืนยันการจอง");
                comfirm.setHeaderText("ยืนยันว่าจะจองกิจกรรมนี้");
                comfirm.setContentText("คุณยืนยันที่จะจองใช่หรือไม่");
                Optional<ButtonType> result = comfirm.showAndWait();
                if(result.isPresent()){
                    if (result.get() == ButtonType.OK){
                        ResultSet checkTicket = ev.getSelect(eventId);
                        if(checkTicket.next()){
                            if(checkTicket.getInt("currentMember")<checkTicket.getInt("evTicket")){
                                GenerateCode gc = new GenerateCode(profile.getStdId(),eventId);
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
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"toJoinEvent : toJoinEvent Bug !");
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
        catch(IOException e){
            LOGGER.log(Level.SEVERE ,"root : callShowCode Bug !");
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
            LOGGER.log(Level.SEVERE ,"stage : callShowCode Bug !");
        }
        stage.show();
    }

    public void setStdId(LoginModel profile) {
        this.stdId = profile.getStdId();
    }
}
