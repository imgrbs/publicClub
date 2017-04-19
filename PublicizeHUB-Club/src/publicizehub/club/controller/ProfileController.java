package publicizehub.club.controller;


/* Import Package จำเป็นของ JavaFX และ
Method ต่างๆ */
/* JavaFX */
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/* For Logic */
import static java.lang.Long.parseLong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import publicizehub.club.model.ConnectionBuilder;
import publicizehub.club.model.Event;

/**
 * FXML Controller class
 *
 * @author JIL
 */
public class ProfileController { // JavaFX บังคับ implement Method ของ JavaFX
    Event ev = new Event(); // Model Class ของ Event ( ดึงข้อมูล Event จาก DB )
    ConnectionBuilder cb = new ConnectionBuilder(); // Model Class สำหรับ Connect กับ DB
    
    /* Controller ของหน้า GUI อื่น */
    JoinController jc = new JoinController(); // Controller ของการจองกิจกรรม
    DetailController dc = new DetailController(); // Controller ของ GUI แสดงรายละเอียดกิจกรรม
    EventController ec = new EventController(); // Controller จัดการกิจกรรมที่ดึงมาจาก DB
    
    private long stdId; // ตัวแปรเก็บ Student ID
    
    private Stage mainStage; // เก็บ Stage ก่อนหน้าที่จะเรียก GUI Profile
    private Stage thisStage; // เก็บ Stage ของ GUI ปัจจุบัน
    
    /* ตัวแปรของ JavaFX ที่อิงกับไฟล์ .fxml จะต้องพิมพ์ @FXML กำกับเสมอ */
    @FXML
    private Label labelId;
    @FXML
    private Label labelName;
    @FXML
    private Label labelDepartment;
    @FXML
    private Label labelEvName;
    
    /* Layout VBox */
    /* เป็นกล่องสำหรับเก็บ Component แบบ Dynamic 
    โดยเมื่อมี Component ใหม่ VBox จะให้ต่อข้างล่างได้เลย */    
    @FXML
    private VBox listEventBox1 = new VBox(); // Box เก็บกิจกรรมที่ยังไม่จบ
    @FXML
    private VBox listEventBox2 = new VBox(); // Box เก็บกิจกรรมที่จบแล้ว
    
    @FXML
    private Button backBtn; // ปุ่มกดกลับหน้าหลัก
    
    /* Setter Getter */
    public void setLabelId(String labelId) {
        this.labelId.setText(labelId);
    }

    public void setLabelName(String labelName) {
        this.labelName.setText(labelName);
    }

    public void setLabelDepartment(String labelDepartment) {
        this.labelDepartment.setText(labelDepartment);
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }
    /* END Setter Getter */

    
    /* Method ของการ Get ค่า ID จาก Label */
    @FXML
    public void getEventToProfile(){
        System.out.println("Befor Get Event");
        /* ResultSet เก็บข้อมูลกิจกรรมที่ Get ผ่าน Model Class 
        โดยอิงจาก ID ที่อยู่ใน Label Student ID */
        ResultSet rs = ev.getSelect(parseLong(this.labelId.getText()));
        cb.logout(); // ปิด Connection เพราะ Connect ตอนใช้ getSelect
        System.out.println("After Get Event");
        /* ลองรับค่าดูก่อน ถ้ามีจึงให้ Loop เก็บต่อ 
        ถ้าค่ายังไม่มีแต่แรกจึงไม่ให้เข้า Loop */
        try{
            if(rs.next()){
                System.out.println("Event Come");
                setEventToGui(rs.getInt("evId"));
                while(rs.next()){
                    setEventToGui(rs.getInt("evId"));
                }
            }
        /* ดัก SQLException ไว้กันพลาดจะได้รู้ว่าผิดส่วนนี้รึเปล่า */
        }catch(SQLException e){
            e.printStackTrace();
        }
        cb.logout(); // ปิด Connection จาก setEventToGui
    }

    /* Method ที่แยกกิจกรรมที่จบแล้วกับยังไม่จบให้อยู่คนละ Box */
    public void setEventToGui(int eventId){
        ResultSet findStd = ev.getSelect(eventId);
        /* ResultSet นำ eventId ไปดึงข้อมูลปัจจุบัน */
        ec.setStdId(getStdId()); // ส่ง Student ID ให้ EventController
        /* ส่งเพราะต้องการหากิจกรรมที่เฉพาะ Student ID นี้ */
        try{
            /* เช็คว่ามีกิจกรรมไหม ถ้ามีให้เช็ควันว่าจบรึยัง ถ้าจบแล้วให้ใส่ Box ที่ 2 
            ถ้ายังไม่จบให้ใส่ Box 1 */
            /* โดยให้รองรับเข้ามาก่อน 1 ครั้ง ถ้ามีจะอนุญาติให้ใช้ Loop เพื่อดึงต่อ 
            โดยเมื่อ .next() แล้วมีจึงต้อง ทำการ Set ค่าทันที */
            if(findStd.next()){
                LocalDate ld = LocalDate.parse(""+findStd.getString("evEndDate")); // เวลาจบกิจกรรมนั้นๆ
                if(ld.compareTo(LocalDate.now())>-1){ 
            // เช็คกับ LocalDatee.now() คือเวลาปัจจุวัน ถ้ามากกว่า -1 คือ มากกว่าหรือเท่ากับ
            // เรียกใช้ EventController โดย จะสร้าง Component เป็นกล่องสี่เหลี่ยม 1 กล่อง 
            // ถ้าส่ง ใน Parameter สุดท้าย  True จะเป็นปุ่ม Join กับ Detail
            // ส่ง Event Name สำหรับ Label ชื่อ Event นั้นๆ และส่ง Event ID สำหรับใช้ Method อื่น
                    ec.addEventToPresentPane(findStd.getString("evName"),
                            findStd.getInt("evId"),this.listEventBox1,true,true); 
                }
                else {
                    // ถ้าเป็น ใน Parameter สุดท้าย false จะเป็นปุ่มประเมิณขึ้นมาแทน
                    ec.addEventToPresentPane(findStd.getString("evName"),
                            findStd.getInt("evId"),this.listEventBox2,false,true);
                    while(findStd.next()){
                        ld = LocalDate.parse(findStd.getString("evEndDate"));
                        if(ld.compareTo(LocalDate.now())>-1){
                            ec.addEventToPresentPane(findStd.getString("evName"),
                                    findStd.getInt("evId"),this.listEventBox1,true,true);
                        }
                        else {
                            ec.addEventToPresentPane(findStd.getString("evName"),
                                    findStd.getInt("evId"),this.listEventBox2,false,true);
                        }
                    }
                }
            }
        /* ดัก SQLException ไว้กันพลาดจะได้รู้ว่าผิดส่วนนี้รึเปล่า */
        }catch(SQLException e){
            e.printStackTrace();
        }
        cb.logout();
    }
    
    /* Method สำหรับ เปลี่ยน Stage */
    public void callMain(){
        System.out.println("callMain() WORK");
        mainStage.show(); // แสดง Stage หลัก ( Main )
        thisStage.close(); // ปิด Stage ปัจจุบัน ( Profile )
    }
    

    
}
