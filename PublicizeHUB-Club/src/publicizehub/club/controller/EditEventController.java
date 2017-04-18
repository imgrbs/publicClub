/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import publicizehub.club.model.TableEvent;
import com.jfoenix.controls.*;
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
   
    private Event thisEvent;
    private long stdId=lc.getStdId();
    

    public EditEventController(Event event) {
        thisEvent = event;
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
    @FXML
    public void showValue(){
        eventName.setText(thisEvent.getEvName());        
        startDate.setValue(thisEvent.getEvDate());        
        endDate.setValue(thisEvent.getEvEndDate());        
        startTime.setValue(thisEvent.getEvTime());
        endTime.setValue(thisEvent.getEvEndTime());
        ticket.setValue(""+thisEvent.getEvTicket());
        setType(thisEvent.getEvType());
        description.setText(thisEvent.getEvDescrip());
        place.setText(thisEvent.getEvPlace());
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
        showValue();
        evTypeResult();
    }
    @FXML
    public void evTypeResult(){
        int evType=2;
        if(camp.isSelected()){
            evType=0;
        }else if(seminar.isSelected()){
            evType=1;
        }else if(other.isSelected()){
            evType=2;
        }
        thisEvent.setEvType(evType);
    }
   
    @FXML
    public void setValueToCombobox(){
        
        ticket.getItems().addAll("5","10","15","20","25","30","35","40","45","50","75","100","ระบุเอง");  
        ticket.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if(ticket.getValue().equals("ระบุเอง")){
                    ticket.setValue("");
                    ticket.setEditable(true);   
                    
                }else 
                    
                    ticket.setEditable(false); 
                    
            }
        });
    }
    @FXML
    public void clickConfirm(){
        
        setAllValue();
        e.updateEvent(thisEvent);
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
