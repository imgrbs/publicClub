package publicizehub.club.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.LoginModel;

/**
 *
 * @author JIL
 */
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    ConnectionBuilder cb = new ConnectionBuilder();

    LoginModel lm = new LoginModel();

    private long stdId;
    private String name;
    private String department;
    private int status;
    private boolean checkLogin = true;

    private Stage thisStage;
    private Scene thisScene;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label warning;

    @FXML
    private Button signinBtn;

    @FXML
    private Button registerBtn;

    public LoginController() {
    }

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getCheckLogin() {
        return checkLogin;
    }

    public Scene getThisScene() {
        return thisScene;
    }

    public void setThisScene(Scene thisScene) {
        this.thisScene = thisScene;
    }

    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    public void callLogin(Stage stage,Scene scene) {
        
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/LoginGui.fxml"));
        stage.setTitle("PublicizeHUB");
        try {
            root = (Parent) fxmlLoader.load();
            scene.setRoot(root);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "root : callLogin Failed");
        }

        LoginController controller = fxmlLoader.<LoginController>getController();
        
        controller.setThisStage(stage);
        controller.setThisScene(scene);
        
    }

    @FXML
    public void getValue() {
        String tempUn = this.username.getText();
        String tempPw = this.password.getText();
        if (tempUn != null && tempPw != null) {
            if ((tempUn.length() > 5) || tempPw.length() > 5) {
                this.username.clear();
                this.password.clear();
                try {
                    LoginModel profile = lm.login(tempUn, tempPw, warning);
                    if (profile != null) {
                        callMain(profile);
                    }
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "getValue : getValue Failed");
                }
            } else {
                warning.setText("กรุณากรอก Username และ Password ให้ถูกต้อง");
            }
        } else {
            warning.setText("กรุณากรอก Username และ Password");
        }
    }

    
    @FXML
    public void callMain(LoginModel prof) {
        this.name = prof.getName();
        this.department = prof.getDepartment();
        this.status = prof.getStatus();
        this.stdId = prof.getStdId();
        MainController mc = new MainController();
        mc.setProfile(prof);
        try {
            mc.callMain(thisStage, thisScene, prof);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}