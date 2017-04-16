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
import javafx.scene.control.Alert;
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
    FeedbackModel fbm = new FeedbackModel();

    RadioButton[] Q1_N1 = new RadioButton[5];
    RadioButton[] Q1_N2 = new RadioButton[5];
    RadioButton[] Q1_N3 = new RadioButton[5];
    RadioButton[] Q1_N4 = new RadioButton[5];
    RadioButton[] Q1_N5 = new RadioButton[5];
    RadioButton[] Q2_N6 = new RadioButton[5];
    RadioButton[] Q2_N7 = new RadioButton[5];
    RadioButton[] Q2_N8 = new RadioButton[5];
    RadioButton[] Q2_N9 = new RadioButton[5];
    RadioButton[] Q2_N10 = new RadioButton[5];
    
    int[] valueRadio = new int[10];
    @FXML
    ToggleGroup[] groupQ = new ToggleGroup[10];    
    

    @FXML
    private Label evName;

    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;

   

    
    @FXML
    private RadioButton Q1_N1_5;
    
    @FXML
    private RadioButton Q1_N2_5;

    @FXML
    private RadioButton Q1_N3_5;

    @FXML
    private RadioButton Q1_N4_5;

    @FXML
    private RadioButton Q1_N5_5;

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
    private RadioButton Q2_N2_5;

    @FXML
    private RadioButton Q2_N3_5;

    @FXML
    private RadioButton Q2_N4_5;


    @FXML
    private RadioButton Q2_N5_5;
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

    public void setValueRadio(RadioButton[] radio, int valueRadio) {
        if (radio[0].isSelected()) {
            valueRadio += 100;
        } else if (radio[1].isSelected()) {
            valueRadio += 80;
        } else if (radio[2].isSelected()) {
            valueRadio += 60;
        } else if (radio[3].isSelected()) {
            valueRadio += 40;
        } else if (radio[4].isSelected()) {
            valueRadio += 20;
        } else {
            valueRadio += 0;
        }

    }

    @FXML
    public void setValueRadio() {

        try {
            setValueRadio(Q1_N1, valueRadio[0]);
            setValueRadio(Q1_N2, valueRadio[1]);
            setValueRadio(Q1_N3, valueRadio[2]);
            setValueRadio(Q1_N4, valueRadio[3]);
            setValueRadio(Q1_N5, valueRadio[4]);
            setValueRadio(Q2_N6, valueRadio[5]);
            setValueRadio(Q2_N7, valueRadio[6]);
            setValueRadio(Q2_N8, valueRadio[7]);
            setValueRadio(Q2_N9, valueRadio[8]);
            setValueRadio(Q2_N10, valueRadio[9]);

        } catch (Exception e) {
            e.printStackTrace();
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Error !");
            warning.setHeaderText("กรุณาให้คะแนนให้ครบทุกข้อ");
            warning.setContentText("ขออภัย, คุณให้คะแนนไม่ครบทุกข้อ");
            warning.showAndWait();
        }
    }

    @FXML
    public void clickConfirm() {
        setValueRadio();
    }

    public void sentValue() {
        fbm.insertValue(evId, stdId, valueRadio[0], valueRadio[1], valueRadio[2], valueRadio[3], valueRadio[4],
                valueRadio[5], valueRadio[6], valueRadio[7], valueRadio[8], valueRadio[9]);
        fbm.setSumQ();
    }

    public void callEvaluation(int eventId, String evName, long stdId) {
        this.evId = eventId;
        this.stdId = stdId;
//        ResultSet rs = ev.getSelect(eventId);
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/FormEvaluations.fxml"));
        try {
            root = (Parent) fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Form_EvaluationsController controller = fxmlLoader.<Form_EvaluationsController>getController();

        Scene scene = new Scene(root);
        try {
            stage.setScene(scene);
            controller.evName.setText(evName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        controller.confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("CLICK!");
                clickConfirm();
                if (valueRadio[0] != -1 && valueRadio[1] != -1
                        || valueRadio[2] != -1 && valueRadio[3] != -1
                        || valueRadio[4] != -1 && valueRadio[5] != -1
                        || valueRadio[6] != -1 && valueRadio[7] != -1
                        || valueRadio[8] != -1 && valueRadio[9] != -1) {
                    sentValue();
                    fbm.insertToLog(eventId, stdId);
                    Alert warning = new Alert(Alert.AlertType.INFORMATION);
                    warning.setTitle("Success !");
                    warning.setHeaderText("ประเมิณสำเร็จ");
                    warning.setContentText("ขอบคุณครับ");
                    warning.showAndWait();
                }
            }
        });
        controller.cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stageClose(stage);
            }
        });

        stage.show();
        cb.logout();
    }

    public void stageClose(Stage stage) {
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
