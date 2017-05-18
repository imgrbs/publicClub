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
    RadioButton[] rdiAnswerNum1= new RadioButton[5];
    RadioButton[] rdiAnswerNum2= new RadioButton[5];
    RadioButton[] rdiAnswerNum3 = new RadioButton[5];
    RadioButton[] rdiAnswerNum4 = new RadioButton[5];
    RadioButton[] rdiAnswerNum5 = new RadioButton[5];
    RadioButton[] rdiAnswerNum6 = new RadioButton[5];
    RadioButton[] rdiAnswerNum7 = new RadioButton[5];
    RadioButton[] rdiAnswerNum8 = new RadioButton[5];
    RadioButton[] rdiAnswerNum9 = new RadioButton[5];
    RadioButton[] rdiAnswerNum10 = new RadioButton[5];

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
        rdiAnswerNum1[0] = Q1_N1_1;
        rdiAnswerNum1[1] = Q1_N1_2;;
        rdiAnswerNum1[2] = Q1_N1_3;
        rdiAnswerNum1[3] = Q1_N1_4;
        rdiAnswerNum1[4] = Q1_N1_5;
        //num2
        rdiAnswerNum2[0] = Q1_N2_1;
        rdiAnswerNum2[1] = Q1_N2_2;
        rdiAnswerNum2[2] = Q1_N2_3;
        rdiAnswerNum2[3] = Q1_N2_4;
        rdiAnswerNum2[4] = Q1_N2_5;
        //num3
        rdiAnswerNum3[0] = Q1_N3_1;
        rdiAnswerNum3[1] = Q1_N3_2;
        rdiAnswerNum3[2] = Q1_N3_3;
        rdiAnswerNum3[3] = Q1_N3_4;
        rdiAnswerNum3[4] = Q1_N3_5;
        //num4
        rdiAnswerNum4[0] = Q1_N4_1;
        rdiAnswerNum4[1] = Q1_N4_2;
        rdiAnswerNum4[2] = Q1_N4_3;
        rdiAnswerNum4[3] = Q1_N4_4;
        rdiAnswerNum4[4] = Q1_N4_5;
        //num5
        rdiAnswerNum5[0] = Q1_N5_1;
        rdiAnswerNum5[1] = Q1_N5_2;
        rdiAnswerNum5[2] = Q1_N5_3;
        rdiAnswerNum5[3] = Q1_N5_4;
        rdiAnswerNum5[4] = Q1_N5_5;
        //num6
        rdiAnswerNum6[0] = Q2_N1_1;
        rdiAnswerNum6[1] = Q2_N1_2;
        rdiAnswerNum6[2] = Q2_N1_3;
        rdiAnswerNum6[3] = Q2_N1_4;
        rdiAnswerNum6[4] = Q2_N1_5;
        //num7
        rdiAnswerNum7[0] = Q2_N2_1;
        rdiAnswerNum7[1] = Q2_N2_2;
        rdiAnswerNum7[2] = Q2_N2_3;
        rdiAnswerNum7[3] = Q2_N2_4;
        rdiAnswerNum7[4] = Q2_N2_5;
        //num8
        rdiAnswerNum8[0] = Q2_N3_1;
        rdiAnswerNum8[1] = Q2_N3_2;
        rdiAnswerNum8[2] = Q2_N3_3;
        rdiAnswerNum8[3] = Q2_N3_4;
        rdiAnswerNum8[4] = Q2_N3_5;
        //num9
        rdiAnswerNum9[0] = Q2_N4_1;
        rdiAnswerNum9[1] = Q2_N4_2;
        rdiAnswerNum9[2] = Q2_N4_3;
        rdiAnswerNum9[3] = Q2_N4_4;
        rdiAnswerNum9[4] = Q2_N4_5;
        //num10
        rdiAnswerNum10[0] = Q2_N5_1;
        rdiAnswerNum10[1] = Q2_N5_2;
        rdiAnswerNum10[2] = Q2_N5_3;
        rdiAnswerNum10[3] = Q2_N5_4;
        rdiAnswerNum10[4] = Q2_N5_5;
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
        setValueRadio(rdiAnswerNum1, 0);
        setValueRadio(rdiAnswerNum2, 1);
        setValueRadio(rdiAnswerNum3, 2);
        setValueRadio(rdiAnswerNum4, 3);
        setValueRadio(rdiAnswerNum5, 4);
        setValueRadio(rdiAnswerNum6, 5);
        setValueRadio(rdiAnswerNum7, 6);
        setValueRadio(rdiAnswerNum8, 7);
        setValueRadio(rdiAnswerNum9, 8);
        setValueRadio(rdiAnswerNum10, 9);
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
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/FormEvaluations.fxml"));
        stage.setTitle("PublicizeHUB");
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
