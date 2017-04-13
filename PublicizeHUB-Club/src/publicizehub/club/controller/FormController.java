package publicizehub.club.controller;

/**
 *
 * @author ImagineRabbits
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FormController {

    @FXML
    private Label evName;

    @FXML
    private Button acceptBtn;

    public Label getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName.setText(evName);
    }
        
    
}

