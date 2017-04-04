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

import publicizehub.club.model.Search;
import publicizehub.club.model.ConnectionBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Imagine
 */
public class SearchController implements Initializable {
    Search s = new Search();
    ConnectionBuilder cb = new ConnectionBuilder();
    
    @FXML
    private Label label;
    @FXML
    VBox buttonBox = new VBox(8);
    @FXML
    TextField search;
    @FXML
    Label l;
    
    Alert alert = new Alert(AlertType.WARNING);

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
            if(temp.charAt(0)!=' '&&(temp.equals("")==false)&&(temp.equals(" ")==false)){
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
            } else {
                temp = "nullEventThatNoMeaning";
                search.setText("");
                alert.setTitle("คำเตือน");
                alert.setHeaderText("กรุณาใส่ชื่อกิจกรรม!");
//                alert.setContentText("");
                alert.showAndWait();
            }
        } catch(Exception e){
            temp = "nullEventThatNoMeaning";
        }
        
        System.out.println(temp);
        result = s.resultSearch(temp);
        try{
            while(result.next()){
                initialize(result.getString("evName"));
            }
            if(result.next()==false){
                search.setText("");
                alert.setTitle("คำเตือน");
                alert.setHeaderText("ขออภัยไม่มีกิจกรรมที่คุณค้นหา!");
                alert.setContentText("ขอโทษเนาะ");
                alert.showAndWait();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLException checkSearch After Add Name");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception checkSearch After Add Name");
        }
        cb.logout();
    }
}
