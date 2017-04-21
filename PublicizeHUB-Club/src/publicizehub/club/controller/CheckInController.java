package publicizehub.club.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ImagineRabbits
 */
public class CheckInController {
    
    
    
    @FXML
    public void callCheckIn(int eventId){
        Stage stage= new Stage();
        Parent root=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CheckIn.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
//        CheckInController controller = fxmlLoader.<CheckInController>getController();
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        stage.show();
    }
}
