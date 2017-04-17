/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import com.jfoenix.controls.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import publicizehub.club.model.*;

/**
 *
 * @author JIL
 */
public class NewsController implements Initializable {
    News nw = new News();
    ArrayList<String> myArrList = new ArrayList<String>();
    ConnectionBuilder cb = new ConnectionBuilder();
    
    @FXML
    private JFXTextArea textNews;

    @FXML
    private JFXButton submit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void callAddNews(){
        Stage stage= new Stage();
        Parent root=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/AddNews.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        NewsController controller = fxmlLoader.<NewsController>getController();
        controller.submit.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                controller.insertNew();
            }
        });
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        stage.show();
    }
    
    @FXML
    public void insertNew() {
        String text = textNews.getText();
        Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
        warning.setTitle("เพิ่มข่าว");
        warning.setHeaderText("ยืนยันการเพิ่มข่าว");
        warning.setContentText("ยืนยันความถูกต้องและต้องการเพิ่มข่าว?");
        Optional<ButtonType> result = warning.showAndWait();
        if (result.get() == ButtonType.OK){
            nw.toInsertNews(text);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("เพิ่มข่าวสำเร็จแล้ว");
            alert.showAndWait();
            textNews.setText("");
        }
    }
}
