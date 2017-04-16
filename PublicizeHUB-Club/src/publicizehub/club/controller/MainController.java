package publicizehub.club.controller;

import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import publicizehub.club.view.*;

/**
 *
 * @author ImagineRabbits
 */
public class MainController {
    private LoginController li = new LoginController();

    public LoginController getLi() {
        return li;
    }
    
    SearchController sc = new SearchController();
    JoinController jc = new JoinController();
    DetailController dc = new DetailController();
    ResultSet rs = null;

    private Stage thisStage;
    
    @FXML
    private Label stdId;

    @FXML
    private Label stdName;

    @FXML
    private Label labelEvMain1;
    @FXML
    private Label labelEvMain2;
    @FXML
    private Button joinEvMain1;
    @FXML
    private Button joinEvMain2;
    @FXML
    private Button detailEv1;
    @FXML
    private Button detailEv2;
    @FXML
    private Button manageBtn;
    @FXML
    private ImageView managePic;
    @FXML
    TextField searchfield;

    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }
    
    public void setManageDisable(){
        this.manageBtn.setVisible(false);
        this.managePic.setVisible(false);
    }
    
    public void setUserData(long stdId,String stdName){
        this.stdId.setText(""+stdId);
        this.stdName.setText(""+stdName);
    }
    
    @FXML
    protected void callProfile() {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Profile.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        ProfileController controller = fxmlLoader.<ProfileController>getController();
        controller.setStdId(getLi().getStdId());
        controller.setLabelDepartment(li.getDepartment());
        controller.setLabelId(""+li.getStdId());
        controller.setLabelName(li.getName()+" "+li.getSurname());
        controller.getEventToProfile();
        controller.setMainStage(thisStage);
        controller.setThisStage(stage);
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        stage.show();
        thisStage.close();
    }

    @FXML
    protected void callSearchText() {
        System.out.println(searchfield.getText());
    }

    @FXML
    protected void callSearchEvent() {
        System.out.println("callCampEvent");
        sc.setCheckEvType(0);
        try {
            sc.checkSearchEvType();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception callCampEvent");
        }
    }


    @FXML
    public void getEvent() {
        int eventId1,eventId2;
        if (rs == null) {
            System.out.println("LOAD EVENT ONSTART");
            rs = sc.getEventToGui("IT 3K ครั้งที่ 13");
            try {
                if (rs.next()) {
                    labelEvMain1.setText(rs.getString("evName"));
                    eventId1 = rs.getInt("evId");
                    joinEvMain1.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            jc.toJoinEvent(eventId1);
                        }
                    });
                    detailEv1.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            dc.callDetail(eventId1);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = sc.getEventToGui("Brown Bag #2.0");
            try {
                if (rs.next()) {
                    labelEvMain2.setText(rs.getString("evName"));
                    eventId2 = rs.getInt("evId");
                    joinEvMain2.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            jc.toJoinEvent(eventId2);
                        }
                    });
                    detailEv2.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            dc.callDetail(eventId2);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
