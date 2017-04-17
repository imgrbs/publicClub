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
    Form_EvaluationsController fe = new Form_EvaluationsController();

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
    
    public void addEventToPresentPane(String evName,int eventId,VBox listEventBox,boolean evaluation) {
        this.evId = eventId; 
        // this.stdId = 59130500007l
        Pane p = new Pane();
        Label labelEvName = new Label(evName);
        Button joinbtn = new Button("Join");
        Button detailbtn = new Button("Detail");
        Button evaluationbtn = new Button("ประเมิณกิจกรรม");
        if(evaluation){
            joinbtn.getStyleClass().add("joinbtnSearch");
            joinbtn.getStyleClass().add("quark");
            detailbtn.getStyleClass().add("detailbtnSearch");
            detailbtn.getStyleClass().add("quark");
            joinbtn.setLayoutX(285);
            joinbtn.setLayoutY(100);
            detailbtn.setLayoutX(370);
            detailbtn.setLayoutY(100);
            joinbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    jc.toJoinEvent(eventId);
                }
            });
            detailbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dc.callDetail(eventId);
                }
            });
            p.getChildren().add(joinbtn);
            p.getChildren().add(detailbtn);
        }else{
            evaluationbtn.getStyleClass().add("evaluationbtn");
            evaluationbtn.getStyleClass().add("quark");
            evaluationbtn.setLayoutX(280);
            evaluationbtn.setLayoutY(90);
            FeedbackModel fbm = new FeedbackModel();
            evaluationbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                        ResultSet  log = fbm.getFormLog(eventId, getStdId());
                        try{
                            if(log.next())blockFeedback();
                            else fe.callEvaluation(eventId,evName,getStdId());
                        }catch(SQLException e){
                            e.printStackTrace();
                        }
                    }
            });
            cb.logout();
            p.getChildren().add(evaluationbtn);
        }
        p.getChildren().add(labelEvName);

        listEventBox.setMargin(p,new Insets(15,25,15,30));
        p.setStyle("-fx-background-color: #" + "ffffff" + ";" +
                   "-fx-background-radius: 10px;" +
                   "-fx-effect: dropshadow(three-pass-box, #4d4d4d, 5, 0, 0, 1);");
        labelEvName.setStyle("-fx-padding: 30px 0px 0px 50px;"+
                   "-fx-font-size: 30px;"+
                   "-fx-text-fill: #000000;");
        p.setPrefSize(480,150);
        listEventBox.getChildren().add(p);
    }
    
    public void blockFeedback(){
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Error !");
        warning.setHeaderText("คุณเคยประเมิณแล้ว !");
        warning.setContentText("ขออภัย , คุณเคยประเมิณกิจกรรมนี้แล้ว ไม่สามารถประเมิณอีกได้");
        warning.showAndWait();
    }
}
