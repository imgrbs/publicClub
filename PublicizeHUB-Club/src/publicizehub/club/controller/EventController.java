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
import publicizehub.club.model.Event;
import publicizehub.club.model.FeedbackModel;

/**
 *
 * @author ImagineRabbits
 */
public class EventController {
    JoinController jc = new JoinController();
    DetailController dc = new DetailController();
    FormEvaluationsController fe = new FormEvaluationsController();
    FeedbackModel fbm = new FeedbackModel();

    private int evId;
    private int evType;
    private long stdId;

    ConnectionBuilder cb = new ConnectionBuilder();
    Event ev = new Event();


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
    
    /* Method สร้าง Component ใน Profile GUI */
    public void addEventToPresentPane(String evName,int eventId,VBox listEventBox,boolean evaluation) {
        /* รับ Event Name String มาเพื่อจะ Set Label เป็นชื่อ */
        Pane p = new Pane(); // สร้างกล่องสำหรับ 1 Component
        Label labelEvName = new Label(evName); // สร้าง Label ชื่อ Event
        /*เช็คว่า เป็น กิจกรรมที่จบแล้วหรือไม่ ถ้าเป็น true คือยังไม่จบ จะให้สร้าง
        ปุ่ม Check Code กับ Detail ถ้าจบแล้วจะให้สร้างปุ่มประเมิณกิจกรรม */
        if(evaluation){
            Button joinbtn = new Button("Check Code"); // สร้างปุ่มสำหรับ Check Code
            Button detailbtn = new Button("Detail"); // สร้างปุ่ม Detail กิจกรรม
            /* เพิ่ม CSS จากไฟล์ style.css */
            joinbtn.getStyleClass().add("joinBtnProfile"); // css ตกแต่งปุ่ม
            joinbtn.getStyleClass().add("quark"); // css font quark
            detailbtn.getStyleClass().add("detailbtnSearch"); // css ตกแต่งปุ่ม
            detailbtn.getStyleClass().add("quark"); // css font quark
            /* ตั้งตำแหน่งของปุ่มใน Box Component */
            joinbtn.setLayoutX(225);
            joinbtn.setLayoutY(100);
            detailbtn.setLayoutX(370);
            detailbtn.setLayoutY(100);
            /* Bind Event ให้กดปุ่ม Check Code แล้วจะเรียกใช้ Method 
            ของ JoinController ทำให้เรียกดูโค้ด Join Event ได้ */
            joinbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    jc.toJoinEvent(eventId); 
                    // method JoinEvent ของ JoinEvent Controller
                }
            });
            /* Bind Event ให้ปุ่ม Detail สำหรับดูรายละเอียดกิจกรรม */
            detailbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dc.callDetail(eventId);
                    // method CallDetail ของ Detail Controller
                }
            });
            /* สั่งเพิ่ม ปุ่มทั้ง2ปุ่ม เข้ากับ Box Component */
            p.getChildren().add(joinbtn);
            p.getChildren().add(detailbtn);
        }else{
            /* Method คล้าย ใน if แต่ สร้างปุ่ม ประเมิณกิจกรรม และเรียกคนละ
            Method โดยปุ่มนี้จะเรียก Method ประเมิณกิจกรรมนั้นๆ */
            Button evaluationbtn = new Button("ประเมิณกิจกรรม");
            /* เพิ่ม CSS จากไฟล์ style.css */
            evaluationbtn.getStyleClass().add("evaluationbtn"); // css ตกแต่งปุ่ม
            evaluationbtn.getStyleClass().add("quark"); // css font quark
            /* ปรับตำแหน่ง */
            evaluationbtn.setLayoutX(280);
            evaluationbtn.setLayoutY(90);
            /* Bind Event ให้ปุ่ม ประเมิณกิจกรรม */
            evaluationbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    /* เช็คใน Log ก่อนว่า เคยประเมิณรึยัง
                    ถ้าประเมิณแล้วจะเรียก Alert แจ้ง และไม่ให้ประเมิณอีก 
                    ถ้ายังไม่ประเมิณจะเรียก GUI สำหรับประเมิณมาแสดง */
                        ResultSet  log = fbm.getFormLog(eventId, getStdId());
                        try{
                            if(log.next())blockFeedback(); // เรียก Alert
                            else fe.callEvaluation(eventId,evName,getStdId());
                            // เรียก Method ประเมิณ
                        }catch(SQLException e){
                            e.printStackTrace();
                        }
                    }
            });
            cb.logout();
            p.getChildren().add(evaluationbtn);
        }
        p.getChildren().add(labelEvName); // เพิ่ม Label ลง Component

        /* ตกแต่ง Box สำหรับใส่ Component โดยให้เว้นระยะตามกำหนด */
        listEventBox.setMargin(p,new Insets(15,25,15,30));
        p.setStyle("-fx-background-color: #" + "ffffff" + ";" +
                   "-fx-background-radius: 10px;" +
                   "-fx-effect: dropshadow(three-pass-box, #4d4d4d, 5, 0, 0, 1);");
        /* เขียน CSS inline Style ใน Java */
        labelEvName.setStyle("-fx-padding: 30px 0px 0px 50px;"+
                   "-fx-font-size: 30px;"+
                   "-fx-text-fill: #000000;");
        p.setPrefSize(480,150); // ตั้งค่าขนาดของ Component
        listEventBox.getChildren().add(p); // เพิ่ม Componnet ให้ Box
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
