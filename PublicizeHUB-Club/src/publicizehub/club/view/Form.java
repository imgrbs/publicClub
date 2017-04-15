package publicizehub.club.view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import publicizehub.club.controller.FormController;

/**
 *
 * @author ImagineRabbits
 */
public class Form extends Application   {
    @Override
    public void start(Stage stage) throws Exception {
        
        try{
            stage = new Stage();
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("FormEvaluations.fxml"));  
            FormController controller=null;
            Parent root = null;
            root = (Parent)loader.load(); 
            controller = loader.<FormController>getController();
            controller.setEvName("Dek-D Adminssion");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }  
}
