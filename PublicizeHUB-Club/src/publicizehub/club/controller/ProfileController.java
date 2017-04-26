package publicizehub.club.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.lang.Long.parseLong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private LoginModel li;
    
    ConnectionBuilder cb = new ConnectionBuilder();
    
    JoinController jc = new JoinController();
    DetailController dc = new DetailController();
    EventController ec = new EventController();
    
    private long stdId;
    
    private Stage mainStage;
    private Stage thisStage;
    private Scene mainScene;
    private Scene thisScene;
    
    
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

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public Scene getThisScene() {
        return thisScene;
    }

    public void setThisScene(Scene thisScene) {
        this.thisScene = thisScene;
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

    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }

    public LoginModel getLogin() {
        return li;
    }

    public void setLogin(LoginModel login) {
        this.li = login;
    }
    
    
    
    @FXML
    public void getEventToProfile(){
        ResultSet rs = ev.getSelect(parseLong(this.labelId.getText()));
        cb.logout();
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
                ec.setStdId(getStdId());
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
    
    @FXML
    public void callMain(){
        mainStage.setScene(thisScene);    
//        mainStage.show();
//        thisStage.close();
    }
    
    @FXML
    public void callProfile(Stage mainStage,Scene tempScene,LoginModel profile){
//        Stage stage = new Stage();
//        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Profile.fxml"));     
        ProfileController controller = fxmlLoader.<ProfileController>getController();
        
        controller.setMainScene(tempScene);
        try{
            Parent root = (Parent)fxmlLoader.load(); 
            tempScene.setRoot(root);
            mainStage.setScene(tempScene);  
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        controller.setLogin(profile);
        controller.setStdId(controller.getLogin().getStdId());
        controller.setLabelDepartment(controller.getLogin().getDepartment());
        controller.setLabelId(""+controller.getLogin().getStdId());
        controller.setLabelName(controller.getLogin().getName());
//        controller.getEventToProfile();

//        controller.setMainStage(thisStage);
//        controller.setThisScene(tempScene);
//        controller.setMainStage(mainStage);
//        controller.setThisStage(stage);
//        Scene scene = new Scene(root);
//        mainStage.show();
    }
    
}
