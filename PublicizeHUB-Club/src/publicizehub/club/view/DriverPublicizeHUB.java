package publicizehub.club.view;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import publicizehub.club.controller.MainController;

/**
 *
 * @author ImagineRabbits
 */
public class DriverPublicizeHUB extends Application  {
    private Stage thisStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        thisStage = stage;
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("FeedGui.fxml")); 
        Parent root = null;
        try{
            thisStage = new Stage();
            root = (Parent)loader.load(); 
        }
        catch(IOException e){
            e.printStackTrace();
        }
        MainController controller = loader.<MainController>getController();
        controller.getEvent();
        controller.setUserData(controller.getLi().getStdId(),controller.getLi().getName()+" "+controller.getLi().getSurname());
        controller.getNc().addNewsToList(controller.getNewsList());
        controller.setThisStage(thisStage);
        Scene scene = new Scene(root);
        controller.setThisScene(scene);
        
        thisStage.setScene(scene);
        thisStage.centerOnScreen();
        thisStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
