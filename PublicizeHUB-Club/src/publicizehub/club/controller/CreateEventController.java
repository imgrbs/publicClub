package publicizehub.club.controller;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.jfoenix.controls.*;


import publicizehub.club.model.EventModel;

/**
 * FXML Controller class
 *
 * @author JIL
 */
public class CreateEventController implements Initializable {
    EventModel e = new EventModel();
    EventModel thisEvent = null;
    
    LoginController lc = new LoginController();
    
    private int evType = -1;
    private long stdId=lc.getStdId();
    boolean check=true;
    
    @FXML
    private Stage thisStage = null;
    @FXML
    private JFXTextField eventName;
    @FXML
    private JFXDatePicker startDate;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private JFXTimePicker startTime;
    @FXML
    private JFXTimePicker endTime;
    @FXML
    private JFXDatePicker startRegis;
    @FXML
    private ComboBox<String> ticket;
    @FXML
    private ToggleGroup type;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField place;

    @FXML
    private JFXRadioButton camp;

    @FXML
    private JFXRadioButton seminar;

    @FXML
    private JFXRadioButton other;

    @FXML
    private JFXButton confirmBtn;
    @FXML
    private JFXButton cancelBtn;

    @FXML
    private Label warnNum;
    private String customText="ระบุเอง";


    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    public JFXButton getCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(JFXButton cancelBtn) {
        this.cancelBtn = cancelBtn;
    }
    
    

    @FXML
    public void setAllValue(){
        thisEvent = new EventModel(stdId,eventName.getText(),description.getText(),startDate.getValue(),endDate.getValue(),
            startRegis.getValue(),startRegis.getValue().plusDays(15),place.getText(),Integer.parseInt(ticket.getValue()),
            startTime.getValue(),endTime.getValue(),evType);
    }
    @FXML
    public void evTypeResult(){
        if(camp.isSelected()){
            this.evType=0;
        }else if(seminar.isSelected()){
            this.evType=1;
        }else if(other.isSelected()){
            this.evType=2;
        }
    }
    
    @FXML
    public void checkCustomize(){
        String customText="ระบุเอง";
        if(ticket.getValue().equals(customText)){
            ticket.setEditable(true);   
            ticket.setValue(" ");
        }else {
            ticket.setEditable(false);
        }
    } 
    
    @FXML
    public void setValueToCombobox(){
        ticket.getItems().addAll("5","10","15","20","25","30","35","40","45","50","75","100","ระบุเอง");  
    }
    
    @FXML
    public void clickConfirm(){
        Alert warning = null;
        evTypeResult();
        validateField();
        if(this.check){
            warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("Error!");
            warning.setHeaderText("กรุณากรอกข้อมูลให้ครบทุกช่อง!");
            warning.showAndWait();            
        }else{
            warning = new Alert(Alert.AlertType.CONFIRMATION);
            warning.setTitle("Information!");
            warning.setHeaderText("ยืนยันที่จะสร้างกิจกรรม");
            Optional<ButtonType> result = warning.showAndWait();
            if(result.isPresent()){
                if(result.get() == ButtonType.OK){
                    setAllValue();
                    e.createEvent(thisEvent);
                    warning = new Alert(Alert.AlertType.INFORMATION);
                    setEmptyField();
                    warning.setTitle("Success!");
                    warning.setHeaderText("สร้างกิจกรรมสำเร็จ");
                    warning.showAndWait();
                }
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setValueToCombobox();
    }
    
    @FXML
    public void callCreateEvent(){
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CreateEvent.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        CreateEventController controller = fxmlLoader.<CreateEventController>getController();
        controller.setThisStage(stage);
        controller.getCancelBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.closeStage();
            }
        });
        Scene scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void checkNumber(){
        Alert warning = null;
        for(int i=0;i<ticket.getValue().length();i++){
            if (ticket.getValue().charAt(i) < '0' || ticket.getValue().charAt(i) > '9') {
                warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Error!");
                warning.setHeaderText("จำนวนบัตรไม่ถูกต้อง");
                warning.setContentText("กรุณาใส่จำนวนบัตรให้ถูกต้อง (0-9)");
                warning.showAndWait();
                this.check=true;
                i=ticket.getValue().length();
                ticket.setValue("");
            }
        }
    }
                
    @FXML
    public void closeStage(){
        getThisStage().close();
    }
    
    public void validateField(){
        if(ticket.getValue()==null||ticket.getValue().equals("ระบุเอง")||ticket.getValue().equals("")||
           eventName.getText().equals("")||description.getText().equals("")||
           place.getText().equals("")||startRegis.getValue()==null||
           startDate.getValue()==null||endDate.getValue()==null||
           startTime.getValue()==null||endTime.getValue()==null||evType==-1){
            this.check = true;
        }else if(ticket.getValue()!=null) {
                checkNumber();
        }
    }
    
    public void setEmptyField(){
        String customText="ระบุเอง";
        eventName.setText("");
        description.setText("");
        place.setText("");
        try{
            startRegis.setValue(null);
            startDate.setValue(null);
            endDate.setValue(null);
            startTime.setValue(null);
            endTime.setValue(null);
        }catch(NullPointerException e){
            System.out.println("Set Date Value = null : maybe BUG");
        }
        evType=-1;
        camp.setSelected(false);
        seminar.setSelected(false);
        other.setSelected(false);
        ticket.setValue(customText);
    }
}
