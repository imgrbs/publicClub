package publicizehub.club.controller;

/**
 *
 * @author ImagineRabbits
 */
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.Event;
import publicizehub.club.model.FeedbackModel;

public class Form_EvaluationsController implements Initializable {

    ConnectionBuilder cb = new ConnectionBuilder();
    Event ev = new Event();
    
    private int evId = 10048;
    private long stdId = 59130500012L;
    int valueRadio1 = 0;
    int valueRadio2 = 0;
    int valueRadio3 = 0;
    int valueRadio4 = 0;
    int valueRadio5 = 0;
    int valueRadio6 = 0;
    int valueRadio7 = 0;
    int valueRadio8 = 0;
    int valueRadio9 = 0;
    int valueRadio10 = 0;
    FeedbackModel fbm = new FeedbackModel();

    @FXML
    private Label evName;

    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;

    @FXML
    private RadioButton Q1_N1_5;

    @FXML
    private ToggleGroup groupQ1;

    @FXML
    private RadioButton Q1_N2_5;

    @FXML
    private ToggleGroup groupQ2;

    @FXML
    private RadioButton Q1_N3_5;

    @FXML
    private ToggleGroup groupQ3;

    @FXML
    private RadioButton Q1_N4_5;

    @FXML
    private ToggleGroup groupQ4;

    @FXML
    private RadioButton Q1_N5_5;

    @FXML
    private ToggleGroup groupQ5;

    @FXML
    private RadioButton Q1_N1_4;

    @FXML
    private RadioButton Q1_N2_4;

    @FXML
    private RadioButton Q1_N3_4;

    @FXML
    private RadioButton Q1_N4_4;

    @FXML
    private RadioButton Q1_N5_4;

    @FXML
    private RadioButton Q1_N1_3;

    @FXML
    private RadioButton Q1_N2_3;

    @FXML
    private RadioButton Q1_N3_3;

    @FXML
    private RadioButton Q1_N4_3;

    @FXML
    private RadioButton Q1_N5_3;

    @FXML
    private RadioButton Q1_N1_2;

    @FXML
    private RadioButton Q1_N2_2;

    @FXML
    private RadioButton Q1_N3_2;

    @FXML
    private RadioButton Q1_N4_2;

    @FXML
    private RadioButton Q1_N5_2;

    @FXML
    private RadioButton Q1_N1_1;

    @FXML
    private RadioButton Q1_N2_1;

    @FXML
    private RadioButton Q1_N3_1;

    @FXML
    private RadioButton Q1_N4_1;

    @FXML
    private RadioButton Q1_N5_1;

    @FXML
    private RadioButton Q2_N1_5;

    @FXML
    private ToggleGroup groupQ6;

    @FXML
    private RadioButton Q2_N2_5;

    @FXML
    private ToggleGroup groupQ7;

    @FXML
    private RadioButton Q2_N3_5;

    @FXML
    private ToggleGroup groupQ8;

    @FXML
    private RadioButton Q2_N4_5;

    @FXML
    private ToggleGroup groupQ9;

    @FXML
    private RadioButton Q2_N5_5;

    @FXML
    private ToggleGroup groupQ10;

    @FXML
    private RadioButton Q2_N1_4;

    @FXML
    private RadioButton Q2_N2_4;

    @FXML
    private RadioButton Q2_N3_4;

    @FXML
    private RadioButton Q2_N4_4;

    @FXML
    private RadioButton Q2_N5_4;

    @FXML
    private RadioButton Q2_N1_3;

    @FXML
    private RadioButton Q2_N2_3;

    @FXML
    private RadioButton Q2_N3_3;

    @FXML
    private RadioButton Q2_N4_3;

    @FXML
    private RadioButton Q2_N5_3;

    @FXML
    private RadioButton Q2_N1_2;

    @FXML
    private RadioButton Q2_N2_2;

    @FXML
    private RadioButton Q2_N3_2;

    @FXML
    private RadioButton Q2_N4_2;

    @FXML
    private RadioButton Q2_N5_2;

    @FXML
    private RadioButton Q2_N1_1;

    @FXML
    private RadioButton Q2_N2_1;

    @FXML
    private RadioButton Q2_N3_1;

    @FXML
    private RadioButton Q2_N4_1;

    @FXML
    private RadioButton Q2_N5_1;


