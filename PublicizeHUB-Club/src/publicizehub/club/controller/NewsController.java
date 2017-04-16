/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

}
