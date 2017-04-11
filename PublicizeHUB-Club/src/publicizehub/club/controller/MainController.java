package publicizehub.club.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        System.out.println(searchfield.getText());
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
