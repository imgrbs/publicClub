package publicizehub.club.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ImagineRabbits
 */
public class ManageController implements Initializable  {
    private NewsController nc = new NewsController();
    private LoginController li = new LoginController();
    
    private Stage mainStage;
    private Stage thisStage;
    
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
    private VBox listEventBox1 = new VBox(); // Box เก็บกิจกรรมที่ยังไม่จบ
    @FXML
    private VBox listEventBox2 = new VBox(); // Box เก็บกิจกรรมที่จบแล้ว

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
    
    @FXML
    public void callAddNews(){
        nc.callAddNews();
    }
    
    @FXML
    public void callMain(){
        mainStage.show();
        thisStage.close();
    }
    
    @FXML
    public void callManage(Stage mainStage){
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Manage.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        ManageController controller = fxmlLoader.<ManageController>getController();
        controller.setStdId(getLi().getStdId());
        controller.setLabelDepartment(li.getDepartment());
        controller.setLabelId(""+li.getStdId());
        controller.setLabelName(li.getName()+" "+li.getSurname());
//        controller.getEventToProfile();
        controller.setMainStage(mainStage);
        controller.setThisStage(stage);
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        stage.show();
        mainStage.close();
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
            
    
}
