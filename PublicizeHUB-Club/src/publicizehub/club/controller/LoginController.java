package publicizehub.club.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author JIL
 */

//จำลองเอาเด้อ
public class LoginController{
   
    private long stdId = 59130500007l;
    private String name = "กีรติ";
    private String surname = "เจียรจินดารัตน์";
    private String department = "Information Technnology";
    private int status = 0;
    private boolean checkLogin = true;

    
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button signinBtn;

    @FXML
    private Button registerBtn;
    
}
