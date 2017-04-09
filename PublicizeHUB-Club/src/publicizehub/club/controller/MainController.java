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
import publicizehub.club.view.*;
/**
 *
 * @author ImagineRabbits
 */
public class MainController {
    
    @FXML
    protected void callAdminGui() {
        AdminGUI ag  = new AdminGUI();
        ag.setTheme();
        ag.Run();
        ag.setVisible(true);
    }
}
