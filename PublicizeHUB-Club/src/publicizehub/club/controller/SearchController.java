package publicizehub.club.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import publicizehub.club.model.SearchModel;
import publicizehub.club.model.ConnectionBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import publicizehub.club.model.ClickModel;
import publicizehub.club.model.EventModel;
import publicizehub.club.model.LoginModel;

/**
 *
 * @author Imagine
 */
public class SearchController {
    private static final Logger LOGGER = Logger.getLogger( FormSumActivityController.class.getName() );
    private ConnectionBuilder cb = new ConnectionBuilder();
    private JoinController jc = new JoinController();
    private DetailController dc = new DetailController();
    private SearchModel s = new SearchModel();
    private EventModel em = new EventModel();
    private ClickModel cm = new ClickModel();
    private Alert alert = new Alert(AlertType.WARNING);
    
    private LoginModel profile;
    
    @FXML
    private Label label;
    @FXML
    private VBox buttonBox = new VBox();
    @FXML
    private TextField search;
    @FXML
    private Label l;
    @FXML
    private Label typeName;
    
    private int checkEvType;
    
    private String text;

    public Label getTypeName() {
        return typeName;
    }

    public void setTypeName(Label typeName) {
        this.typeName = typeName;
    }

    public SearchController() {
        checkEvType=-1;
    }

    public LoginModel getProfile() {
        return profile;
    }

    public void setProfile(LoginModel profile) {
        this.profile = profile;
    }

    
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public int getCheckEvType() {
        return checkEvType;
    }

    public void setCheckEvType(int checkEvType) {
        this.checkEvType = checkEvType;
    }

    public TextField getSearch() {
        return search;
    }

    public void setSearch(TextField search) {
        this.search = search;
    }
    
    public void setSearch(String search) {
        try{
            this.search.setText(search);
        }
            catch(Exception e){
                LOGGER.log(Level.SEVERE ," setSearch Bug !");
        }
    }

    public JoinController getJc() {
        return jc;
    }

    public void setJc(JoinController jc) {
        this.jc = jc;
    }
    
    
    
