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

/**
 * FXML Controller class
 *
 * @author JIL
 */
public class ProfileController {
    
    private static final Logger LOGGER = Logger.getLogger( FormSumActivityController.class.getName() );
    EventModel ev = new EventModel();
    ConnectionBuilder cb = new ConnectionBuilder();
    
    LoginController li = new LoginController();
    JoinController jc = new JoinController();
    DetailController dc = new DetailController();
    EventController ec = new EventController();
    
    private long stdId;
    
    private Stage mainStage;
    private Stage thisStage;
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
        mainStage.show();
//        thisStage.close();
    }
    
    @FXML
    public void callProfile(Stage mainStage,Scene tempScene){
//        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Profile.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        ProfileController controller = fxmlLoader.<ProfileController>getController();
        controller.setStdId(li.getStdId());
        controller.setLabelDepartment(li.getDepartment());
        controller.setLabelId(""+li.getStdId());
        controller.setLabelName(li.getName()+" "+li.getSurname());
        controller.getEventToProfile();
//        controller.setMainStage(thisStage);
        controller.setMainStage(mainStage);
//        controller.setThisStage(stage);
        controller.setThisScene(tempScene);
        Scene scene = new Scene(root); 
        try{
            mainStage.setScene(scene);    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        mainStage.show();
    }
    
}
