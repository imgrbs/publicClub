package publicizehub.club.controller;

import com.jfoenix.controls.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.EventModel;

/**
 * FXML Controller class
 *
 * @author JIL
 */
public class EditEventController {

    private static final Logger LOGGER = Logger.getLogger(EditEventController.class.getName());
    private EventModel ev = new EventModel();
    private LoginController lc = new LoginController();
    ConnectionBuilder cb = new ConnectionBuilder();

    private EventModel thisEvent;

    private int resultType;
    private int evType = -1;
    private ArrayList typeList = new ArrayList<>();

    private long stdId = lc.getStdId();

    private Stage thisStage;

    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }

    public EventModel getThisEvent() {
        return thisEvent;
    }

    public void setThisEvent(EventModel thisEvent) {
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
    private JFXComboBox<String> ticket;
    @FXML
    private ToggleGroup type;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField place;
    @FXML
    private JFXComboBox<String> eventType;
    
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
    public void showValue() {
        eventName.setText(thisEvent.getEvName());
        startDate.setValue(thisEvent.getEvDate());
        endDate.setValue(thisEvent.getEvEndDate());
        startTime.setValue(thisEvent.getEvTime());
        endTime.setValue(thisEvent.getEvEndTime());
        startRegis.setValue(thisEvent.getEvStartRegis());
        ticket.setValue("" + thisEvent.getEvTicket());
        description.setText(thisEvent.getEvDescrip());
        place.setText(thisEvent.getEvPlace());
        setType(thisEvent.getEvType()); 
        //ticket.setEditable(false);
    }
    @FXML
    public void setTypeToComboBox(){
        ResultSet rs = ev.getEventType();
        try{
            while(rs.next()){ 
                typeList.add(rs.getString("typeName"));
            }
            
        }catch(SQLException sqe){
            LOGGER.log(Level.WARNING, "setTypeToComboBox : SQLException",sqe);
        }catch(Exception e){
            LOGGER.log(Level.WARNING, "setTypeToComboBox : Exception",e);
        }
        cb.logout();
        System.out.println("Event Type : "+typeList);
        eventType.getItems().addAll(typeList);
    }
    
    @FXML
    public void setType(int evType) {
        ResultSet rs = ev.getEventType(evType);
        try{
            while(rs.next()){
                String evTypeText = rs.getString("typeName");
                System.out.println("evTypeText : "+evTypeText);
                eventType.setValue(evTypeText);
            }
        }catch(SQLException sqe){
            LOGGER.log(Level.WARNING, "setTypeToComboBox : SQLException",sqe);
        }catch(Exception e){
            LOGGER.log(Level.WARNING, "setTypeToComboBox : Exception",e);
        }
        cb.logout();
    }

    /*@FXML
    public void setAllValue() {
        showValue();
        evTypeResult();
    }*/

    @FXML
    public void evTypeResult() {
        if (camp.isSelected()) {
            evType = 0;
        } else if (seminar.isSelected()) {
            evType = 1;
        } else if (other.isSelected()) {
            evType = 2;
        }
        thisEvent.setEvType(evType);
    }

    @FXML
    public int typeResult() {
        int evType=0;
        ResultSet rs = ev.getEventType(eventType.getValue());
        System.out.println("eventType.getValue() : "+eventType.getValue());
        try{
            while(rs.next()){ 
                evType = rs.getInt("typeValue");
                System.out.println("evType : "+evType);
            }
            
        }catch(SQLException sqe){
            LOGGER.log(Level.WARNING, "setTypeToComboBox : SQLException",sqe);
        }catch(Exception e){
            LOGGER.log(Level.WARNING, "setTypeToComboBox : Exception",e);
        }
        cb.logout();
        
        return evType;
    }
    
    @FXML
    public void checkCustomize() {
        if (ticket.getValue().equals("ระบุเอง")) {
            ticket.setEditable(true);
            ticket.setValue("");
        } else {
            ticket.setEditable(false);
        }
    }

    @FXML
    public void setValueToCombobox() {
        ticket.getItems().addAll("5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "75", "100", "ระบุเอง");
        ticket.setEditable(true);
    }

    @FXML
    public void clickConfirm() {
        Alert warning = null;
        getTypeFromCombo();
        if (checkEditEvent()) {
            warning = new Alert(Alert.AlertType.CONFIRMATION);
            warning.setTitle("ยืนยันการแก้ไข");
            warning.setHeaderText("คุณยังไม่ได้แก้ไขกิจกรรม");
            warning.setContentText("ยืนยันที่จะไม่แก้ไขใช่หรือไม่");
            Optional<ButtonType> result = warning.showAndWait();
            if (result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    thisStage.close();
                }
            }
        } else if(validateField()){
            warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("Error!");
            warning.setHeaderText("กรอกข้อมูลไม่ครบ");
            warning.setContentText("กรุณากรอกข้อมูลให้ครบทุกช่อง!");
            warning.showAndWait(); 
        }else if(validateDate()){
            if(validateTime()){
                warning = new Alert(Alert.AlertType.CONFIRMATION);
                warning.setTitle("ยืนยันการแก้ไข");
                warning.setHeaderText("แก้ไขข้อมูลกิจกรรม");
                warning.setContentText("ยืนยันที่จะแก้ไขข้อมูลกิจกรรม");
                Optional<ButtonType> result = warning.showAndWait();
                if (result.isPresent()) {
                    if (result.get() == ButtonType.OK) {
                        setValueToObj();
                        ev.updateEvent(thisEvent);
                        warning = new Alert(Alert.AlertType.INFORMATION);
                        warning.setTitle("Success!");
                        warning.setHeaderText("แก้ไขข้อมูล");
                        warning.setContentText("แก้ไขข้อมูลสำเร็จแล้ว !");
                        warning.showAndWait();
                        thisStage.close();
                    }
                }
            }
        }
        resetEvent(thisEvent);
    }

    public boolean validateField(){
        boolean check = false;
        if(ticket.getValue()==null||ticket.getValue().equals("ระบุเอง")||ticket.getValue().equals("")||
           eventName.getText().equals("")||description.getText().equals("")||
           place.getText().equals("")||startRegis.getValue()==null||
           startDate.getValue()==null||endDate.getValue()==null||
           startTime.getValue()==null||endTime.getValue()==null||evType==-1){
            check = true;
        }
        System.out.println("evType : "+evType);
        if(ticket.getValue()!=null) {
            checkNumber(check);
        }
        return check;
    }
    
    public boolean validateDate(){
        boolean check = false;
        Alert warning = new Alert(Alert.AlertType.ERROR);
        warning.setHeaderText("Error !");
        LocalDate dateRegis = startRegis.getValue();
        LocalDate dateStart = startDate.getValue();
        LocalDate dateEnd = endDate.getValue();
        if(dateRegis!=null && dateStart != null && dateEnd !=null){
            if(dateStart.compareTo(dateEnd)>0){
                warning.setContentText("กรุณาใส่วันเริ่มกิจกรรม ให้น้อยกว่าหรือเท่ากับ วันจบกิจกรรม");
                warning.showAndWait();
            } else {
                check = true;
            }
        }
        return check;
    }
    
    public boolean validateTime(){
        boolean check = false;
        Alert warning = new Alert(Alert.AlertType.ERROR);
        warning.setHeaderText("Error !");
        LocalDate dateStart = startDate.getValue();
        LocalDate dateEnd = endDate.getValue();
        LocalTime timeStart = startTime.getValue();
        LocalTime timeEnd = endTime.getValue();
        LocalTime tempTime = startTime.getValue().plusMinutes(30);
        LocalTime time20PM = LocalTime.of(20, 0, 0);
        LocalTime time05AM = LocalTime.of(5, 0, 0);
        if(dateStart.compareTo(dateEnd)==0){
            if(timeStart.compareTo(time20PM)<=0&&
               timeStart.compareTo(time05AM)>=0&&
               timeEnd.compareTo(time20PM)<=0&&
               timeEnd.compareTo(time05AM)>=0){
                if(timeStart.compareTo(timeEnd)>-1){
                    warning.setContentText("กรุณาใส่กรุณาใส่เวลาให้ถูกต้อง");
                    warning.showAndWait();
                }else if(timeEnd.compareTo(tempTime)<0){
                    warning.setContentText("กรุณาใส่กรุณาจบกิจกรรมอย่างน้อย 30 นาทีขึ้นไป");
                    warning.showAndWait();
                }else {
                    check = true;
                }
            }else{
                warning.setContentText("กรุณาใส่กรุณาใส่เวลาให้ถูกต้อง");
                warning.showAndWait();
            }
            
        }else {
            if(timeStart.compareTo(time20PM)<=0&&
               timeStart.compareTo(time05AM)>=0&&
               timeEnd.compareTo(time20PM)<=0&&
               timeEnd.compareTo(time05AM)>=0){
                check = true;
            }else{
                warning.setContentText("กรุณาใส่กรุณาใส่เวลาให้ถูกต้อง");
                warning.showAndWait();
            }
        }
        return check;
    }
    
    @FXML
    public void checkNumber(boolean check){
        Alert warning = null;
        warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Error!");
        if(ticket.getValue().length()<=5){ 
            for(int i=0;i<ticket.getValue().length();i++){
                if (ticket.getValue().charAt(i) < '0' || ticket.getValue().charAt(i) > '9') {
                    check=false;
                    warning.setHeaderText("จำนวนบัตรไม่ถูกต้อง");
                    warning.setContentText("กรุณาใส่จำนวนบัตรให้ถูกต้อง (0-9)");
                    warning.showAndWait();
                    break;
                }
            }
        }else {
            warning.setHeaderText("จำนวนบัตรไม่ถูกต้อง");
            warning.setContentText("กรุณาใส่จำนวนบัตรให้ถูกต้อง");
            warning.showAndWait();
        }
    }
    @FXML
    public void getTypeFromCombo(){
        ResultSet rs = ev.getEventType(eventType.getValue());
        System.out.println("eventType.getValue() : "+eventType.getValue());
        try{
            while(rs.next()){ 
                this.evType = rs.getInt("typeValue");
                System.out.println("evType : "+evType);
            }
            
        }catch(SQLException sqe){
            LOGGER.log(Level.WARNING, "setTypeToComboBox : SQLException",sqe);
        }catch(Exception e){
            LOGGER.log(Level.WARNING, "setTypeToComboBox : Exception",e);
        }
        cb.logout();
        
    }
    @FXML
    public void closeStage() {
        getThisStage().close();
    }

    @FXML
    public void callEditEvent(EventModel event) {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/EditEvent.fxml"));
        try {
            root = (Parent) fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        EditEventController controller = fxmlLoader.<EditEventController>getController();
        controller.setThisStage(stage);
        controller.setThisEvent(controller.resetEvent(event));
        controller.setValueToCombobox();
        controller.setTypeToComboBox();
        controller.showValue();
        
        Scene scene = new Scene(root);
        try {
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.show();
    }

    public boolean checkEditEvent() {
        boolean checkEdit = false;
        if (getThisEvent().getEvName().equals(eventName.getText())
                && getThisEvent().getEvDate().equals(startDate.getValue())) {
            if (getThisEvent().getEvEndDate().equals(endDate.getValue())
                    && getThisEvent().getEvTime().equals(startTime.getValue())) {
                if (getThisEvent().getEvEndTime().equals(endTime.getValue())
                        && getThisEvent().getEvStartRegis().equals(startRegis.getValue())) {
                    if ((getThisEvent().getEvTicket() + "").equals("" + ticket.getValue())
                            && getThisEvent().getEvType() == typeResult()
                            && getThisEvent().getEvDescrip().equalsIgnoreCase(description.getText())) {
                        if (getThisEvent().getEvPlace().equalsIgnoreCase(place.getText())) {
                            checkEdit = true;
                        }
                    }
                }
            }
        }
        return checkEdit;
    }

    public void setValueToObj() {
        thisEvent = new EventModel(eventName.getText(), description.getText(), startDate.getValue(),
                endDate.getValue(), startRegis.getValue(), endDate.getValue().plusDays(15),
                place.getText(), Integer.parseInt(ticket.getValue()), startTime.getValue(),
                endTime.getValue(), typeResult(), this.thisEvent.getEvId());
    }

    public EventModel resetEvent(EventModel event){
        ResultSet rs = ev.getSelect(event.getEvId());
        try{
            if(rs.next()){
                event = new EventModel(rs.getString("evName"),
                rs.getString("evDescrip"),rs.getDate("evStartDate"),
                rs.getDate("evEndDate"),rs.getDate("evStartRegis"),
                rs.getDate("evEndFeedback"),rs.getString("evPlace"),
                rs.getInt("evTicket"),rs.getTime("evTime"),
                rs.getTime("evEndTime"),rs.getInt("evType"),rs.getInt("evId")
                );
                setNewValue(event);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return event;
    }
    
    public void setNewValue(EventModel event){
        eventName.setText(event.getEvName());
        startDate.setValue(event.getEvDate());
        endDate.setValue(event.getEvEndDate());
        startTime.setValue(event.getEvTime());
        endTime.setValue(event.getEvEndTime());
        startRegis.setValue(event.getEvStartRegis());
        ticket.setValue("" + event.getEvTicket());
        description.setText(event.getEvDescrip());
        place.setText(event.getEvPlace());
        setType(event.getEvType());
        
    }
}
