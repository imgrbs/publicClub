package publicizehub.club.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.GenerateCode;
import publicizehub.club.model.News;

/**
 *
 * @author JIL
 */
public class NewsController {
    private static final Logger LOGGER = Logger.getLogger( GenerateCode.class.getName() );
    private News nw = new News();
    private ArrayList<String> myArrList = new ArrayList<>();
    private ConnectionBuilder cb = new ConnectionBuilder();
    
    @FXML
    private JFXTextArea textNews;

    @FXML
    private JFXButton submit;
    
    public void callAddNews(){
        Stage stage= new Stage();
        Parent root=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/AddNews.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(IOException e){
            e.printStackTrace();
        }
        NewsController controller = fxmlLoader.<NewsController>getController();
        controller.submit.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                controller.insertNew();
            }
        });
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"callAddNews : callAddNews Bug !");
        }
        stage.show();
    }
    
    @FXML
    public void insertNew() {
        String text = textNews.getText();
        if(text.equals("")||text.length()<15){
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("Error!");
            warning.setHeaderText("กรุณาใส่ข้อความอย่างน้อย 15 ตัวอักษร");
            warning.showAndWait();
        }else {
            Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
            warning.setTitle("เพิ่มข่าว");
            warning.setHeaderText("ยืนยันการเพิ่มข่าว");
            warning.setContentText("ยืนยันความถูกต้องและต้องการเพิ่มข่าว?");
            Optional<ButtonType> result = warning.showAndWait();
            if(result.isPresent()){
                if (result.get() == ButtonType.OK){
                    nw.toInsertNews(text);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("เพิ่มข่าวสำเร็จแล้ว");
                    alert.showAndWait();
                    textNews.setText("");
                }
            }
        }
        
    }
    
    @FXML
    public void addNewsToList(ListView<String> list){
        ObservableList<String> items =FXCollections.observableArrayList();
        ResultSet news = nw.getNews();
        try{
            while(news.next()){
                items.add(news.getString("content"));
            }
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"addNewsToList : addNewsToList Bug !");
        }
        list.setItems(items);

    }
}
