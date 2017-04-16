/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import publicizehub.club.model.TableEvent;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import publicizehub.club.model.Event;

/**
 * FXML Controller class
 *
 * @author JIL
 */
public class EditEventController implements Initializable {
    Event e = new Event();
    TableEvent te = new TableEvent();
    LoginController lc = new LoginController();
   
    private String evName; 
    private String evDescrip;
    private LocalDate evDate;
    private LocalDate evEndDate;
    private String evPlace;
    private int evTicket;
    private int currentMember;
    private String evTime;
    private String evEndTime;
    private int evType;
    private long stdId=lc.getStdId();

    public String getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName = evName;
    }

    public String getEvDescrip() {
        return evDescrip;
    }

    public void setEvDescrip(String evDescrip) {
        this.evDescrip = evDescrip;
    }

    public LocalDate getEvDate() {
        return evDate;
    }

    public void setEvDate(LocalDate evDate) {
        this.evDate = evDate;
    }

    public LocalDate getEvEndDate() {
        return evEndDate;
    }

    public void setEvEndDate(LocalDate evEndDate) {
        this.evEndDate = evEndDate;
    }

    public String getEvPlace() {
        return evPlace;
    }

    public void setEvPlace(String evPlace) {
        this.evPlace = evPlace;
    }

    public int getEvTicket() {
        return evTicket;
    }

    public void setEvTicket(int evTicket) {
        this.evTicket = evTicket;
    }

    public int getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(int currentMember) {
        this.currentMember = currentMember;
    }

    public String getEvTime() {
        return evTime;
    }

    public void setEvTime(String evTime) {
        this.evTime = evTime;
    }

    public String getEvEndTime() {
        return evEndTime;
    }

    public void setEvEndTime(String evEndTime) {
        this.evEndTime = evEndTime;
    }

    public int getEvType() {
        return evType;
    }

    public void setEvType(int evType) {
        this.evType = evType;
    }

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }
    
    
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
    
    @FXML
    public void showValue(){
        
        eventName.setText(evName);        
        startDate.setValue(evDate);        
        endDate.setValue(evEndDate);        
        startTime.setValue(LocalTime.MAX);
        endTime.setValue(LocalTime.MAX);
        ticket.setValue(evTicket+"");
        setType(evType);
        //type.selectToggle(camp);
        description.setText(evDescrip);
        place.setText(evPlace);
    }
    @FXML
    public void setType(int evType){
        
        if(evType==0){
            camp.setSelected(true);
        }else if(evType==1){
            seminar.setSelected(true);
        }else
            other.setSelected(true);
    }
    @FXML
    public void setAllValue(){
        this.evName = eventName.getText();
        this.evDescrip = description.getText();
        this.evDate = startDate.getValue();
        this.evEndDate = endDate.getValue();
        this.evTime = (startTime.getValue())+"";
        this.evEndTime = (endTime.getValue())+"";
        this.evTicket = Integer.parseInt(ticket.getValue());
        this.evPlace = place.getText();
        evTypeResult();
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
   
    @FXML
    public void setValueToCombobox(){
        
        ticket.getItems().addAll("5","10","15","20","25","30","35","40","45","50","75","100","ระบุเอง");  
        //if(ticket.selectionModelProperty().equals("ระบุเอง")){
            ticket.setEditable(true);
        //}  
    }
    @FXML
    public void clickConfirm(){
        
        setAllValue();
        confirmBtn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                e.createEvent(evName, evDescrip, evDate, evEndDate, evTime, evEndTime, evPlace, evTicket, evType, stdId);
            }
        });
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setValueToCombobox();
        showValue();
    }    
    
}
