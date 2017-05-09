package publicizehub.club.controller;


import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
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

import java.util.logging.Logger;
import java.util.logging.Level;


import publicizehub.club.model.EventModel;
import publicizehub.club.model.LoginModel;

/**
 * FXML Controller class
 *
 * @author JIL
 */
public class CreateEventController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(EditEventController.class.getName());
    EventModel e = new EventModel();
    LoginModel lm = new LoginModel();
    EventModel thisEvent = null;
    
    LoginController lc = new LoginController();
    
    private int evType = -1;
    private long stdId=this.lm.getStdId();
    
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
    private JFXComboBox<String> ticket;
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
    private Label warnNum;
    private String customText="ระบุเอง";

    public LoginModel getLm() {
        return lm;
    }

    public void setLm(LoginModel lm) {
        this.lm = lm;
    }
    
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
        thisEvent = new EventModel(stdId,eventName.getText(),description.getText(),startDate.getValue(),endDate.getValue(),
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
    
    @FXML
    public void checkCustomize(){
        String customText="ระบุเอง";
        if(ticket.getValue().equals(customText)){
            ticket.setEditable(true);   
            ticket.setValue(" ");
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
        Alert warning = null; 
        evTypeResult(); 
        if(eventName.getText().length()<5 ||
           description.getText().length()<25 || 
           place.getText().length()<5 || 
           eventName.getText().length()>125 || 
           description.getText().length()>500 ||
           place.getText().length()>150){
            warning = new Alert(Alert.AlertType.ERROR); 
            warning.setTitle("Error!");
            warning.setHeaderText("ความยาวน้อยไป หรือมากเกินไป");
            warning.setContentText("ชื่อกิจกรรม , รายละเอียด และ สถานที่ มีความยาวตัวอักษรน้อยเกินไป หรือมากเกินไป");
            warning.showAndWait();
        }else {
            if(validateField()){
                warning = new Alert(Alert.AlertType.ERROR);
                warning.setTitle("Error!");
                warning.setHeaderText("กรอกข้อมูลไม่ครบ");
                warning.setContentText("กรุณากรอกข้อมูลให้ครบทุกช่อง!");
                warning.showAndWait();            
            }else if(validateDate()){ 
                if(validateTime()){ 
                    warning = new Alert(Alert.AlertType.CONFIRMATION);
                    warning.setTitle("Information!");
                    warning.setHeaderText("ยืนยันที่จะสร้างกิจกรรม"); 
                    warning.setContentText("ข้อมูลถูกต้องครบถ้วนแล้ว ยืนยันที่จะสร้าง?"); 
                    Optional<ButtonType> result = warning.showAndWait();
                    if(result.isPresent()){
                        if(result.get() == ButtonType.OK){
                            setAllValue();
                            e.createEvent(thisEvent); 
                            try{
                                setEmptyField();
                            }catch(NullPointerException e){
                                LOGGER.log(Level.WARNING, "set NULL !");
                            }
                            warning = new Alert(Alert.AlertType.INFORMATION);
                            warning.setTitle("Success!");
                            warning.setHeaderText("สร้างกิจกรรมสำเร็จ");
                            warning.showAndWait();
                        }
                    }
                }
                
            }
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
        catch(IOException e){
            LOGGER.log(Level.WARNING, "root : Exception",e);
        }
        CreateEventController controller = fxmlLoader.<CreateEventController>getController();
        controller.setLm(this.lm);
        controller.setThisStage(stage);
        controller.stdId = this.lm.getStdId();
        controller.getCancelBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.closeStage();
            }
        });
        Scene scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();
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
    public void closeStage(){
        getThisStage().close();
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
        if(ticket.getValue()!=null) {
            checkNumber(check);
        }
        return check;
    }
    
    public void setEmptyField(){
        String cusText="ระบุเอง";
        eventName.setText("");
        description.setText("");
        place.setText("");
        try{
            startRegis.setValue(null);
            startDate.setValue(null);
            endDate.setValue(null);
            startTime.setValue(null);
            endTime.setValue(null);
        }catch(NullPointerException e){
            LOGGER.log(Level.WARNING, "set NULL !");
        }catch(Exception e){
            LOGGER.log(Level.WARNING, "set NULL !");
        }
        evType=-1;
        camp.setSelected(false);
        seminar.setSelected(false);
        other.setSelected(false);
        ticket.setValue(cusText);
    }
    
    public boolean validateDate(){
        boolean check = false;
        Alert warning = new Alert(Alert.AlertType.ERROR);
        warning.setHeaderText("Error !");
        LocalDate dateRegis = startRegis.getValue();
        LocalDate dateStart = startDate.getValue();
        LocalDate dateEnd = endDate.getValue();
        if(dateRegis!=null && dateStart != null && dateEnd !=null){
            if(dateRegis.compareTo(dateStart)>=0 || 
               dateRegis.compareTo(LocalDate.now())<0 ||
               dateRegis.compareTo(dateEnd)>=0){
                warning.setContentText("กรุณาใส่วันสมัครให้น้อยกว่าหรือเท่ากับวันเริ่มกิจกรรม , มากกว่าหรือเท่ากับวันปัจจุบัน และน้อยกว่าวันจบกิจกรรม");
                warning.showAndWait();
            }else if(dateStart.compareTo(dateEnd)>0){
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
}
