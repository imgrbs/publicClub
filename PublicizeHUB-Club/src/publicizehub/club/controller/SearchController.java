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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Imagine
 */
public class SearchController implements Initializable {
    Search s = new Search();
    
    @FXML
    private Label label;
    private TextField searchField;
    @FXML
    VBox buttonBox = new VBox(8);
    
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
    protected void initialize() {
        Pane p = new Pane();
        Label l = new Label("Add");
        p.getChildren().add(l);
        buttonBox.setMargin(p,new Insets(30,30,0,30));
        p.setStyle("-fx-background-color: #" + "AAA");
        l.setStyle("-fx-padding: 30px 0px 0px 50px");
        l.setStyle("-fx-font-size: 30px");
        p.setPrefSize(480,150);
        buttonBox.getChildren().add(p);
    }
    
    @FXML
    public void checkSearch(){
        ResultSet rs;
        String temp = searchField.getText();
        if(temp.charAt(0)==' '){
            System.out.println("Alert");
        } else {
            for (int i = 0; i < temp.length(); i++) {
                if(temp.charAt(i)=='%'||temp.charAt(i)=='_'||temp.charAt(0)=='\''){
                    temp = "nullEventThatNoMeaning";
                }
            }
        }
        
        try{
            while(s.resultSearch(temp).next()){
                
            }
        }
        catch(SQLException e){
            
            
        }
        catch(Exception e){
            
        }
        
    }
}