    public Label getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName.setText(evName);
    }

    @FXML
    public void setValueRadio() {

//Q1
        //Num1
        if (Q1_N1_5.isSelected()) {
            valueRadio1 += 100;
        } else if (Q1_N1_4.isSelected()) {
            valueRadio1 += 80;
        } else if (Q1_N1_3.isSelected()) {
            valueRadio1 += 60;
        } else if (Q1_N1_2.isSelected()) {
            valueRadio1 += 40;
        } else if (Q1_N1_1.isSelected()) {
            valueRadio1 += 20;
        } else {
            valueRadio1 += 0;
        }

        //Num2
        if (Q1_N2_5.isSelected()) {
            valueRadio2 += 100;
        } else if (Q1_N2_4.isSelected()) {
            valueRadio2 += 80;
        } else if (Q1_N2_3.isSelected()) {
            valueRadio2 += 60;
        } else if (Q1_N2_2.isSelected()) {
            valueRadio2 += 40;
        } else if (Q1_N2_1.isSelected()) {
            valueRadio2 += 20;
        } else {
            valueRadio2 += 0;
        }

        //Num3
        if (Q1_N3_5.isSelected()) {
            valueRadio3 += 100;
        } else if (Q1_N3_4.isSelected()) {
            valueRadio3 += 80;
        } else if (Q1_N3_3.isSelected()) {
            valueRadio3 += 60;
        } else if (Q1_N3_2.isSelected()) {
            valueRadio3 += 40;
        } else if (Q1_N3_1.isSelected()) {
            valueRadio3 += 20;
        } else {
            valueRadio3 += 0;
        }

        //Num4
        if (Q1_N4_5.isSelected()) {
            valueRadio4 += 100;
        } else if (Q1_N4_4.isSelected()) {
            valueRadio4 += 80;
        } else if (Q1_N4_3.isSelected()) {
            valueRadio4 += 60;
        } else if (Q1_N4_2.isSelected()) {
            valueRadio4 += 40;
        } else if (Q1_N4_1.isSelected()) {
            valueRadio4 += 20;
        } else {
            valueRadio4 += 0;
        }

        //Num5
        if (Q1_N5_5.isSelected()) {
            valueRadio5 += 100;
        } else if (Q1_N5_4.isSelected()) {
            valueRadio5 += 80;
        } else if (Q1_N5_3.isSelected()) {
            valueRadio5 += 60;
        } else if (Q1_N5_2.isSelected()) {
            valueRadio5 += 40;
        } else if (Q1_N5_1.isSelected()) {
            valueRadio5 += 20;
        } else {
            valueRadio5 += 0;
        }

//Q2
        //Num1
        if (Q2_N1_5.isSelected()) {
            valueRadio6 += 100;
        } else if (Q2_N1_4.isSelected()) {
            valueRadio6 += 80;
        } else if (Q2_N1_3.isSelected()) {
            valueRadio6 += 60;
        } else if (Q2_N1_2.isSelected()) {
            valueRadio6 += 40;
        } else if (Q2_N1_1.isSelected()) {
            valueRadio6 += 20;
        } else {
            valueRadio6 += 0;
        }

        //Num2
        if (Q2_N2_5.isSelected()) {
            valueRadio7 += 100;
        } else if (Q2_N2_4.isSelected()) {
            valueRadio7 += 80;
        } else if (Q2_N2_3.isSelected()) {
            valueRadio7 += 60;
        } else if (Q2_N2_2.isSelected()) {
            valueRadio7 += 40;
        } else if (Q2_N2_1.isSelected()) {
            valueRadio7 += 20;
        } else {
            valueRadio7 += 0;
        }

        //Num3
        if (Q2_N3_5.isSelected()) {
            valueRadio8 += 100;
        } else if (Q2_N3_4.isSelected()) {
            valueRadio8 += 80;
        } else if (Q2_N3_3.isSelected()) {
            valueRadio8 += 60;
        } else if (Q2_N3_2.isSelected()) {
            valueRadio8 += 40;
        } else if (Q2_N3_1.isSelected()) {
            valueRadio8 += 20;
        } else {
            valueRadio8 += 0;
        }

        //Num4
        if (Q2_N4_5.isSelected()) {
            valueRadio9 += 100;
        } else if (Q2_N4_4.isSelected()) {
            valueRadio9 += 80;
        } else if (Q2_N4_3.isSelected()) {
            valueRadio9 += 60;
        } else if (Q2_N4_2.isSelected()) {
            valueRadio9 += 40;
        } else if (Q2_N4_1.isSelected()) {
            valueRadio9 += 20;
        } else {
            valueRadio9 += 0;
        }

        //Num5
        if (Q2_N5_5.isSelected()) {
            valueRadio10 += 100;
        } else if (Q2_N5_4.isSelected()) {
            valueRadio10 += 80;
        } else if (Q2_N5_3.isSelected()) {
            valueRadio10 += 60;
        } else if (Q2_N5_2.isSelected()) {
            valueRadio10 += 40;
        } else if (Q2_N5_1.isSelected()) {
            valueRadio10 += 20;
        } else {
            valueRadio10 += 0;
        }
    }

    @FXML
    public void clickConfirm() {
        setValueRadio();
        confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fbm.insertValue(evId, stdId, valueRadio1, valueRadio2, valueRadio3, valueRadio4, valueRadio5,
                valueRadio6, valueRadio7, valueRadio8, valueRadio9, valueRadio10);
                fbm.setSumQ();
            }
        });
        
    }
                
    public void callEvaluation(int eventId){
        ResultSet rs = ev.getSelect(eventId);
        Stage stage= new Stage();
        Parent root=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/FormEvaluations.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Form_EvaluationsController controller = fxmlLoader.<Form_EvaluationsController>getController();
        
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        controller.cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stageClose(stage);
            }
        });
        stage.show();
        cb.logout();
    }

    public void stageClose(Stage stage){
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
