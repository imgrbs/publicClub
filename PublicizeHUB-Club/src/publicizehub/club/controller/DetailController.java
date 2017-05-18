package publicizehub.club.controller;

/**
 *
 * @author ImagineRabbits
 */
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import publicizehub.club.model.EventModel;
import publicizehub.club.view.ListPerson;

public class DetailController {
    private EventModel ev = new EventModel();
    private ListPersonController lpc = new ListPersonController();

    private Stage thisStage;
    private int eventId;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    
    
    
    @FXML
    private Label evName;

    @FXML
    private Label evDate;

    @FXML
    private Label evPlace;

    @FXML
    private TextArea evDescrip;
    
    @FXML
    private Button showName;

    public void setEvName(String evName) {
        this.evName.setText(evName);
    }

    public void setEvDate(String evDate) {
        this.evDate.setText(evDate);
    }

    public void setEvPlace(String evPlace) {
        this.evPlace.setText(evPlace);
    }

    public void setEvDescrip(String evDescrip) {
        this.evDescrip.setText(evDescrip);
    }
    
        public Stage getThisStage() {
        return thisStage;
    }
    
    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
        thisStage.setTitle("PublicizeHUB");   
    }

    @FXML
    public void callDetail(int eventId){
        ResultSet rs = ev.getSelect(eventId);
        Stage stage= new Stage();
        Parent root=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Detail.fxml"));
        stage.setTitle("PublicizeHUB");
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        DetailController controller = fxmlLoader.<DetailController>getController();
            controller.setEventId(eventId);
            System.out.println(eventId);
        try{
            if(rs.next()){
                controller.setEvName(rs.getString("evName"));
                controller.setEvDate(rs.getString("evStartDate"));
                controller.setEvPlace(rs.getString("evPlace"));
                controller.setEvDescrip(rs.getString("evDescrip"));
                
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        stage.show();
    }
    
        @FXML
    public void callDetail(EventModel event){
        Stage stage= new Stage();
        Parent root=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Detail.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        DetailController controller = fxmlLoader.<DetailController>getController();
        controller.setEvName(event.getEvName());
        controller.setEvDate(event.getEvDate()+"");
        controller.setEvPlace(event.getEvPlace());
        controller.setEvDescrip(event.getEvDescrip());
        controller.setThisStage(thisStage);
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        stage.show();
    }
    
    public void callListPerso(){
        lpc.callListPerson(eventId);
    }
    
}
