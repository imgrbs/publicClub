package publicizehub.club.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import publicizehub.club.model.CheckInModel;

/**
 *
 * @author ImagineRabbits
 */
public class CheckInController implements Initializable{
    CheckInModel cim = new CheckInModel();
    @FXML
    private ListView<String> listName;
    @FXML
    private TextField insertCode;
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
    public void callCheckIn(int evId){
        Stage stage= new Stage();
        Parent root=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CheckIn.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
            setEventId(evId);          
            System.out.println("callCheckin : "+eventId);
            
//        CheckInController controller = fxmlLoader.<CheckInController>getController();
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
        Alert warning = null;
        System.out.println("clickConfirm eventId : "+eventId);
        warning = new Alert(Alert.AlertType.CONFIRMATION);
        warning.setTitle("Information!");
        warning.setHeaderText("ยืนยันที่จะลงทะเบียนผู้เข้าร่วมกิจกรรม?");
        Optional<ButtonType> result = warning.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                System.out.println("clickConfirm eventId : "+eventId);
                checkCode(getEventId());
                addNameToList(stdId);
                setValue(stdId,ci.getEvId());
                ci.updateStatusCheckIn(insertCode.getText(),stdId,eventId);
                ci.keepDataCheckIn(cm); 
                System.out.println("Everything is Finished!");               
                /*warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setTitle("Success!");
                warning.setHeaderText("สร้างกิจกรรมสำเร็จ");
                warning.showAndWait();*/
            }
        }
        
    }
    @FXML
    public void checkCode(int evId){
        ResultSet rs = ci.getSelect(evId);
        System.out.println("checkCode : "+evId);
        try{
            while(rs.next()){
                setStdId(rs.getLong("stdId"));
                System.out.println("checkCode std = "+stdId);
                
            }
        }catch(SQLException e){
            System.out.println("SQL Exception");
        }
    }
    @FXML
    public void addNameToList(long stdId){
        ResultSet rs = ci.getName(stdId);
        System.out.println("addNameToList : "+stdId);
        System.out.println("addNameToList : "+insertCode.getText());
        ObservableList<String> items =FXCollections.observableArrayList();
        try{
            while(rs.next()){
                String name = rs.getString("stdName");
                items.add(stdId+"\t"+name);
                System.out.println("addNameToList name = "+name);
                System.out.println("addNameToList : "+insertCode.getText());
            }
            listName.setItems(items);
        }catch(SQLException e){
            System.out.println("SQL Exception");
        }
    }
    @FXML
    public void setValue(long stdId,int eventId){
        cm = new CheckInModel(stdId, eventId, insertCode.getText(), LocalDate.now(), LocalTime.now());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
   
}
