package publicizehub.club.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ImagineRabbits
 */
public class ManageController {
    private NewsController nc = new NewsController();
    private LoginController li = new LoginController();
    
    private Stage mainStage;
    private Stage thisStage;
    
    @FXML
    private JFXButton addNewsBtn;

    public LoginController getLi() {
        return li;
    }

    public void setLi(LoginController li) {
        this.li = li;
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
//        controller.setStdId(getLi().getStdId());
//        controller.setLabelDepartment(li.getDepartment());
//        controller.setLabelId(""+li.getStdId());
//        controller.setLabelName(li.getName()+" "+li.getSurname());
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
            
    
}