    @FXML
    public void addEventToPane(String eventName,int eventId,LocalDate regisDate,LocalDate endDate) {
        System.out.println(profile.getStdId());
        getJc().setProfile(profile);
        Pane p = new Pane();
        l= new Label(eventName);
        Button joinbtn = new Button("เข้าร่วม");
        Button detailbtn = new Button("รายละเอียด");
        joinbtn.getStyleClass().add("joinbtnSearch");
        detailbtn.getStyleClass().add("detailbtnSearch");
        joinbtn.setLayoutX(285);
        joinbtn.setLayoutY(100);
        detailbtn.setLayoutX(370);
        detailbtn.setLayoutY(100);
        p.getChildren().add(l);
        p.getChildren().add(joinbtn);
        p.getChildren().add(detailbtn);
        System.out.println(LocalDate.now().compareTo(regisDate));
        if(LocalDate.now().compareTo(regisDate)<0){
            joinbtn.setDisable(true);
        }
        joinbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                getJc().setStdId(profile.getStdId());
                getJc().toJoinEvent(eventId);
            }
        });
        detailbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                dc.callDetail(eventId);
                cm.increaseClick(eventId,regisDate,endDate);
            }
        });
        buttonBox.setMargin(p,new Insets(15,25,15,30));
        p.setStyle("-fx-background-color: #" + "ffffff" + ";" +
                   "-fx-background-radius: 10px;" +
                   "-fx-effect: dropshadow(three-pass-box, #4d4d4d, 5, 0, 0, 1);");
        l.getStyleClass().add("labelNameSearch");
        l.getStyleClass().add("quark");;
        p.setPrefSize(480,150);
        buttonBox.getChildren().add(p);
    }
    
    
    @FXML
    public void checkSearch(){
        ResultSet result=null;
        buttonBox.getChildren().clear();
        String temp = search.getText();
        typeName.setText(temp);
        if(checkEvType!=-1){
            
        }else if(temp.equals("")||search.getText()==null){
                temp = this.text;
        }
        search.setText("");
        try{
            if((temp.equals("")||temp.charAt(0)==' ')&&checkEvType==-1){
                temp = "nullEventThatNoMeaning";
                search.setText("");
                alert.setTitle("คำเตือน");
                alert.setHeaderText("กรุณาใส่ชื่อกิจกรรมให้ถูกต้อง!");
                alert.showAndWait();
            } else {
                for (int i = 0; i < temp.length(); i++) {
                    if(temp.charAt(i)=='%'||temp.charAt(i)=='_'||temp.charAt(0)=='\''){
                        temp = "nullEventThatNoMeaning";
                        search.setText("");
                        alert.setTitle("คำเตือน");
                        alert.setHeaderText("กรุณาใส่ชื่อกิจกรรมให้ถูกต้อง!");
                        alert.setContentText("Injection Detected :)");
                        alert.showAndWait();
                        i=temp.length();
                    }
                }
            }
            if(checkEvType!=-1){
                result = s.resultEventType(checkEvType);
            }else {
                result = s.resultSearch(temp);
            }
            
            if(result!=null){
                if(result.next()==false){
                    search.setText("");
                    alert.setTitle("Warning!");
                    alert.setHeaderText("ขออภัย");
                    alert.setContentText("ไม่มีกิจกรรมที่คุณค้นหา..");
                    alert.showAndWait();
                } else {
                    LocalDate tempDate = result.getDate("evStartRegis").toLocalDate();
                    LocalDate tempEndDate = result.getDate("evEndDate").toLocalDate();
                    tempDate = tempDate.minusDays(10);
                    if(LocalDate.now().compareTo(tempDate)>-1&&tempEndDate.compareTo(LocalDate.now())>=0){
                        addEventToPane(result.getString("evName"),result.getInt("evId"),tempDate.plusDays(10),tempEndDate);
                    }
                    while(result.next()){
                        tempDate = result.getDate("evStartRegis").toLocalDate();
                        tempEndDate = result.getDate("evEndDate").toLocalDate();
                        tempDate = tempDate.minusDays(10);
                        if(LocalDate.now().compareTo(tempDate)>-1&&tempEndDate.compareTo(LocalDate.now())>=0){
                            addEventToPane(result.getString("evName"),result.getInt("evId"),tempDate.plusDays(10),tempEndDate);
                        }
                    }
                }
            }
        } catch(SQLException e){
            LOGGER.log(Level.SEVERE ," setSearch Bug !");
        }

        cb.logout();
    }
    
    @FXML
    public void checkSearchEvType() throws Exception{
        Stage stage= new Stage();
        Parent root=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ViewSearch.fxml"));   
        stage.setTitle("PublicizeHUB");
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(IOException e){
            LOGGER.log(Level.SEVERE ,"root : checkSearchEvType Bug !");
        }
        SearchController controller = fxmlLoader.<SearchController>getController();
        controller.setCheckEvType(getCheckEvType());
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"setScene : checkSearchEvType Bug !");
        }
        stage.show();
    }
    
    @FXML
    public ResultSet getEventToGui(String wording){
        ResultSet rs = s.resultSearch(wording);
        return rs;
    }
    
    @FXML
    public void callSearch(String text){
        Stage stage= new Stage();
        Parent root=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ViewSearch.fxml"));
        stage.setTitle("PublicizeHUB");
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(IOException e){
            LOGGER.log(Level.SEVERE ,"root : callSearch Bug !");
        }
        SearchController controller = fxmlLoader.<SearchController>getController();
        controller.setProfile(profile);
        controller.setText(text);
        controller.checkSearch();
        controller.getTypeName().setText(text);
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"stage : setScene Bug !");
        }
        stage.show();
    }
    public String typeName(int evType){
        ResultSet rs = em.getEventType(evType);
        String name="";
        try{
            if(rs.next()){
                name = rs.getString("typeName");
            }
        }catch(SQLException se){
            LOGGER.log(Level.SEVERE ,"root : typeName Bug !");
        }
        return name;
    }
    public void callSearch(int evType,LoginModel prof){
        System.out.println(prof.getStdId());
        Stage stage= new Stage();
        Parent root=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ViewSearch.fxml"));   
        stage.setTitle("PublicizeHUB");
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(IOException e){
            LOGGER.log(Level.SEVERE ,"root : callSearch Bug !");
        }
        SearchController controller = fxmlLoader.<SearchController>getController();
        controller.setCheckEvType(evType);
        controller.setProfile(prof);
        controller.checkSearch();
        controller.typeName.setText(typeName(evType));
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"stage : callSearch Bug !");
        }
        stage.show();
    }
    
    
}
