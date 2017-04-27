package publicizehub.club.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.EventModel;
import publicizehub.club.model.LoginModel;

/**
 * FXML Controller class
 *
 * @author JIL
 */
public class ProfileController {
    
    private static final Logger LOGGER = Logger.getLogger( FormSumActivityController.class.getName() );
    private EventModel ev = new EventModel();
    private LoginModel profile;
    
    ConnectionBuilder cb = new ConnectionBuilder();
    
    JoinController jc = new JoinController();
    DetailController dc = new DetailController();
    EventController ec = new EventController();
    
    private long stdId;
    
    private Stage mainStage;
    private Scene mainScene;
    private Parent tempRoot;
    
    
    @FXML
    private Label labelId;
    @FXML
    private Label labelName;
    @FXML
    private Label labelDepartment;
    @FXML
    private Label labelEvName;
    
    @FXML
    private VBox listEventBox1 = new VBox();
    @FXML
    private VBox listEventBox2 = new VBox();
    
    @FXML
    private Button backBtn;

    public Parent getTempRoot() {
        return tempRoot;
    }

    public void setTempRoot(Parent tempRoot) {
        this.tempRoot = tempRoot;
    }
    
    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
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

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }

    public LoginModel getProfile() {
        return profile;
    }

    public void setProfile(LoginModel profile) {
        this.profile = profile;
    }
    
    
    
    @FXML
    public void getEventToProfile(){
        ResultSet rs = ev.getSelect(getProfile().getStdId());
        cb.logout();
        
        System.out.println(getProfile().getStdId() + " 125 Profilecontroller");
        try{
            if(rs.next()){
                setEventToGui(rs.getInt("evId"));
                while(rs.next()){
                    setEventToGui(rs.getInt("evId"));
                }
            }
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"SQLException : getEventToProfile Bug !");
        }
        cb.logout();
    }

    public void setEventToGui(int eventId){
        ResultSet findStd = ev.getSelect(eventId);
        EventModel event = null;
        LocalDate ld = null;
        try{
            if(findStd.next()){
                event = new EventModel(findStd.getString("evName"),
                findStd.getString("evDescrip"),findStd.getDate("evStartDate"),
                findStd.getDate("evEndDate"),findStd.getDate("evStartRegis"),
                findStd.getDate("evEndFeedback"),findStd.getString("evPlace"),
                findStd.getInt("evTicket"),findStd.getInt("currentMember"),
                findStd.getTime("evTime"),findStd.getTime("evEndTime"),
                findStd.getInt("evType"),findStd.getInt("evId")
                );
                ld = event.getEvEndDate();
                
                System.out.println(getProfile().getStdId() + " 155 profilecontroller");
                ec.setStdId(profile.getStdId());
                if(ld.compareTo(LocalDate.now())>-1){ 
                    ec.addEventToPresentPane(event,this.listEventBox1,true,true); 
                }
                else {
                    ec.addEventToPresentPane(event,this.listEventBox2,false,true);
                }
            }
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"SQLException : setEventToGui Bug !");
        }
        
    }
    
    public void callMain(){
        mainScene.setRoot(tempRoot);
    }
    
    @FXML
    public void callProfile(Stage mainStage,Scene mainScene,LoginModel prof){
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("../view/Profile.fxml")); 

        Parent root = null;
        try{
            root = (Parent)loader.load();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        ProfileController controller = loader.<ProfileController>getController();
        /* SET root to scene ! */
        controller.setMainStage(mainStage);
        controller.setMainScene(mainScene);
        controller.setTempRoot(mainScene.getRoot());
        controller.setProfile(prof);
        controller.setStdId(prof.getStdId());
//        controller
        controller.getEventToProfile();
        System.out.println(controller.getProfile().getStdId() + " 193 ProfileController");
        controller.setLabelId(prof.getStdId()+"");
        controller.setLabelName(prof.getName());
        controller.setLabelDepartment(prof.getDepartment());
        
        mainScene.setRoot(root);
    }
    
}
