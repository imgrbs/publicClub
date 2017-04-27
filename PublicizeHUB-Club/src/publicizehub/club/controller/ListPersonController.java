package publicizehub.club.controller;

//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import publicizehub.club.model.CheckInModel;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.EventModel;
import publicizehub.club.model.PersonModel;

/**
 *
 * @author ImagineRabbits
 */
public class ListPersonController {

    PersonModel psm = new PersonModel();
    CheckInModel cim = new CheckInModel();
    EventModel evm = new EventModel();
    ConnectionBuilder cb = new ConnectionBuilder();
    
    ObservableList<String> items =FXCollections.observableArrayList();  
    
    private String evName;
    private int evId;
    private String stdName;
    private long stdId;

    public String getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName = evName;
    }

    public int getEvId() {
        return evId;
    }

    public void setEvId(int evId) {
        this.evId = evId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }
    
    @FXML
    private ListView<String> listName;

    @FXML
    private Label show_evName;

    @FXML
    private Button closeBtn;

    @FXML
    public void callListPerson(int evId){
        //ListPerson lp = new ListPerson();
        //lp.setVisible(true);
        Stage stage= new Stage();
        Parent root=null;
        ResultSet rs = evm.getEvent();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ListPersonName.fxml"));     
        try{
           root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        ListPersonController controller = fxmlLoader.<ListPersonController>getController();

        try{
            if(rs.next()){
                controller.show_evName.setText(rs.getString("evName"));
                controller.setEvId(rs.getInt("evId"));
            }
        } catch (SQLException se){
            System.out.println("SQL Exception");
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
    
    public void showPersonName(int evId) {
         ResultSet rs = psm.getListPerson(evId);
 
        try{
            while(rs.next()){ 
                long std_id = rs.getLong("stdId");
                ResultSet getName = cim.getName(std_id);
                while(getName.next()){
                    String name = getName.getString("std_name");
                    String data = std_id+"\t"+name;
                    items.add(data);
                }           
            }
            
            listName.setItems(items);
        }catch(SQLException e){
            System.out.println("SQL Exception");
        }
        cb.logout();
       
    }
       
   

}



