package publicizehub.club.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import publicizehub.club.model.FeedbackModel;

/**
 * FXML Controller class
 *
 * @author budsagorn_ss
 */
public class FormSumActivityController implements Initializable {

    @FXML
    private Label evName;

    @FXML
    private BarChart<?, ?> feedbackChart;

    @FXML
    private Label numPepleRegis;

    @FXML
    private Label numPepleJoin;

    @FXML
    private TableColumn<?, ?> stdName;

    @FXML
    private TableColumn<?, ?> stdSurname;

    @FXML
    private TableColumn<?, ?> department;

    @FXML
    private TableColumn<?, ?> regisDate;

    @FXML
    private TableColumn<?, ?> joinDate;

//    FeedbackModel fbm = new FeedbackModel();
    int[] averQ = new int[10];
    
    public FormSumActivityController() {
        setValueAver();
    }

    @FXML
       public void setValueAver(){
        for (int i = 0; i < averQ.length; i++) {
            averQ[i] = -1;
        }
    }
    
    
    /*method คำนวณหาค่าเฉลี่ยแต่ละข้อ(10 ข้อ) และเฉลี่ยรวมชุดคำถามที่ 1,2*/
    public void calculateFeedback(int evId, long stdId, int numPeple, int sumQ1,
            int sumQ2, int sumQ3, int sumQ4, int sumQ5, int sumQ6, int sumQ7,
            int sumQ8, int sumQ9, int sumQ10) {

        averQ[0] = sumQ1 / numPeple;
        averQ[1] = sumQ2 / numPeple;
        averQ[2] = sumQ3 / numPeple;
        averQ[3] = sumQ4 / numPeple;
        averQ[4] = sumQ5 / numPeple;
        averQ[5] = sumQ6 / numPeple;
        averQ[6] = sumQ7 / numPeple;
        averQ[7] = sumQ8 / numPeple;
        averQ[8] = sumQ9 / numPeple;
        averQ[9] = sumQ10 / numPeple;

        //คิด % แต่ละข้อโดยแบ่งเป็น 2 ชุด 
        double x = 0.2;
        int percentQ1 = (int) (averQ[0] * x);
        int percentQ2 = (int) (averQ[1] * x);
        int percentQ3 = (int) (averQ[2] * x);
        int percentQ4 = (int) (averQ[3] * x);
        int percentQ5 = (int) (averQ[4] * x);
        
        int percentQ6 = (int) (averQ[5] * x);
        int percentQ7 = (int) (averQ[6] * x);
        int percentQ8 = (int) (averQ[7]* x);
        int percentQ9 = (int) (averQ[8] * x);
        int percentQ10 = (int) (averQ[9] * x);

        int setSumQ1 = (int) (percentQ1 + percentQ2 + percentQ3 + percentQ4 + percentQ5);
        int setSumQ2 = (int) (percentQ6 + percentQ7 + percentQ8 + percentQ9 + percentQ10);

        //ส่งค่ากลับไป model เพื่อเอาค่าไปเก็บไว้ใน DB
//        fbm.insertAvgrValue(evId, numPeple, averQ[0], averQ[1], averQ[2], averQ[3], averQ[4], averQ[5],
//                averQ[6], averQ[7], averQ[8], averQ[9], setSumQ1, setSumQ2);
    }

    //set ค่าที่จะโชว์กราฟ
    @FXML
    public void setFeedbackChart() {
        XYChart.Series setl = new XYChart.Series<>();
        setl.getData().add(new XYChart.Data("Q1", averQ[0]));
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
    public void showFeedbackChart(){
        this.setFeedbackChart();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
