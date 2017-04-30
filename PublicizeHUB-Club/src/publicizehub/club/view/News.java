/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.view;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import publicizehub.club.controller.FirstNewsController;


/**
 *
 * @author budsagorn_ss
 */
public class News extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("News.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        
        FirstNewsController controller = fxmlLoader.<FirstNewsController>getController();
        Scene scene = new Scene(root);
        controller.setThisScene(scene);
        
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
