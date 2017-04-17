/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;


import com.jfoenix.controls.JFXTreeTableView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;

/**
 * FXML Controller class
 *
 * @author budsagorn_ss
 */
public class ShowFeedbackController implements Initializable {

    @FXML
    private BarChart<?, ?> feedbackChart;

    @FXML
    private Label numberBuy;

    @FXML
    private Label numberJoin;

    @FXML
    private Label evName;

    @FXML
    private JFXTreeTableView<?> tableStd;

    @FXML
    private TreeTableColumn<?, ?> stdName;

    @FXML
    private TreeTableColumn<?, ?> stdSurname;

    @FXML
    private TreeTableColumn<?, ?> stdDepart;

    @FXML
    private TreeTableColumn<?, ?> stdBuy;

    @FXML
    private TreeTableColumn<?, ?> stdCheckin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       XYChart.Series setl = new XYChart.Series<>();
        setl.getData().add(new XYChart.Data("Q1", 60));
        setl.getData().add(new XYChart.Data("Q2", 98));
        setl.getData().add(new XYChart.Data("Q3", 35));
        setl.getData().add(new XYChart.Data("Q4", 73));
        setl.getData().add(new XYChart.Data("Q5", 58));
        setl.getData().add(new XYChart.Data("Q6", 100));
        setl.getData().add(new XYChart.Data("Q7", 93));
        setl.getData().add(new XYChart.Data("Q8", 78));
        setl.getData().add(new XYChart.Data("Q9", 88));
        setl.getData().add(new XYChart.Data("Q10", 20));
        feedbackChart.getData().addAll(setl);
        
    }    
    
}
