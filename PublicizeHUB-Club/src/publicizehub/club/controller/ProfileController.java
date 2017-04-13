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

/**
 * FXML Controller class
 *
 * @author JIL
 */
public class ProfileController implements Initializable {
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
    
     @FXML
    public void addEventToPresentPane(String eventName,int eventId) {
        Pane p = new Pane();
        labelEvName = new Label(eventName);
        Button joinbtn = new Button("Join");
        Button detailbtn = new Button("Detail");
        joinbtn.getStyleClass().add("joinbtnSearch");
        detailbtn.getStyleClass().add("detailbtnSearch");
        joinbtn.setLayoutX(285);
        joinbtn.setLayoutY(100);
        detailbtn.setLayoutX(370);
        detailbtn.setLayoutY(100);
        p.getChildren().add(labelEvName);
        p.getChildren().add(joinbtn);
        p.getChildren().add(detailbtn);
        joinbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //jc.toJoinEvent(eventId);
            }
        });
        detailbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //dc.callDetail(eventId);
            }
        });
        listEventBox1.setMargin(p,new Insets(15,25,15,30));
        p.setStyle("-fx-background-color: #" + "ffffff" + ";" +
                   "-fx-background-radius: 10px;" +
                   "-fx-effect: dropshadow(three-pass-box, #4d4d4d, 5, 0, 0, 1);");
        labelEvName.setStyle("-fx-padding: 30px 0px 0px 50px;"+
                   "-fx-font-size: 30px;"+
                   "-fx-text-fill: #000000;");
        p.setPrefSize(480,150);
        listEventBox1.getChildren().add(p);
    }
}
