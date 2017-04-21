package publicizehub.club.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author budsagorn_ss
 */
public class Person extends RecursiveTreeObject<Person> {
    private static final Logger LOGGER = Logger.getLogger( GenerateCode.class.getName() );
    private ConnectionBuilder cb = new ConnectionBuilder();
    
    private StringProperty stdId;
    private StringProperty stdName;
    private StringProperty stdSurname;
    private StringProperty department;
    private StringProperty statusEvaluation;
    private StringProperty statusCheckIn;
    private StringProperty dateBuyTicket;
    private StringProperty timestamp;

    public Person() {
    }

    public Person(String stdId, String stdName, String stdSurname, String department, 
        int statusEvaluation, int statusCheckIn, Date dateBuyTicket, Time timestamp) {
        this.stdId = new SimpleStringProperty(stdId);
        this.stdName =  new SimpleStringProperty(stdName);
        this.stdSurname = new SimpleStringProperty(stdSurname);
        this.department = new SimpleStringProperty(department);
        if(statusEvaluation==0){
            this.statusEvaluation = new SimpleStringProperty("ยังไม่ประเมิณ");
        }else{
            this.statusEvaluation = new SimpleStringProperty("ประเมิณแล้ว");
        }
        if(statusEvaluation==0){
            this.statusCheckIn = new SimpleStringProperty("ไม่เข้าร่วม");
        }else{
            this.statusCheckIn = new SimpleStringProperty("เข้าร่วม");
        }
        this.dateBuyTicket = new SimpleStringProperty(""+dateBuyTicket);
        this.timestamp = new SimpleStringProperty(""+timestamp);
    }

    public ConnectionBuilder getCb() {
        return cb;
    }

    public void setCb(ConnectionBuilder cb) {
        this.cb = cb;
    }

    public StringProperty getStdId() {
        return stdId;
    }

    public void setStdId(StringProperty stdId) {
        this.stdId = stdId;
    }

    public StringProperty getStdName() {
        return stdName;
    }

    public void setStdName(StringProperty stdName) {
        this.stdName = stdName;
    }

    public StringProperty getStdSurname() {
        return stdSurname;
    }

    public void setStdSurname(StringProperty stdSurname) {
        this.stdSurname = stdSurname;
    }

    public StringProperty getDepartment() {
        return department;
    }

    public void setDepartment(StringProperty department) {
        this.department = department;
    }

    public StringProperty getStatusEvaluation() {
        return statusEvaluation;
    }

    public void setStatusEvaluation(StringProperty statusEvaluation) {
        this.statusEvaluation = statusEvaluation;
    }

    public StringProperty getStatusCheckIn() {
        return statusCheckIn;
    }

    public void setStatusCheckIn(StringProperty statusCheckIn) {
        this.statusCheckIn = statusCheckIn;
    }

    public StringProperty getDateBuyTicket() {
        return dateBuyTicket;
    }

    public void setDateBuyTicket(StringProperty dateBuyTicket) {
        this.dateBuyTicket = dateBuyTicket;
    }

    public StringProperty getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(StringProperty timestamp) {
        this.timestamp = timestamp;
    }
    
    public ResultSet getProfile(long stdId){
        ResultSet rs = null;
        PreparedStatement ps;
        cb.connecting();
        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_profile where stdId = ?");
            ps.setLong(1, stdId);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"getProfile : getProfile Failed");
        }
        return rs;
    }
}
