package publicizehub.club.controller;

import java.io.IOException;
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
import java.util.Optional;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.LoginModel;

/**
 *
 * @author ImagineRabbits
 */
public class MainController {
    private ConnectionBuilder cb = new ConnectionBuilder();
    private LoginController li = new LoginController();
    private ProfileController pc = new ProfileController();
    private ManageController mc = new ManageController();
    private SearchController sc = new SearchController();
    private JoinController jc = new JoinController();
    private DetailController dc = new DetailController();
    private NewsController nc = new NewsController();
    private FirstNewsController fc = new FirstNewsController();
    private ResultSet rs = null;
    
    private LoginModel profile;
    
    private Stage thisStage;
    private Scene thisScene;

    public Scene getThisScene() {
        return thisScene;
    }

    public void setThisScene(Scene thisScene) {
        this.thisScene = thisScene;
    }

    public LoginModel getProfile() {
        return profile;
    }

    public void setProfile(LoginModel profile) {
        this.profile = profile;
    }
    
    
    
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
    
    @FXML
    private Button logoutBtn;
        
    public LoginController getLi() {
        return li;
    }
    
    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
        thisStage.setTitle("PublicizeHUB");   
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

    public JoinController getJc() {
        return jc;
    }

    public void setJc(JoinController jc) {
        this.jc = jc;
    }

    public SearchController getSc() {
        return sc;
    }

    public void setSc(SearchController sc) {
        this.sc = sc;
    }
    
     
    
    @FXML
    public void callProfile() {
        pc.callProfile(thisStage,thisScene,getProfile());
    }
    
    @FXML
    protected void callSearch0() {
        sc.callSearch(0,getProfile());
        searchfield.clear();
    }
    
    @FXML
    protected void callSearch1() {
        sc.callSearch(1,getProfile());
        searchfield.clear();
    }
    
    @FXML
    protected void callSearch2() {
        sc.callSearch(2,getProfile());
        searchfield.clear();
    }
    @FXML
    protected void callSearch3() {
        sc.callSearch(3,getProfile());
        searchfield.clear();
    }
    @FXML
    protected void callSearch4() {
        sc.callSearch(4,getProfile());
        searchfield.clear();
    }
    @FXML
    protected void callSearch5() {
        sc.callSearch(5,getProfile());
        searchfield.clear();
    }
    @FXML
    protected void callSearch6() {
        sc.callSearch(6,getProfile());
        searchfield.clear();
    }

    @FXML
    public void getEvent() {
        int eventId1,eventId2;
        jc.setProfile(profile);
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
        mc.callManage(thisStage,thisScene,getProfile());
    }
    
    
    @FXML
    public void sentToSearch(){
        String text = searchfield.getText();
        System.out.println(text);
        sc.setProfile(profile);
        sc.callSearch(text);
        searchfield.setText("");
    }
  
    
    
    public void callMain(Stage stage,Scene scene,LoginModel prof) throws Exception {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("../view/FeedGui.fxml")); 
        Parent root = null;
        try{
            root = (Parent)loader.load();
            scene.setRoot(root);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        MainController controller = loader.<MainController>getController();
        if(prof.getStatus()==0){
            controller.manageBtn.setVisible(false);
            controller.managePic.setVisible(false);
        }
        sc.setProfile(prof);
        controller.getJc().setProfile(prof);
        controller.getSc().setProfile(prof);
        controller.setProfile(prof);
        controller.getEvent();
        controller.setUserData(controller.getProfile().getStdId(),controller.getProfile().getName());
        controller.getNc().addNewsToList(controller.getNewsList());
        controller.setThisStage(stage);
        controller.setThisScene(scene); 
    }
    
    @FXML
    public void logout(){
        Alert warning = null;
        warning = new Alert(Alert.AlertType.CONFIRMATION);
        warning.setTitle("Information!");
        warning.setHeaderText("ยืนยันที่จะออกจากระบบ"); 
        warning.setContentText("คุณแน่ใจที่จะออกจากระบบ ใช่หรือไม่?");
        Optional<ButtonType> result = warning.showAndWait();
        if(result.isPresent()){
            if(result.get() == ButtonType.OK){
                this.profile = null;
                fc.callFirstNews();
                getThisStage().close();
            }
        }
    }
   
}
