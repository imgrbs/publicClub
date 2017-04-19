package publicizehub.club.controller;

/**
 *
 * @author ImagineRabbits
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import publicizehub.club.model.Event;
import publicizehub.club.view.ListPerson;

public class DetailController {
    private Event ev = new Event();

    @FXML
    private Label evName;

    @FXML
    private Label evDate;

    @FXML
    private Label evPlace;

    @FXML
    private TextArea evDescrip;

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
    
    

    @FXML
    public void callDetail(int eventId){
        ResultSet rs = ev.getSelect(eventId);
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
    public void callDetail(Event event){
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
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        stage.show();
    }
    
    public void callListPerson(){
        ListPerson lp = new ListPerson();
        lp.setVisible(true);
    }
}
