package publicizehub.club.controller;

/**
 *
 * @author ImagineRabbits
 */
import com.jfoenix.controls.JFXButton;
import static com.sun.media.jfxmediaimpl.MediaUtils.warning;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.EventModel;
import publicizehub.club.model.FeedbackModel;
import publicizehub.club.model.LoginModel;


public class FormEvaluationsController implements Initializable {  // JavaFX บังคับ implement Method ของ JavaFX

    ConnectionBuilder cb = new ConnectionBuilder(); // Model Class สำหรับ Connect กับ DB
    EventModel ev = new EventModel(); // Model Class ของ Event ( ดึงข้อมูล Event จาก DB )

    private int evId = 10048;
    private long stdId = 59130500012L;
    
    private LoginModel profile;
    FeedbackModel fbm = new FeedbackModel(); // Model Class ของ FeedbackModel (ส่งค่าเข้า DB )

    public LoginModel getProfile() {
        return profile;
    }

    public void setProfile(LoginModel profile) {
        this.profile = profile;
    }
    
    /*สร้าง array object RadioButton  เพื่อ set ค่าเวลาคลิกที่ปุ่ม radio button*/
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

    //สร้างตัวแปร valueRadio array ที่มี type int เพื่อไว้เก็บค่าสำหรับค่าคำตอบของคำถาม 10 ข้อ
    int[] valueRadio = new int[10];

    public int getEvId() {
        return evId;
    }

