package publicizehub.club.view;

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
public class main extends Application  {
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("FeedGui.fxml"));  
        MainController controller=null;
        Parent root = null;
        try{
            stage = new Stage();
            root = (Parent)loader.load(); 
            controller = loader.<MainController>getController();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        controller.getEvent();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
