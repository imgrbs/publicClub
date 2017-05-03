/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.NewsModel;

/**
 * FXML Controller class
 *
 * @author Mujill
 */
public class FirstNewsController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());
    ConnectionBuilder cb = new ConnectionBuilder();
    LoginController li = new LoginController();
    NewsController nc = new NewsController();
    NewsModel nw = new NewsModel();
            
    
    
    @FXML
    private ListView<String> listNews;

    @FXML
    private JFXButton loginBtn;
    
    private Stage thisStage;
    private Scene thisScene;

    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    public Scene getThisScene() {
        return thisScene;
    }

    public void setThisScene(Scene thisScene) {
        this.thisScene = thisScene;
    }
    @FXML
    public void callFirstNews() {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/News.fxml"));
        //stage.setTitle("Login");
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "root : callLogin Failed");
        }

        FirstNewsController controller = fxmlLoader.<FirstNewsController>getController();
        System.out.println("callFirstNews");
        Scene scene = new Scene(root);
        controller.setThisStage(stage);
        controller.setThisScene(scene);
        controller.addNewsToList(controller.listNews);
        
        try {
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("Exception");
        }
        stage.show();
    }
    
    public void callLogin(){
        li.callLogin(thisStage,thisScene);
    }
    //@FXML
    public void addNewsToList(ListView<String> list){
        ObservableList<String> items =FXCollections.observableArrayList();
        ResultSet news = nw.getNews();
        System.out.println("Method addNewsTolist");
        try{
            while(news.next()){
                               
            if( ((LocalDate.now()).compareTo(news.getDate("datestamp").toLocalDate().plusDays(10))<1) && 
                  ((LocalDate.now()).compareTo(news.getDate("datestamp").toLocalDate().minusDays(10)) > -1)){
                    System.out.println(news.getString("content"));
                    String times = news.getString("timestamp").substring(0,5);
                    System.out.println(times);
                    String temp = news.getDate("datestamp")+" | "+ times +"   "+news.getString("content");
                    System.out.println(temp);
                    items.add(0,temp);
                    System.out.println("items");
                }
            }

         
    
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"addNewsToList : addNewsToList Bug !");
        }
        list.setItems(items);
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addNewsToList(listNews);
    }    
    
}
