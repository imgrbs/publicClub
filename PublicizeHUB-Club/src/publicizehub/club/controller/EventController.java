package publicizehub.club.controller;


import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.EventModel;
import publicizehub.club.model.FeedbackModel;
import publicizehub.club.view.CheckIn;

/**
 *
 * @author ImagineRabbits
 */
public class EventController {
    private JoinController jc = new JoinController();
    private DetailController dc = new DetailController();
    private FormEvaluationsController fe = new FormEvaluationsController();
    private EditEventController ee = new EditEventController();
    private CheckInController ci = new CheckInController();
    private FormSumActivityController fsa = new FormSumActivityController();
    
    private int evType;
    private long stdId;

    private ConnectionBuilder cb = new ConnectionBuilder();
    private EventModel ev = new EventModel();
    private FeedbackModel fbm = new FeedbackModel();


    public void setEventType(int evType) {
        this.evType = evType;
    }

    public int getEvType() {
        return evType;
    }

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }
    
    
    public void addEventToPresentPane(EventModel event,VBox listEventBox,boolean evaluation,boolean checkTypeGui) {
        String presentText = "ตรวจสอบโค้ด";
        String presentDetail = "รายละเอียด";
        String evaluaText = "ประเมิณกิจกรรม";
        if(!checkTypeGui){
            presentText="แก้ไขกิจกรรม";
            presentDetail = "เช็คอิน";
            evaluaText = "ผลตอบรับ";
        }
        Pane p = new Pane();
        Label labelEvName = new Label(event.getEvName());
        if(evaluation){
            Button joinbtn = new Button(presentText);
            Button detailbtn = new Button(presentDetail);
            joinbtn.getStyleClass().add("joinBtnProfile");
            joinbtn.getStyleClass().add("quark");
            detailbtn.getStyleClass().add("detailbtnProfile");
            detailbtn.getStyleClass().add("quark");
            joinbtn.setLayoutX(225);
            joinbtn.setLayoutY(100);
            detailbtn.setLayoutX(370);
            detailbtn.setLayoutY(100);
            if(presentText.equals("ตรวจสอบโค้ด")){
                joinbtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent evt) {
                        jc.toJoinEvent(event.getEvId()); 
                    }
                });
                detailbtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent evt) {
                        dc.callDetail(event.getEvId());
                    }
                });
            }else{
                joinbtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent evt) {
                        ee.callEditEvent(event); 
                    }
                });
                detailbtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent evt) {
                        ci.callCheckIn(event);
                    }
                });
            }
            
            p.getChildren().add(joinbtn);
            p.getChildren().add(detailbtn);
        }else{
            Button evaluationbtn = new Button(evaluaText);
            evaluationbtn.getStyleClass().add("evaluationbtn");
            evaluationbtn.getStyleClass().add("quark");
            evaluationbtn.setLayoutX(370);
            evaluationbtn.setLayoutY(90);
            if(presentText.equals("ตรวจสอบโค้ด")){
                evaluationbtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent evt) {
                            ResultSet  log = fbm.getFormLog(event.getEvId(), getStdId());
                            try{
                                if(log.next())blockFeedback();
                                else fe.callEvaluation(event,getStdId());
                            }catch(SQLException e){
                                e.printStackTrace();
                            }
                        }
                });
            }else {
                evaluationbtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent evt) {
                        fsa.callFeedback(event);
                    }
                });           
            }
            cb.logout();
            p.getChildren().add(evaluationbtn);
        }
        p.getChildren().add(labelEvName);

        listEventBox.setMargin(p,new Insets(15,25,15,30));
        p.setStyle("-fx-background-color: #" + "ffffff" + ";" +
                   "-fx-background-radius: 10px;" +
                   "-fx-effect: dropshadow(three-pass-box, #4d4d4d, 5, 0, 0, 1);");
        labelEvName.getStyleClass().add("labelNameSearch");
        labelEvName.getStyleClass().add("quark");
        p.setPrefSize(480,150);
        listEventBox.getChildren().add(p);
    }
    
    /* Alert สำหรับกิจกรรมที่ประเมิณแล้ว */
    public void blockFeedback(){
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Error !");
        warning.setHeaderText("คุณเคยประเมิณแล้ว !");
        warning.setContentText("ขออภัย , คุณเคยประเมิณกิจกรรมนี้แล้ว ไม่สามารถประเมิณอีกได้");
        warning.showAndWait();
    }
}
