package publicizehub.club.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import publicizehub.club.view.*;

/**
 *
 * @author ImagineRabbits
 */
public class MainController {
    SearchController sc = new SearchController();
    
    @FXML
    TextField searchfield;
    
    @FXML
    protected void callAdminGui() {
        AdminGUI ag  = new AdminGUI();
        ag.setTheme();
        ag.Run();
        ag.setVisible(true);
    }
    
    @FXML
    protected void callSearchText(){
        Stage stage = new Stage();
        Parent root = null;
        try{
           root = FXMLLoader.load(getClass().getResource("../view/ViewSearch.fxml"));
        } 
        catch(Exception e){
           e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        sc.setSearch(searchfield.getText());
        sc.checkSearch();
    }
    
    @FXML
    protected void callSearchEvent(){
        System.out.println("callCampEvent");
        sc.setCheckEvType(0);
        try {
            sc.checkSearchEvType();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception callCampEvent");
        }
    }
}
