package publicizehub.club.controller;

import com.jfoenix.controls.JFXTreeTableView;
import java.net.URL;
import java.sql.ResultSet;
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
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.Event;
import publicizehub.club.model.FeedbackModel;

/**
 * FXML Controller class
 *
 * @author budsagorn_ss
 */
public class FormSumActivityController implements Initializable {
    private ConnectionBuilder cb = new ConnectionBuilder();
    private FeedbackModel fbm = new FeedbackModel();
    
    private int eventId;

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

    public FeedbackModel getFbm() {
        return fbm;
    }

    public void setFbm(FeedbackModel fbm) {
        this.fbm = fbm;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public FormSumActivityController() {
        
    }

    public Label getNumberBuy() {
        return numberBuy;
    }

    public void setNumberBuy(Label numberBuy) {
        this.numberBuy = numberBuy;
    }

    public Label getNumberJoin() {
        return numberJoin;
    }

    public void setNumberJoin(Label numberJoin) {
        this.numberJoin = numberJoin;
    }

    public Label getEvName() {
        return evName;
    }

    public void setEvName(Label evName) {
        this.evName = evName;
    }

    public TreeTableColumn<?, ?> getStdName() {
        return stdName;
    }

    public void setStdName(TreeTableColumn<?, ?> stdName) {
        this.stdName = stdName;
    }

    public TreeTableColumn<?, ?> getStdSurname() {
        return stdSurname;
    }

    public void setStdSurname(TreeTableColumn<?, ?> stdSurname) {
        this.stdSurname = stdSurname;
    }

    public TreeTableColumn<?, ?> getStdDepart() {
        return stdDepart;
    }

    public void setStdDepart(TreeTableColumn<?, ?> stdDepart) {
        this.stdDepart = stdDepart;
    }

    public TreeTableColumn<?, ?> getStdBuy() {
        return stdBuy;
    }

    public void setStdBuy(TreeTableColumn<?, ?> stdBuy) {
        this.stdBuy = stdBuy;
    }

    public TreeTableColumn<?, ?> getStdCheckin() {
        return stdCheckin;
    }

    public void setStdCheckin(TreeTableColumn<?, ?> stdCheckin) {
        this.stdCheckin = stdCheckin;
    }

    public void calculateFeedback(int evId) {
        
        int[] averQ = new int[10];
        ResultSet result = null;
        result = fbm.getSumQ(evId);
        
        int numPeople = 1;
        numPeople = fbm.numPeople(evId);
        
        int setSumQ1 = 0;
        int setSumQ2 = 0;
        
        try {
            while (result.next()) {
                averQ[0] += result.getInt("sumQ1");
                averQ[1] += result.getInt("sumQ2");
                averQ[2] += result.getInt("sumQ3");
                averQ[3] += result.getInt("sumQ4");
                averQ[4] += result.getInt("sumQ5");
                averQ[5] += result.getInt("sumQ6");
                averQ[6] += result.getInt("sumQ7");
                averQ[7] += result.getInt("sumQ8");
                averQ[8] += result.getInt("sumQ9");
                averQ[9] += result.getInt("sumQ10");
            }
            cb.logout();

            averQ[0] /= numPeople;
            averQ[1] /= numPeople;
            averQ[2] /= numPeople;
            averQ[3] /= numPeople;
            averQ[4] /= numPeople;
            averQ[5] /= numPeople;
            averQ[6] /= numPeople;
            averQ[7] /= numPeople;
            averQ[8] /= numPeople;
            averQ[9] /= numPeople;

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
            
            fbm.insertAvgrValue(evId, numPeople, averQ[0], averQ[1], averQ[2], averQ[3], averQ[4], averQ[5],
                            averQ[6], averQ[7], averQ[8], averQ[9], setSumQ1, setSumQ2);
            setFeedbackChart(averQ);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //set ค่าที่จะโชว์กราฟ
    @FXML
    public void setFeedbackChart(int averQ[]) {
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void callFeedback(Event event) {
//        this.eventId=evId;
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ShowFeedback.fxml"));
        try {
            root = (Parent) fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FormSumActivityController controller = fxmlLoader.<FormSumActivityController>getController();
        ResultSet rs = null;
        rs = controller.getFbm().selectValueFeedback(getEventId());
        controller.getEvName().setText(event.getEvName());
        controller.getNumberBuy().setText(""+getFbm().getStdBuy(event.getEvId()));
        controller.getNumberJoin().setText(""+getFbm().getStdJoin(event.getEvId()));
        try{
            if(!rs.next()){
                controller.calculateFeedback(event.getEvId());
                rs = controller.getFbm().selectValueFeedback(event.getEvId());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        try {
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.show();
    }

}
