package publicizehub.club.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author ImagineRabbits
 */
public class ShowCodeController {
    private long stdId;

    @FXML
    Label codeText;
    
    
    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }

    public Label getCodeText() {
        return codeText;
    }

    public void setCodeText(String codeText) {
        this.codeText.setText(codeText);
    }
    
    
    
}