    public void setEvId(int evId) {
        this.evId = evId;
    }

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }
    
     /* ตัวแปรของ JavaFX ที่อิงกับไฟล์ .fxml จะต้องพิมพ์ @FXML กำกับเสมอ */
    @FXML
    private Label evName;

    @FXML
    private JFXButton confirmBtn;
    @FXML
    private JFXButton cancelBtn;

    //ToggleGroup สำหรับจัดกลุ่ม radio
    @FXML
    private ToggleGroup groupQ1;
    
    @FXML
    private ToggleGroup groupQ2;
    
    @FXML
    private ToggleGroup groupQ3;
    
    @FXML
    private ToggleGroup groupQ4;
    
    @FXML
    private ToggleGroup groupQ5;
    
    @FXML
    private ToggleGroup groupQ6;
    
    @FXML
    private ToggleGroup groupQ7;
    
    @FXML
    private ToggleGroup groupQ8;
    
    @FXML
    private ToggleGroup groupQ9;
    
    @FXML
    private ToggleGroup groupQ10;

    //RadioButton 
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

    //method สำหรับ
    public FormEvaluationsController() {
        setRadioFromGui();
    }

    @FXML
    /*method set ค่าเริ่มต้นของ valueRadio โดยจะวนลูป 5 รอบ ค่าเริ่มต้นของ valueRadio[0-5] = -1*/
    public void setRadioFromGui() {
        for (int i = 0; i < valueRadio.length; i++) {
            valueRadio[i] = -1;
        }
    }
    
    /*method set ค่าปุ่มของ RadioButton ของ array ให้เท่ากับค่า fxid ใน javaFx
    ex. Q1_N1[0] เท่ากับ ปุ่ม radio ที่ 1*/
    public void setValueToArr(){
        //num1
        Q1_N1[0] = Q1_N1_1;
        Q1_N1[1] = Q1_N1_2;;
        Q1_N1[2] = Q1_N1_3;
        Q1_N1[3] = Q1_N1_4;
        Q1_N1[4] = Q1_N1_5;
        //num2
        Q1_N2[0] = Q1_N2_1;
        Q1_N2[1] = Q1_N2_2;
        Q1_N2[2] = Q1_N2_3;
        Q1_N2[3] = Q1_N2_4;
        Q1_N2[4] = Q1_N2_5;
        //num3
        Q1_N3[0] = Q1_N3_1;
        Q1_N3[1] = Q1_N3_2;
        Q1_N3[2] = Q1_N3_3;
        Q1_N3[3] = Q1_N3_4;
        Q1_N3[4] = Q1_N3_5;
        //num4
        Q1_N4[0] = Q1_N4_1;
        Q1_N4[1] = Q1_N4_2;
        Q1_N4[2] = Q1_N4_3;
        Q1_N4[3] = Q1_N4_4;
        Q1_N4[4] = Q1_N4_5;
        //num5
        Q1_N5[0] = Q1_N5_1;
        Q1_N5[1] = Q1_N5_2;
        Q1_N5[2] = Q1_N5_3;
        Q1_N5[3] = Q1_N5_4;
        Q1_N5[4] = Q1_N5_5;
        //num6
        Q2_N6[0] = Q2_N1_1;
        Q2_N6[1] = Q2_N1_2;
        Q2_N6[2] = Q2_N1_3;
        Q2_N6[3] = Q2_N1_4;
        Q2_N6[4] = Q2_N1_5;
        //num7
        Q2_N7[0] = Q2_N2_1;
        Q2_N7[1] = Q2_N2_2;
        Q2_N7[2] = Q2_N2_3;
        Q2_N7[3] = Q2_N2_4;
        Q2_N7[4] = Q2_N2_5;
        //num8
        Q2_N8[0] = Q2_N3_1;
        Q2_N8[1] = Q2_N3_2;
        Q2_N8[2] = Q2_N3_3;
        Q2_N8[3] = Q2_N3_4;
        Q2_N8[4] = Q2_N3_5;
        //num9
        Q2_N9[0] = Q2_N4_1;
        Q2_N9[1] = Q2_N4_2;
        Q2_N9[2] = Q2_N4_3;
        Q2_N9[3] = Q2_N4_4;
        Q2_N9[4] = Q2_N4_5;
        //num10
        Q2_N10[0] = Q2_N5_1;
        Q2_N10[1] = Q2_N5_2;
        Q2_N10[2] = Q2_N5_3;
        Q2_N10[3] = Q2_N5_4;
        Q2_N10[4] = Q2_N5_5;
    }

    public Label getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName.setText(evName);
    }

    
    @FXML
    /*method เก็บค่าเมื่อกดปุ่ม radio ตามค่าที่กำหนด 
    ex. ถ้ากดradio[4] ค่าก็จะเท่ากับ 101 */
    public void setValueRadio(RadioButton radio[], int index) {
        if (radio[4].isSelected()) {
            valueRadio[index] += 101;
        } else if (radio[3].isSelected()) {
            valueRadio[index]  += 81;
        } else if (radio[2].isSelected()) {
            valueRadio[index]  += 61;
        } else if (radio[1].isSelected()) {
            valueRadio[index]  += 41;
        } else if (radio[0].isSelected()) {
            valueRadio[index]  += 21;
        }
    }

    
    @FXML
    public void setValueRadio() {
        setValueToArr();
        setValueRadio(Q1_N1, 0);
        setValueRadio(Q1_N2, 1);
        setValueRadio(Q1_N3, 2);
        setValueRadio(Q1_N4, 3);
        setValueRadio(Q1_N5, 4);
        setValueRadio(Q2_N6, 5);
        setValueRadio(Q2_N7, 6);
        setValueRadio(Q2_N8, 7);
        setValueRadio(Q2_N9, 8);
        setValueRadio(Q2_N10, 9);
    }

    @FXML
    public void clickConfirm() {
        this.setValueRadio();
    }

    //method ส่งค่าไปที่ model
    public void sentValue() {
        fbm.insertValue(evId, stdId, valueRadio[0], valueRadio[1], 
                valueRadio[2], valueRadio[3], valueRadio[4],valueRadio[5], 
                valueRadio[6], valueRadio[7], valueRadio[8], valueRadio[9]);
//        fbm.setSumQ();
    }

 
    public void callEvaluation(EventModel event, long stdId) {
//        ResultSet rs = ev.getSelect(eventId);
        System.out.println(stdId);
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/FormEvaluations.fxml"));
        try {
            root = (Parent) fxmlLoader.load();
        /* ดัก SQLException ไว้กันพลาดจะได้รู้ว่าผิดส่วนนี้รึเปล่า */
        } catch (Exception e) {
            e.printStackTrace();
        }
        FormEvaluationsController controller = fxmlLoader.<FormEvaluationsController>getController();
        controller.setEvId(event.getEvId());
        controller.setStdId(stdId);
        Scene scene = new Scene(root);
        try {
            stage.setScene(scene);
            controller.evName.setText(event.getEvName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //set ค่าปุ่มยืนยันให้ทำงาน
        controller.confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                controller.clickConfirm();
                /*เช็คว่าค่าของปุ่มทุกข้อมีค่าตามที่กดจริง หากลืมหรือไม่กดปุ่มที่ข้อไหนค่าก็จะเท่ากับ -1 ตามที่กำหนดไว้ตอนแรก
                แต่หากกดครบทุกปุ่มก็หมายความว่า ประเมินสำเร็จแล้ว"*/
                if (controller.valueRadio[0] != -1 && controller.valueRadio[1] != -1
                        || controller.valueRadio[2] != -1 && controller.valueRadio[3] != -1
                        || controller.valueRadio[4] != -1 && controller.valueRadio[5] != -1
                        || controller.valueRadio[6] != -1 && controller.valueRadio[7] != -1
                        || controller.valueRadio[8] != -1 && controller.valueRadio[9] != -1) {
                    Alert al =  new Alert(Alert.AlertType.CONFIRMATION);
                    al.setHeaderText("ยืนยันการประเมิน");
                    al.setContentText("ยืนยันคะแนนถูกต้องครบถ้วน");

                    Optional<ButtonType> result = al.showAndWait();
                    if(result.isPresent()){
                        if(result.get() == ButtonType.OK){
                            controller.sentValue();
                            System.out.println("SENT COMPLETE");
                            fbm.insertToLog(event.getEvId(), stdId);
                            fbm.updateStatusEva(event.getEvId(), stdId);
                            Alert warning = new Alert(Alert.AlertType.INFORMATION);
                            warning.setTitle("Success !");
                            warning.setHeaderText("ประเมินสำเร็จ");
                            warning.setContentText("ขอบคุณครับ");
                            warning.showAndWait();
                            stage.close();
                        }
                    }
                    
                }else {
                    Alert warning = new Alert(Alert.AlertType.WARNING);
                    warning.setTitle("Error !");
                    warning.setHeaderText("กรุณาให้คะแนนให้ครบทุกข้อ");
                    warning.setContentText("ขออภัย, คุณให้คะแนนไม่ครบทุกข้อ");
                    warning.showAndWait();
                    /*หากกดปุ่มไม่ครบ 10 ข้อ ก็จะมีหน้าต่างแจ้ง Error และบอกให้กรอกคะแนนให้ครบทุกข้อ*/
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

    //คำสั่งปิดหน้านั้น
    public void stageClose(Stage stage) {
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
