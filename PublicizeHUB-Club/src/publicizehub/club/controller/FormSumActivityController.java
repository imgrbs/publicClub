package publicizehub.club.controller;

import com.jfoenix.controls.JFXTreeTableView;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import publicizehub.club.model.FeedbackModel;

/**
 * FXML Controller class
 *
 * @author budsagorn_ss
 */
public class FormSumActivityController implements Initializable {

    FeedbackModel fbm = new FeedbackModel();

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

    int[] averQ = new int[10];

    public FormSumActivityController() {
        setValueAver();
    }

    @FXML
    public void setValueAver() {
        for (int i = 0; i < averQ.length; i++) {
            averQ[i] = 0;
        }
    }

    public void calculateFeedback(int evId) throws SQLException {

        ResultSet result = null;
        result = fbm.getSumQ(evId);
        
        int numPeople = 0;
        numPeople = fbm.numPeople(evId);
        
        int sumQ1 = 0;
        int sumQ2 = 0;
        int sumQ3 = 0;
        int sumQ4 = 0;
        int sumQ5 = 0;
        int sumQ6 = 0;
        int sumQ7 = 0;
        int sumQ8 = 0;
        int sumQ9 = 0;
        int sumQ10 = 0;
        int setSumQ1 = 0;
        int setSumQ2 = 0;
        
        try {
   
            while (result.next()) {
                sumQ1 += result.getInt("sumQ1");
                sumQ2 += result.getInt("sumQ2");
                sumQ3 += result.getInt("sumQ3");
                sumQ4 += result.getInt("sumQ4");
                sumQ5 += result.getInt("sumQ5");
                sumQ6 += result.getInt("sumQ6");
                sumQ7 += result.getInt("sumQ7");
                sumQ8 += result.getInt("sumQ8");
                sumQ9 += result.getInt("sumQ9");
                sumQ10 += result.getInt("sumQ10");
            }

            averQ[0] = sumQ1 / numPeople;
            averQ[1] = sumQ2 / numPeople;
            averQ[2] = sumQ3 / numPeople;
            averQ[3] = sumQ4 / numPeople;
            averQ[4] = sumQ5 / numPeople;
            averQ[5] = sumQ6 / numPeople;
            averQ[6] = sumQ7 / numPeople;
            averQ[7] = sumQ8 / numPeople;
            averQ[8] = sumQ9 / numPeople;
            averQ[9] = sumQ10 / numPeople;

            //คิด % แต่ละข้อโดยแบ่งเป็น 2 ชุด 
            double x = 0.2;
            int percentQ1 = (int) (averQ[0] * x);
            int percentQ2 = (int) (averQ[1] * x);
            int percentQ3 = (int) (averQ[2] * x);
            int percentQ4 = (int) (averQ[3] * x);
            int percentQ5 = (int) (averQ[4] * x);

            int percentQ6 = (int) (averQ[5] * x);
            int percentQ7 = (int) (averQ[6] * x);
            int percentQ8 = (int) (averQ[7] * x);
            int percentQ9 = (int) (averQ[8] * x);
            int percentQ10 = (int) (averQ[9] * x);

            setSumQ1 = (int) (percentQ1 + percentQ2 + percentQ3 + percentQ4 + percentQ5);
            setSumQ2 = (int) (percentQ6 + percentQ7 + percentQ8 + percentQ9 + percentQ10);
            
            //ส่งค่ากลับไป model เพื่อเอาค่าไปเก็บไว้ใน DB
             fbm.insertAvgrValue(evId, numPeople, averQ[0], averQ[1], averQ[2], averQ[3], averQ[4], averQ[5],
                                averQ[6], averQ[7], averQ[8], averQ[9], setSumQ1, setSumQ2);
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

        
    }

    //set ค่าที่จะโชว์กราฟ
    @FXML
    public void setFeedbackChart() {
        XYChart.Series setl = new XYChart.Series<>();
        setl.getData().add(new XYChart.Data("Q1", averQ[0]));
        System.out.println(averQ[0]);
        setl.getData().add(new XYChart.Data("Q2", averQ[1]));
        setl.getData().add(new XYChart.Data("Q3", averQ[2]));
        setl.getData().add(new XYChart.Data("Q4", averQ[3]));
        setl.getData().add(new XYChart.Data("Q5", averQ[4]));
        setl.getData().add(new XYChart.Data("Q6", averQ[5]));
        setl.getData().add(new XYChart.Data("Q7", averQ[6]));
        setl.getData().add(new XYChart.Data("Q8", averQ[7]));
        setl.getData().add(new XYChart.Data("Q9", averQ[8]));
        setl.getData().add(new XYChart.Data("Q10", averQ[9]));
        feedbackChart.getData().addAll(setl);
    }

    @FXML
    public void showFeedbackChart() {
        this.setFeedbackChart();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void callFeedback() {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ShowFeedback.fxml"));
        try {
            root = (Parent) fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FormSumActivityController controller = fxmlLoader.<FormSumActivityController>getController();
        controller.setFeedbackChart();
        Scene scene = new Scene(root);
        try {
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.show();
    }

}
