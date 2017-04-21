package publicizehub.club.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import publicizehub.club.model.GenerateCode;
import publicizehub.club.model.Join;

/**
 *
 * @author ImagineRabbits
 */
public class ShowCodeController {
    private static final Logger LOGGER = Logger.getLogger( GenerateCode.class.getName() );
    private final Join j = new Join();
    private Alert comfirm = new Alert(Alert.AlertType.CONFIRMATION);
    private Alert success = new Alert(Alert.AlertType.INFORMATION);
    
    private Stage showCodeStage;
    
    private int eventId;
    
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label codeText;
    @FXML
    private Button closeButton;
    
    public Label getCodeText() {
        return codeText;
    }

    public void setCodeText(String codeText) {
        this.codeText.setText(codeText);
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Stage getShowCodeStage() {
        return showCodeStage;
    }

    public void setShowCodeStage(Stage showCodeStage) {
        this.showCodeStage = showCodeStage;
    }
    
    @FXML
    public void unJoinEvent(){
        comfirm.setTitle("ยืนยันการยกเลิก");
        comfirm.setHeaderText("ยืนยันว่าจะยกเลิกจองกิจกรรมนี้");
        comfirm.setContentText("คุณยืนยันที่จะ \"ยกเลิก\" จองใช่หรือไม่");
        Optional<ButtonType> result = comfirm.showAndWait();
        if(result.isPresent()){
            if (result.get() == ButtonType.OK){
                j.deleteCode(codeText.getText(),getEventId());
                success.setHeaderText("ยกเลิกการจอง");
                success.setContentText("ยกเลิกการจอง สำเร็จแล้ว");
                success.showAndWait();
                try{
                    showCodeStage.close();
                }
                catch(Exception e){
                    LOGGER.log(Level.SEVERE ,"unJoinEvent : unJoinEvent Bug !");
                }
            }
        }
    }
    
}
