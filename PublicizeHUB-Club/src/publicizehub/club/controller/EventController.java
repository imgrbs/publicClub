package publicizehub.club.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.Event;

/**
 *
 * @author ImagineRabbits
 */
public class EventController {
    JoinController jc = new JoinController();
    DetailController dc = new DetailController();

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
    
    public void addEventToPresentPane(Label labelEvName,VBox listEventBox) {
        String eventName="";
        int eventId=0;
        Pane p = new Pane();
        labelEvName = new Label(eventName);
        Button joinbtn = new Button("Join");
        Button detailbtn = new Button("Detail");
        joinbtn.getStyleClass().add("joinbtnSearch");
        detailbtn.getStyleClass().add("detailbtnSearch");
        joinbtn.setLayoutX(285);
        joinbtn.setLayoutY(100);
        detailbtn.setLayoutX(370);
        detailbtn.setLayoutY(100);
        p.getChildren().add(labelEvName);
        p.getChildren().add(joinbtn);
        p.getChildren().add(detailbtn);
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
}
