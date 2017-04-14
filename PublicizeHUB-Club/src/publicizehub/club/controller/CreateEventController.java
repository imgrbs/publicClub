/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import publicizehub.club.model.Event;

/**
 * FXML Controller class
 *
 * @author JIL
 */
public class CreateEventController implements Initializable {
    Event e = new Event();
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
    private long stdId=lc.getStdId();;
    
    
    @FXML
    private TextField eventName;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField startTime;
    @FXML
    private TextField endTime;
    @FXML
    private TextField place;
    @FXML
    private ComboBox<String> ticket;
    @FXML
    private RadioButton camp;
    @FXML
    private ToggleGroup type;
    @FXML
    private RadioButton seminar;
    @FXML
    private RadioButton other;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;

    public String getEvName() {
        return evName;
    }
    @FXML
    public void setEvName(String evName) {
        this.evName = eventName.getText();
    }

    public String getEvDescrip() {
        return evDescrip;
    }
    @FXML
    public void setEvDescrip(String evDescrip) {
        this.evDescrip = description.getText();
    }

    public LocalDate getEvDate() {
        return evDate;
    }
    @FXML
    public void setEvDate(LocalDate evDate) {
        this.evDate = startDate.getValue();
    }  

    public LocalDate getEvEndDate() {
        return evEndDate;
    }
    @FXML
    public void setEvEndDate(LocalDate evEndDate) {
        this.evEndDate = endDate.getValue();
    }

    public String getEvPlace() {
        return evPlace;
    }
    @FXML
    public void setEvPlace(String evPlace) {
        this.evPlace = place.getText();
    }

    public int getEvTicket() {
        return evTicket;
    }
    @FXML
    public void setEvTicket(int evTicket) {
        this.evTicket = Integer.parseInt(ticket.getValue());
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
    @FXML
    public void setEvTime(String evTime) {
        this.evTime = startTime.getText();
    }

    public String getEvEndTime() {
        return evEndTime;
    }
    @FXML
    public void setEvEndTime(String evEndTime) {
        this.evEndTime = endTime.getText();
    }

    public int getEvType() {
        return evType;
    }

    public void setEvType(int evType) {
        this.evType = evType;
    }

    public long getStdId() {
        return lc.getStdId();
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }
//------------------------------------------------------------------------------------------------------------------------------
    public TextField getEventName() {
        return eventName;
    }

    public void setEventName(TextField eventName) {
        this.eventName = eventName;
    }

    public TextArea getDescription() {
        return description;
    }

    public void setDescription(TextArea description) {
        this.description = description;
    }

    public DatePicker getStartDate() {
        return startDate;
    }

    public void setStartDate(DatePicker startDate) {
        this.startDate = startDate;
    }

    public DatePicker getEndDate() {
        return endDate;
    }

    public void setEndDate(DatePicker endDate) {
        this.endDate = endDate;
    }

    public TextField getStartTime() {
        return startTime;
    }

    public void setStartTime(TextField startTime) {
        this.startTime = startTime;
    }

    public TextField getEndTime() {
        return endTime;
    }

    public void setEndTime(TextField endTime) {
        this.endTime = endTime;
    }

    public TextField getPlace() {
        return place;
    }

    public void setPlace(TextField place) {
        this.place = place;
    }

    public ComboBox<?> getTicket() {
        return ticket;
    }

    public void setTicket(ComboBox<String> ticket) {
        this.ticket = ticket;
    }

    public RadioButton getCamp() {
        return camp;
    }

    public void setCamp(RadioButton camp) {
        this.camp = camp;
    }

    public ToggleGroup getType() {
        return type;
    }

    public void setType(ToggleGroup type) {
        this.type = type;
    }

    public RadioButton getSeminar() {
        return seminar;
    }

    public void setSeminar(RadioButton seminar) {
        this.seminar = seminar;
    }

    public RadioButton getOther() {
        return other;
    }

    public void setOther(RadioButton other) {
        this.other = other;
    }
    @FXML
    public void setAllValue(){
        this.evName = eventName.getText();
        this.evDescrip = description.getText();
        this.evDate = startDate.getValue();
        this.evEndDate = endDate.getValue();
        this.evTime = startTime.getText();
        this.evEndTime = endTime.getText();
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
    /**
     * Initializes the controller class.
     */
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setValueToCombobox();
        
        
    }
    
}
