package publicizehub.club.controller;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;

import java.util.logging.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.EventModel;
import publicizehub.club.model.FeedbackModel;
import publicizehub.club.model.PersonModel;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author budsagorn_ss
 */
public class FormSumActivityController  implements Initializable  {
    private static final Logger LOGGER = Logger.getLogger( FormSumActivityController.class.getName() );

    private ConnectionBuilder cb = new ConnectionBuilder();
    private FeedbackModel fbm = new FeedbackModel();
    private PersonModel person = new PersonModel();
    
    private ObservableList<PersonModel> Persons = FXCollections.observableArrayList();
    
    private int eventId;
    
    private Stage thisStage;

    @FXML
    private BarChart<?, ?> feedbackChart;

    @FXML
    private Label numberBuy;

    @FXML
    private Label numberJoin;

    @FXML
    private Label evName;

    @FXML
    private JFXTreeTableView<PersonModel> tableStd;

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

    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
        thisStage.setTitle("PublicizeHUB");   
    }
    
    public void calculateFeedback(int evId) {
        int[] averQ = new int[10];
        ResultSet result;
        result = fbm.getSumQ(evId);
        
        int numPeople;
        numPeople = fbm.numPeople(evId);
        
        int setSumQ1;
        int setSumQ2;
        
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
            if(numPeople<=0){
                numPeople=1;
            }
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
        
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ," calculateFeedback Bug !");
        }
    }

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

    public void callFeedback(EventModel event) {
        Stage stage = new Stage(); 
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ShowFeedback.fxml"));
        stage.setTitle("PublicizeHUB");
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE ,"root : callFeedback Bug !");
        }
        FormSumActivityController controller = fxmlLoader.<FormSumActivityController>getController();
        ResultSet rs;
        rs = controller.getFbm().selectValueFeedback(event.getEvId());
        controller.getEvName().setText(event.getEvName());
        controller.getNumberBuy().setText(""+getFbm().getStdBuy(event.getEvId()));
        System.out.println("EVENT ID " + event.getEvId());
        int temp = getFbm().getStdJoin(event.getEvId());
        System.out.println("NUM"+temp);
        controller.getNumberJoin().setText(""+temp);
        controller.setPersons(event.getEvId());
        controller.checkFeedback(rs,event.getEvId());
        Scene scene = new Scene(root);
        try {
            stage.setScene(scene);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE ,"setScene : callFeedback Bug !");
        }
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        JFXTreeTableColumn<PersonModel, String> stdId = new JFXTreeTableColumn<>("รหัสนักศึกษา");
        stdId.setPrefWidth(105); 
        stdId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PersonModel,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PersonModel, String> param) {
                return param.getValue().getValue().getStdId();
            }
        });

        JFXTreeTableColumn<PersonModel, String> name = new JFXTreeTableColumn<>("ชื่อ");
        name.setPrefWidth(75);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PersonModel,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PersonModel, String> param) {
                return param.getValue().getValue().getStdName();
            }
        });

        JFXTreeTableColumn<PersonModel, String> surName = new JFXTreeTableColumn<>("นามสกุล");
        surName.setPrefWidth(110);
        surName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PersonModel,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PersonModel, String> param) {
                return param.getValue().getValue().getStdSurname();
            }
        });

        JFXTreeTableColumn<PersonModel, String> department = new JFXTreeTableColumn<>("คณะ");
        department.setPrefWidth(160); 
        department.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PersonModel,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PersonModel, String> param) {
                return param.getValue().getValue().getDepartment();
            }
        });
        

        JFXTreeTableColumn<PersonModel, String> joinEvent = new JFXTreeTableColumn<>("วันที่จอง");
        joinEvent.setPrefWidth(90);
        joinEvent.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PersonModel,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PersonModel, String> param) {
                return param.getValue().getValue().getDateBuyTicket();
            }
        });
        JFXTreeTableColumn<PersonModel, String> checkIn = new JFXTreeTableColumn<>("เช็คอิน");
        checkIn.setPrefWidth(75);
        checkIn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PersonModel,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PersonModel, String> param) {
                return param.getValue().getValue().getStatusCheckIn();
            }
        });
        JFXTreeTableColumn<PersonModel, String> evaluation = new JFXTreeTableColumn<>("ประเมิน");
        evaluation.setPrefWidth(81);
        evaluation.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PersonModel,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PersonModel, String> param) {
                return param.getValue().getValue().getStatusEvaluation();
            }
        });
        
        final TreeItem<PersonModel> root = new RecursiveTreeItem<PersonModel>(Persons, RecursiveTreeObject::getChildren);
        tableStd.getColumns().setAll(stdId,name,surName,department,joinEvent,checkIn,evaluation);
        tableStd.setRoot(root);
        tableStd.setShowRoot(false);
    }
    
    public void setPersons(int eventId){
        ResultSet rs = fbm.getStdFormLog(eventId);
        ResultSet selectName;
        try{
            while(rs.next()){
                long stdId = rs.getLong("stdId");
                selectName = person.getProfile(stdId);
                if(selectName.next()){
                    String tempName = selectName.getString("std_name");
                    String name = tempName.substring(0,tempName.indexOf(" "));
                    String surname = tempName.substring(tempName.indexOf(" ")+1,tempName.length());
                    
                    Persons.add(new PersonModel((stdId+""),name,surname
                            ,selectName.getString("std_major"),rs.getInt("status"),rs.getInt("statusCheckIn")
                            ,rs.getDate("dateBuyTicket"),rs.getTime("timestamp")));
                }
            }
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"setPersons : setPersons Failed");
        }
        cb.logout();
    }
    
    public void checkFeedback(ResultSet rs,int evId){
        try{
            if(!(rs.next())){
                calculateFeedback(evId);
            }else{
                int[] averQ = new int[10];
                averQ[0] += rs.getInt("sumQ1");
                averQ[1] += rs.getInt("sumQ2");
                averQ[2] += rs.getInt("sumQ3");
                averQ[3] += rs.getInt("sumQ4");
                averQ[4] += rs.getInt("sumQ5");
                averQ[5] += rs.getInt("sumQ6");
                averQ[6] += rs.getInt("sumQ7");
                averQ[7] += rs.getInt("sumQ8");
                averQ[8] += rs.getInt("sumQ9");
                averQ[9] += rs.getInt("sumQ10");
                setFeedbackChart(averQ);
            }
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"call Controller : callFeedback Bug !");
        }
    }
}
