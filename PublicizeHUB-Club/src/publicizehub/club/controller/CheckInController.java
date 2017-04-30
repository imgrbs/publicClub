package publicizehub.club.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import publicizehub.club.model.CheckInModel;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.EventModel;

/**
 *
 * @author ImagineRabbits
 */
public class CheckInController implements Initializable{
    CheckInModel cim = new CheckInModel();
    ConnectionBuilder cb = new ConnectionBuilder();
    ObservableList<String> items =FXCollections.observableArrayList();
    @FXML
    private ListView<String> listName;
    @FXML
    private TextField insertCode;
    @FXML
    private Label eventName;
    private long stdId;
    private int eventId;
    CheckInModel cm = null;
    CheckInModel ci = new CheckInModel();

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    
    @FXML
    public void callCheckIn(EventModel event){
        Stage stage= new Stage();
        Parent root=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CheckIn.fxml")); 

        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
                      
        CheckInController controller = fxmlLoader.<CheckInController>getController();
        controller.setEventId(event.getEvId());
        controller.eventName.setText(event.getEvName());
        System.out.println(event.getEvId());
        controller.addNameToListFirst(event.getEvId());
        System.out.println("callCheckin : "+event.getEvId());
            
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            System.out.println("Exception");
        }
        stage.show();
    }
    @FXML
    public void clickConfirm(){
        
        System.out.println(getEventId());
        Alert warning = null;
        System.out.println("clickConfirm eventId : "+getEventId());
        warning = new Alert(Alert.AlertType.CONFIRMATION);
        warning.setTitle("Information!");
        warning.setHeaderText("ยืนยันที่จะลงทะเบียนผู้เข้าร่วมกิจกรรม?");
        Optional<ButtonType> result = warning.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                System.out.println("clickConfirm eventId : "+eventId);
                checkCode(insertCode.getText());
                
            }
        }
        
    }
    @FXML
    public void checkCode(String evCode){
        ResultSet rs = ci.getSelect(evCode);
        System.out.println("checkCode : "+evCode);
        Alert warning = null;
        try{
            if(rs.next()){
                setStdId(rs.getLong("stdId"));
                System.out.println("checkCode std = "+getStdId());
                setValue(getStdId(),getEventId());
            // METHOD ส่ง LOG Checkin
                ci.sentLogCheckin(cm);
            // Update statusCheckin ใน logJoining
                ci.updateStatusCheckIn(insertCode.getText(),getStdId(),getEventId());
            // เพิ่มคนใน List
                addNameToList(getStdId());
            // ลบ GEN CODE    
                ci.deleteCode(insertCode.getText());
            }else if(insertCode.getText().length()<6){
                warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setTitle("รหัสไม่ถูกต้อง!");
                warning.setHeaderText("รหัสที่กรอกมาไม่ครบ โปรดตรวจสอบอีกครั้ง");
                warning.showAndWait(); 
            }else{
                warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setTitle("รหัสไม่ถูกต้อง!");
                warning.setHeaderText("รหัสที่กรอกมาไม่ถูกต้อง โปรดตรวจสอบอีกครั้ง");
                warning.showAndWait(); 
            }
        }catch(SQLException e){
            System.out.println("SQL Exception");
        }
        cb.logout();
    }
    @FXML
    public void addNameToList(long stdId){
        ResultSet rs = ci.getName(stdId);
        System.out.println("addNameToList : "+stdId);
        System.out.println("addNameToList : "+insertCode.getText());
        
        try{
            while(rs.next()){
                String name = rs.getString("std_name");               
                items.add(stdId+"\t"+name);
                System.out.println("addNameToList name = "+name);
                System.out.println("addNameToList : "+insertCode.getText());
            }
            listName.setItems(items);
        }catch(SQLException e){
            System.out.println("SQL Exception");
        }
        cb.logout();
    }
    
    @FXML
    public void addNameToListFirst(int evId){
        ResultSet rs = ci.getChecedName(evId);
 
        try{
            while(rs.next()){ 
                long std_id = rs.getLong("stdId");
                ResultSet getName = ci.getName(std_id);
                while(getName.next()){
                    String name = getName.getString("std_name");
                    String data = std_id+"\t"+name;
                    items.add(data);
                }           
            }
            
            listName.setItems(items);
        }catch(SQLException e){
            System.out.println("SQL Exception");
        }
        cb.logout();
    }
    
    @FXML
    public void setValue(long stdId,int eventId){
        cm = new CheckInModel(stdId, eventId, insertCode.getText(), LocalDate.now(), LocalTime.now());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
   
}
