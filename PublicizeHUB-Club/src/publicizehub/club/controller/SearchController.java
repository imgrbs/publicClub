package publicizehub.club.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

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
        buttonBox.getChildren().removeAll();
        ResultSet result;
        String temp = search.getText();
        if(temp.charAt(0)!=' '){
            for (int i = 0; i < temp.length(); i++) {
                if(temp.charAt(i)=='%'||temp.charAt(i)=='_'||temp.charAt(0)=='\''){
                    temp = "nullEventThatNoMeaning";
                }
            }
        } else {
            System.out.println("Alert");
            temp = "nullEventThatNoMeaning";
        }
        
        System.out.println(temp);
        result = s.resultSearch(temp);
        try{
            while(result.next()){
                initialize(result.getString("evName"));
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
