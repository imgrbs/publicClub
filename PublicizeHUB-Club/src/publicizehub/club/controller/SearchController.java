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

/**
 *
 * @author Imagine
 */
public class SearchController implements Initializable {
    
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
        buttonBox.setMargin(p,new Insets(1,1,15,1));
        p.setStyle("-fx-background-color: #" + "AAA");
        l.setStyle("-fx-padding: 5px;");
        p.setPrefSize(100,30);
        buttonBox.getChildren().add(p);
    }
    
    @FXML
    public void checkSearch(){
        String temp = searchField.getText();
        for (int i = 0; i < temp.length(); i++) {
            if(temp.charAt(i)==' '||temp.charAt(i)=='%'||temp.charAt(i)=='_'||temp.charAt(0)=='\''){
                temp = "nullEventThatNoMeaning";
            }
        }
        
    }
}
