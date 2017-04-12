/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

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

/**
 * FXML Controller class
 *
 * @author budsagorn_ss
 */
public class ShowFeedbackController implements Initializable {

     @FXML
    private BarChart<?, ?> FeedbackChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

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
        FeedbackChart.getData().addAll(setl);
                     
    }    
    
}
