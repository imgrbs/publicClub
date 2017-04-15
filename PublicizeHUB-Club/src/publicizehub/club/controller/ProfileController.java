/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.lang.Long.parseLong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.scene.control.Button;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.Event;

/**
 * FXML Controller class
 *
 * @author JIL
 */
public class ProfileController implements Initializable {
    Event ev = new Event();
    ConnectionBuilder cb = new ConnectionBuilder();
    JoinController jc = new JoinController();
    DetailController dc = new DetailController();
    EventController ec = new EventController();
    
//    Date d = new Date();
    
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

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
        try{
            if(findStd.next()){
                LocalDate ld = LocalDate.parse(""+findStd.getString("evEndDate"));
                if(ld.compareTo(LocalDate.now())>-1){
                    ec.addEventToPresentPane(""+findStd.getString("evName"),findStd.getInt("evId"),this.listEventBox1,true);
                }
                else {
                    ec.addEventToPresentPane(""+findStd.getString("evName"),findStd.getInt("evId"),this.listEventBox2,false);
                    while(findStd.next()){
                        ld = LocalDate.parse(""+findStd.getString("evEndDate"));
                        if(ld.compareTo(LocalDate.now())>-1){
                            ec.addEventToPresentPane(""+findStd.getString("evName"),findStd.getInt("evId"),this.listEventBox1,true);
                        }
                        else {
                            ec.addEventToPresentPane(""+findStd.getString("evName"),findStd.getInt("evId"),this.listEventBox2,false);
                        }
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void callMain(){
        System.out.println("callMain() WORK");
        mainStage.show();
        thisStage.close();
    }
    
}
