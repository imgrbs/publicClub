package publicizehub.club.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.lang.Long.parseLong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.Event;

/**
 * FXML Controller class
 *
 * @author JIL
 */
public class ProfileController {
    Event ev = new Event();
    ConnectionBuilder cb = new ConnectionBuilder();
    
    JoinController jc = new JoinController();
    DetailController dc = new DetailController();
    EventController ec = new EventController();
    
    private long stdId;
    
    private Stage mainStage;
    private Stage thisStage;
    
    @FXML
    private Label labelId;
    @FXML
    private Label labelName;
    @FXML
    private Label labelDepartment;
    @FXML
    private Label labelEvName;
    
    @FXML
    private VBox listEventBox1 = new VBox();
    @FXML
    private VBox listEventBox2 = new VBox();
    
    @FXML
    private Button backBtn;
    
    
    public void setLabelId(String labelId) {
        this.labelId.setText(labelId);
    }

    public void setLabelName(String labelName) {
        this.labelName.setText(labelName);
    }

    public void setLabelDepartment(String labelDepartment) {
        this.labelDepartment.setText(labelDepartment);
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

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
    
    @FXML
    public void getEventToProfile(){
        System.out.println("Befor Get Event");
        ResultSet rs = ev.getSelect(parseLong(this.labelId.getText()));
        cb.logout();
        System.out.println("After Get Event");
        try{
            if(rs.next()){
                System.out.println("Event Come");
                setEventToGui(rs.getInt("evId"));
                while(rs.next()){
                    setEventToGui(rs.getInt("evId"));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        cb.logout();
    }

    public void setEventToGui(int eventId){
        ResultSet findStd = ev.getSelect(eventId);
        Event event = null;
        try{
            if(findStd.next()){
                event = new Event(findStd.getString("evName"),
                findStd.getString("evDescrip"),findStd.getDate("evStartDate"),
                findStd.getDate("evEndDate"),findStd.getDate("evStartRegis"),
                findStd.getDate("evEndFeedback"),findStd.getString("evPlace"),
                findStd.getInt("evTicket"),findStd.getInt("currentMember"),
                findStd.getTime("evTime"),findStd.getTime("evEndTime"),
                findStd.getInt("evType"),findStd.getInt("evId")
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        ec.setStdId(getStdId());
        LocalDate ld = LocalDate.parse(""+event.getEvEndDate());
        if(ld.compareTo(LocalDate.now())>-1){ 
            ec.addEventToPresentPane(event,this.listEventBox1,true,true); 
        }
        else {
            ec.addEventToPresentPane(event,this.listEventBox2,false,true);
        }
    }
    
    /* Method สำหรับ เปลี่ยน Stage */
    public void callMain(){
        System.out.println("callMain() WORK");
        mainStage.show(); // แสดง Stage หลัก ( Main )
        thisStage.close(); // ปิด Stage ปัจจุบัน ( Profile )
    }
    

    
}
