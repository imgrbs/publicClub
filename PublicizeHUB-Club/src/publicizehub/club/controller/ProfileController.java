/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import static java.lang.Long.parseLong;
import java.sql.ResultSet;
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
    
    private Stage temp;
    
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

    public void setStage(Stage temp) {
        this.temp = temp;
    }
    
    @FXML
    public void getEventToProfile(){
        ResultSet rs = ev.getSelect(parseLong(this.labelId.getText()));
        try{
            while(rs.next()){
                if(rs.getString("evEndDate").compareTo(anotherString)){
                    
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        cb.logout();
    }
}
