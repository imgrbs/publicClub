package publicizehub.club.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import publicizehub.club.model.Search;
import publicizehub.club.model.ConnectionBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Imagine
 */
public class SearchController implements Initializable {
    ConnectionBuilder cb = new ConnectionBuilder();
    
    Search s = new Search();
    Alert alert = new Alert(AlertType.WARNING);
    
    @FXML
    private Label label;
    @FXML
    VBox buttonBox = new VBox();
    @FXML
    TextField search;
    @FXML
    Label l;
    
    int checkEvType;

    public int getCheckEvType() {
        return checkEvType;
    }

    public void setCheckEvType(int checkEvType) {
        this.checkEvType = checkEvType;
    }

    public TextField getSearch() {
        return search;
    }

    public void setSearch(TextField search) {
        this.search = search;
    }
    
    public void setSearch(String search) {
        try{
            this.search.setText(""+search);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    protected void initialize(String eventName) {
        Pane p = new Pane();
        l= new Label(eventName);
        p.getChildren().add(l);
        buttonBox.setMargin(p,new Insets(15,25,15,30));
        p.setStyle("-fx-background-color: #" + "CD4D28");
        l.setStyle("-fx-padding: 30px 0px 0px 50px;"+
                   "-fx-font-size: 30px;"+
                   "-fx-text-fill: #fff;");
        p.setPrefSize(480,150);
        buttonBox.getChildren().add(p);
    }
    
    
    @FXML
    public void checkSearch(){
        buttonBox.getChildren().clear();
        ResultSet result;
        String temp = search.getText();
        search.setText("");
        try{
            if(temp.charAt(0)==' '||(temp.equals("")==true)||(temp.equals(" ")==true)){
                temp = "nullEventThatNoMeaning";
                search.setText("");
                alert.setTitle("คำเตือน");
                alert.setHeaderText("กรุณาใส่ชื่อกิจกรรมให้ถูกต้อง!");
                alert.showAndWait();
            } else {
                for (int i = 0; i < temp.length(); i++) {
                    if(temp.charAt(i)=='%'||temp.charAt(i)=='_'||temp.charAt(0)=='\''){
                        temp = "nullEventThatNoMeaning";
                        search.setText("");
                        alert.setTitle("คำเตือน");
                        alert.setHeaderText("อย่ามาเล่น Injection ดิ ชิ้วๆ!");
                        alert.setContentText("555555 อิอิอิอิอิอิอิอิ");
                        alert.showAndWait();
                    }
                }
            }
            result = s.resultSearch(temp);
            if(result.next()==false){
                temp = "nullEventThatNoMeaning";
                search.setText("");
                alert.setTitle("คำเตือน");
                alert.setHeaderText("ขออภัยไม่มีกิจกรรมที่คุณค้นหา!");
                alert.setContentText("ขอโทษเนาะ");
                alert.showAndWait();
            } else {
                while(result.next()){
                    initialize(result.getString("evName"));
                }
            }
        } catch(Exception e){
            temp = "nullEventThatNoMeaning";
        }
        
        System.out.println(temp);

        cb.logout();
    }
    
    @FXML
    public void checkSearchEvType() throws Exception{
        System.out.println("checkSearchEvType");
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/ViewSearch.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        if(this.checkEvType==0){
            s.resultEventType(0);
        }
        else if(this.checkEvType==1){
            s.resultEventType(1);
        }
        else {
            s.resultEventType(2);
        }
    }  
}
