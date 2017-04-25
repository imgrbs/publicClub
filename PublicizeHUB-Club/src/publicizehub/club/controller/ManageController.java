package publicizehub.club.controller;

import com.jfoenix.controls.JFXButton;
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
    
    private Stage mainStage;
    private Stage thisStage;
    private Scene thisScene;
    
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

    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    public Scene getThisScene() {
        return thisScene;
    }

    public void setThisScene(Scene thisScene) {
        this.thisScene = thisScene;
    }
    
    
    
    @FXML
    public void callAddNews(){
        nc.callAddNews();
    }
    
    @FXML
    public void callMain(){
        mainStage.setScene(thisScene);    
        mainStage.show();
//        thisStage.close();
    }
    
    @FXML
    public void callManage(Stage mainStage,Scene tempScene){
//        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Manage.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            System.out.println("ERROR at callManage");
        }
        ManageController controller = fxmlLoader.<ManageController>getController();
        controller.setStdId(getLi().getStdId());
        controller.setLabelDepartment(li.getDepartment());
        controller.setLabelId(""+li.getStdId());
        controller.setLabelName(li.getName()+" "+li.getSurname());
        controller.setEventToGui(li.getStdId());
        controller.setMainStage(mainStage);
//        controller.setThisStage(stage);
        controller.setThisScene(tempScene);
        Scene scene = new Scene(root); 
        try{
            mainStage.setScene(scene);    
        }
        catch(Exception e){
            System.out.println("ERROR at callManage");
        }
        mainStage.show();
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
                    ec.addEventToPresentPane(event,this.listEventBox1,true,false); 
                }
                else {
                    ec.addEventToPresentPane(event,this.listEventBox2,false,false);
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
