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
import java.awt.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private Event thisEvent = new Event();
    
    private String evName; 
    private String evDescrip;
    private LocalDate evDate;
    private LocalDate evEndDate;
    private LocalDate evStartRegis;
    private LocalDate evEndFeedback;
    private String evPlace;
    private int evTicket;
    private int currentMember;
    private LocalTime evTime;
    private LocalTime evEndTime;
    private int evType;
    private long stdId=lc.getStdId();
    
    
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
    public void setAllValue(){
        thisEvent.setEvName(eventName.getText()); 
        thisEvent.setEvDescrip(description.getText());
        thisEvent.setEvDate(startDate.getValue());
        thisEvent.setEvEndDate(endDate.getValue()); 
        thisEvent.setEvStartRegis(startRegis.getValue());
        thisEvent.setEvEndFeedback(startRegis.getValue().plusDays(15));
        thisEvent.setEvTime(startTime.getValue());
        thisEvent.setEvEndTime(endTime.getValue());
        thisEvent.setEvTicket(Integer.parseInt(ticket.getValue()));
        thisEvent.setEvPlace(place.getText());
        thisEvent.setStdId(stdId);
        evTypeResult();
        thisEvent.setEvType(evType);
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
        if(ticket.getTypeSelector().equals("ระบุเอง")){
            ticket.setValue("");
            ticket.setEditable(true);
            
        }else 
            ticket.setEditable(false);
    } 
    @FXML
    public void setValueToCombobox(){
        
        ticket.getItems().addAll("5","10","15","20","25","30","35","40","45","50","75","100","ระบุเอง");  
        //if(ticket.selectionModelProperty().equals("ระบุเอง")){
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
        //}  
    }
    @FXML
    public void clickConfirm(){
        setAllValue();
        confirmBtn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                e.createEvent(thisEvent);
            }
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setValueToCombobox();
        
        
    }
    
}
