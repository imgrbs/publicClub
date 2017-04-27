package publicizehub.club.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import publicizehub.club.model.EventModel;
import static java.lang.Long.parseLong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import publicizehub.club.model.LoginModel;

/**
 *
 * @author ImagineRabbits
 */
public class ManageController {
    private NewsController nc = new NewsController();
    private CreateEventController ce = new CreateEventController();
    private LoginController li = new LoginController();
    private EventController ec = new EventController();
    
    private EventModel ev = new EventModel();
    private LoginModel profile;
    
    private Stage mainStage;
    private Scene mainScene;
    private Parent tempRoot;
    
    private long stdId;
    
    @FXML
    private Label labelId;
    @FXML
    private Label labelName;
    @FXML
    private Label labelDepartment;
    @FXML
    private JFXButton addNewsBtn;
    @FXML
    private VBox listEventBox1 = new VBox();
    @FXML
    private VBox listEventBox2 = new VBox();

    public LoginController getLi() {
        return li;
    }

    public void setLi(LoginController li) {
        this.li = li;
    }

    public LoginModel getProfile() {
        return profile;
    }

    public void setProfile(LoginModel profile) {
        this.profile = profile;
    }

    
    
    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }

    public void setLabelId(String labelId) {
        this.labelId.setText(labelId);
    }

    public void setLabelName(String labelName) {
        this.labelName.setText(labelName);
    }

    public void setLabelDepartment(String labelDepartment) {
        this.labelDepartment.setText(labelDepartment);
    }
    
    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public Parent getTempRoot() {
        return tempRoot;
    }

    public void setTempRoot(Parent tempRoot) {
        this.tempRoot = tempRoot;
    }
    
    
    
    
    @FXML
    public void callAddNews(){
        nc.callAddNews();
    }
    
    @FXML
    public void callMain(){
        mainScene.setRoot(tempRoot);
    }
    
    @FXML
    public void callManage(Stage mainStage,Scene tempScene,LoginModel prof){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Manage.fxml"));     
        Parent root = null;
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(IOException e){
            System.out.println("ERROR at callManage");
        }
        ManageController controller = fxmlLoader.<ManageController>getController();
        controller.setProfile(prof);
        controller.setStdId(prof.getStdId());
        controller.setLabelDepartment(prof.getDepartment());
        controller.setLabelId(""+prof.getStdId());
        controller.setLabelName(prof.getName());
        controller.setEventToGui(prof.getStdId());
        controller.setMainStage(mainStage);
        controller.setMainScene(tempScene);
        controller.setTempRoot(tempScene.getRoot());
        tempScene.setRoot(root);
    }
    
    @FXML
    public void getEventToProfile(){
        ResultSet rs = ev.getSelect(parseLong(this.labelId.getText()));
        try{
            if(rs.next()){
                setEventToGui(rs.getInt("evId"));
                while(rs.next()){
                    setEventToGui(rs.getInt("evId"));
                }
            }
        }catch(SQLException e){
            System.out.println("ERROR at getEventToProfile");
        }
    }

    public void setEventToGui(long stdId){
        ResultSet findStd = ev.getEventByStdId(stdId);
        EventModel event;
        try{
            while(findStd.next()){
                event = new EventModel(findStd.getString("evName"),
                findStd.getString("evDescrip"),findStd.getDate("evStartDate"),
                findStd.getDate("evEndDate"),findStd.getDate("evStartRegis"),
                findStd.getDate("evEndFeedback"),findStd.getString("evPlace"),
                findStd.getInt("evTicket"),findStd.getInt("currentMember"),
                findStd.getTime("evTime"),findStd.getTime("evEndTime"),
                findStd.getInt("evType"),findStd.getInt("evId")
                );
                LocalDate ld = findStd.getDate("evEndDate").toLocalDate();
                if(ld.compareTo(LocalDate.now())>-1){ 
                    ec.addEventToPresentPane(getProfile(),event,this.listEventBox1,true,false); 
                }
                else {
                    ec.addEventToPresentPane(getProfile(),event,this.listEventBox2,false,false);
                }
            }
        }catch(SQLException e){
            System.out.println("ERROR at setEventToGui");
        }
    }
    
    @FXML
    public void callCreateEvent(){
        ce.callCreateEvent();
    }
       
}
