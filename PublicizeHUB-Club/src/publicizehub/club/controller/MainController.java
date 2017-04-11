package publicizehub.club.controller;

import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import publicizehub.club.view.*;

/**
 *
 * @author ImagineRabbits
 */
public class MainController {
    SearchController sc = new SearchController();
    ResultSet rs = null;
    
    @FXML
    Label labelEvMain1;
    @FXML
    Label labelEvMain2;
    
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
    
    @FXML
    protected void getEvent(){
        if(rs==null){
            System.out.println("LOAD EVENT ONSTART");
            rs = sc.getEventToGui("IT 3K ครั้งที่ 13");
            try{
                if(rs.next()){
                    labelEvMain1.setText(rs.getString("evName"));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            rs = sc.getEventToGui("Brown Bag #2.0");
            try{
                if(rs.next()){
                    labelEvMain2.setText(rs.getString("evName"));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
}
