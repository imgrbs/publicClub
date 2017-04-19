/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.sun.javafx.scene.control.behavior.OptionalBoolean;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import publicizehub.club.model.Event;

/**
 * FXML Controller class
 *
 * @author JIL
 */
public class CreateEventController implements Initializable {
    Event e = new Event();
    Event thisEvent = null;
    
    LoginController lc = new LoginController();
    
    private int evType;
    private long stdId=lc.getStdId();
    
    @FXML
    private Stage thisStage = null;
    @FXML
    private JFXTextField eventName;
    @FXML
    private JFXDatePicker startDate;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private JFXTimePicker startTime;
    @FXML
    private JFXTimePicker endTime;
    @FXML
    private JFXDatePicker startRegis;
    @FXML
    private ComboBox<String> ticket;
    @FXML
    private ToggleGroup type;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField place;

    @FXML
    private JFXRadioButton camp;

    @FXML
    private JFXRadioButton seminar;

    @FXML
    private JFXRadioButton other;

    @FXML
    private JFXButton confirmBtn;
    @FXML
    private JFXButton cancelBtn;
    
    private String customText="ระบุเอง";

    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    public JFXButton getCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(JFXButton cancelBtn) {
        this.cancelBtn = cancelBtn;
    }
    
    

    @FXML
    public void setAllValue(){
        evTypeResult();
        thisEvent = new Event(stdId,eventName.getText(),description.getText(),startDate.getValue(),endDate.getValue(),
            startRegis.getValue(),startRegis.getValue().plusDays(15),place.getText(),Integer.parseInt(ticket.getValue()),
            startTime.getValue(),endTime.getValue(),evType);
    }
    @FXML
    public void evTypeResult(){
        
        if(camp.isSelected()){
            this.evType=0;
        }else if(seminar.isSelected()){
            this.evType=1;
        }else if(other.isSelected()){
            this.evType=2;
        }
    }
    /**
     * Initializes the controller class.
     */
    public void checkCustomize(){
        if(ticket.getTypeSelector().equals(customText)){
            ticket.setValue("");
            ticket.setEditable(true);
            
        }else 
            ticket.setEditable(false);
    } 
    @FXML
    public void setValueToCombobox(){
        
        ticket.getItems().addAll("5","10","15","20","25","30","35","40","45","50","75","100","ระบุเอง");  
        ticket.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if(ticket.getValue().equals(customText)){
                    ticket.setValue("");
                    ticket.setEditable(true);   
                    
                }else 
                    
                    ticket.setEditable(false); 
                    
            }
        });
    }
    
    @FXML
    public void clickConfirm(){
        Alert warning = null;
        if(!true){
            warning = new Alert(Alert.AlertType.CONFIRMATION);
            warning.setTitle("ยืนยันการสร้างกิจกรรม");
            warning.setHeaderText("");
            warning.showAndWait();
    //        <OptionalBoolean> check =
            if(true){
                setAllValue();
                e.createEvent(thisEvent);
            }
        }else{
            warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("Error!");
            warning.setHeaderText("กรุณากรอกข้อมูลให้ครบทุกช่อง!");
            warning.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setValueToCombobox();
    }
    
    @FXML
    public void callCreateEvent(){
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CreateEvent.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        CreateEventController controller = fxmlLoader.<CreateEventController>getController();
        controller.setThisStage(stage);
        controller.getCancelBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.closeStage();
            }
        });
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        stage.show();
    }
    
    @FXML
    public void closeStage(){
        getThisStage().close();
    }
}
