package publicizehub.club.controller;

import com.jfoenix.controls.JFXButton;
import static java.lang.Long.parseLong;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.Event;

/**
 *
 * @author ImagineRabbits
 */
public class ManageController implements Initializable  {
    private NewsController nc = new NewsController();
    private LoginController li = new LoginController();
    private EventController ec = new EventController();
    private ConnectionBuilder cb = new ConnectionBuilder();
    
    private Event ev = new Event();
    
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
        controller.getEventToProfile();
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
    
    @FXML
    public void getEventToProfile(){
        System.out.println("Befor Get Event");
        ResultSet rs = ev.getSelect(parseLong(this.labelId.getText()));
        cb.logout();
        System.out.println("After Get Event");
        try{
            if(rs.next()){
                System.out.println("Event Come");
                setEventToGui(rs.getInt("evId"));
                while(rs.next()){
                    setEventToGui(rs.getInt("evId"));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        cb.logout();
    }

    public void setEventToGui(int eventId){
        ResultSet findStd = ev.getSelect(eventId);
        ec.setStdId(getStdId());
        try{
            if(findStd.next()){
                LocalDate ld = LocalDate.parse(""+findStd.getString("evEndDate"));
                if(ld.compareTo(LocalDate.now())>-1){ 
                    ec.addEventToPresentPane(findStd.getString("evName"),
                            findStd.getInt("evId"),this.listEventBox1,true); 
                }
                else {
                    ec.addEventToPresentPane(findStd.getString("evName"),
                            findStd.getInt("evId"),this.listEventBox2,false);
                    while(findStd.next()){
                        ld = LocalDate.parse(findStd.getString("evEndDate"));
                        if(ld.compareTo(LocalDate.now())>-1){
                            ec.addEventToPresentPane(findStd.getString("evName"),
                                    findStd.getInt("evId"),this.listEventBox1,true);
                        }
                        else {
                            ec.addEventToPresentPane(findStd.getString("evName"),
                                    findStd.getInt("evId"),this.listEventBox2,false);
                        }
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        cb.logout();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
            
    
}
