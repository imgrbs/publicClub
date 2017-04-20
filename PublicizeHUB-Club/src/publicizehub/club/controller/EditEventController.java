package publicizehub.club.controller;

import publicizehub.club.model.TableEvent;
import com.jfoenix.controls.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import publicizehub.club.model.Event;

/**
 * FXML Controller class
 *
 * @author JIL
 */
public class EditEventController implements Initializable {
    Event ev = new Event();
    TableEvent te = new TableEvent();
    LoginController lc = new LoginController();
   
    private Event thisEvent;
    private long stdId=lc.getStdId();
    
    private Stage thisStage;

    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }
    
    public EditEventController() {
    }
    
    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }

    public Event getThisEvent() {
        return thisEvent;
    }

    public void setThisEvent(Event thisEvent) {
        this.thisEvent = thisEvent;
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
        eventName.setText(getThisEvent().getEvName());        
        startDate.setValue(thisEvent.getEvDate());        
        endDate.setValue(thisEvent.getEvEndDate());        
        startTime.setValue(thisEvent.getEvTime());
        endTime.setValue(thisEvent.getEvEndTime());
        startRegis.setValue(thisEvent.getEvStartRegis());
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
    public void checkCustomize(){
        if(ticket.getValue().equals("ระบุเอง")){
            ticket.setEditable(true);   
            ticket.setValue("");
        }else {
            ticket.setEditable(false);
        }
    } 
    
    @FXML
    public void setValueToCombobox(){
        ticket.getItems().addAll("5","10","15","20","25","30","35","40","45","50","75","100","ระบุเอง");
    }
    
    @FXML
    public void clickConfirm(){
        setAllValue();
        ev.updateEvent(thisEvent);
    } 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        setValueToCombobox();
//        showValue();
    }    
    
    @FXML
    public void closeStage(){
        getThisStage().close();
    }
    
    @FXML
    public void callEditEvent(Event event){
        Stage stage= new Stage();
        Parent root=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/EditEvent.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        EditEventController controller = fxmlLoader.<EditEventController>getController();
        controller.setThisStage(stage);
        controller.setThisEvent(event);
        controller.setValueToCombobox();
        controller.showValue();
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
