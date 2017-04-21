package publicizehub.club.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.ResultSet;
import publicizehub.club.model.ConnectionBuilder;

/**
 *
 * @author ImagineRabbits
 */
public class MainController {
    private ConnectionBuilder cb = new ConnectionBuilder();
    private LoginController li = new LoginController();
    private ManageController mc = new ManageController();
    private SearchController sc = new SearchController();
    private JoinController jc = new JoinController();
    private DetailController dc = new DetailController();
    private NewsController nc = new NewsController();
    private ResultSet rs = null;

    private Stage thisStage;
    
    @FXML
    private Label stdId;

    @FXML
    private Label stdName;

    @FXML
    private Label labelEvMain1;
    
    @FXML
    private Label labelEvMain2;
    
    @FXML
    private Button joinEvMain1;
    
    @FXML
    private Button joinEvMain2;
    
    @FXML
    private Button detailEv1;
    
    @FXML
    private Button detailEv2;
    
    @FXML
    private Button manageBtn;
    
    @FXML
    private ImageView managePic;
    
    @FXML
    private TextField searchfield;
    
    @FXML
    private ListView<String> newsList;

    public LoginController getLi() {
        return li;
    }
    
    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }
    
    public void setManageDisable(){
        this.manageBtn.setVisible(false);
        this.managePic.setVisible(false);
    }
    
    public void setUserData(long stdId,String stdName){
        this.stdId.setText(""+stdId);
        this.stdName.setText(""+stdName);
        if(this.getLi().getStatus()==1){
            this.setManageDisable();
        }
    }

    public NewsController getNc() {
        return nc;
    }

    public void setNc(NewsController nc) {
        this.nc = nc;
    }

    public ListView<String> getNewsList() {
        return newsList;
    }

    public void setNewsList(ListView<String> newsList) {
        this.newsList = newsList;
    }

    public MainController() {
     try{
         cb.connecting();
     }finally{
         cb.logout();
     }
    }
    
    @FXML
    protected void callProfile() {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Profile.fxml"));     
        try{
            root = (Parent)fxmlLoader.load(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        ProfileController controller = fxmlLoader.<ProfileController>getController();
        controller.setStdId(getLi().getStdId());
        controller.setLabelDepartment(li.getDepartment());
        controller.setLabelId(""+li.getStdId());
        controller.setLabelName(li.getName()+" "+li.getSurname());
        controller.getEventToProfile();
        controller.setMainStage(thisStage);
        controller.setThisStage(stage);
        Scene scene = new Scene(root); 
        try{
            stage.setScene(scene);    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        stage.show();
        thisStage.close();
    }
    
    @FXML
    protected void callSearchCamp() {
        System.out.println("callSearchCamp");
//        sc.setCheckEvType(0);
//        try {
//            sc.checkSearchEvType();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        sc.callSearch(0);
        searchfield.setText("");
    }
    
    @FXML
    protected void callSearchSeminar() {
        System.out.println("callSearchSeminar");
        sc.callSearch(1);
        searchfield.setText("");
    }
    
    @FXML
    protected void callSearchOther() {
        System.out.println("callCampOther");
        sc.callSearch(2);
        searchfield.setText("");
    }


    @FXML
    public void getEvent() {
        int eventId1,eventId2;
        if (rs == null) {
            System.out.println("LOAD EVENT ONSTART");
            rs = sc.getEventToGui("IT 3K ครั้งที่ 13");
            try {
                if (rs.next()) {
                    labelEvMain1.setText(rs.getString("evName"));
                    eventId1 = rs.getInt("evId");
                    joinEvMain1.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            jc.toJoinEvent(eventId1);
                        }
                    });
                    detailEv1.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            dc.callDetail(eventId1);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = sc.getEventToGui("Brown Bag #2.0");
            try {
                if (rs.next()) {
                    labelEvMain2.setText(rs.getString("evName"));
                    eventId2 = rs.getInt("evId");
                    joinEvMain2.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            jc.toJoinEvent(eventId2);
                        }
                    });
                    detailEv2.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            dc.callDetail(eventId2);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    public void callManage(){
        mc.callManage(thisStage);
    }
    
    @FXML
    public void sentToSearch(){
        String text = searchfield.getText();
        System.out.println(text);
        sc.callSearch(text);
        searchfield.setText("");
    }
}
