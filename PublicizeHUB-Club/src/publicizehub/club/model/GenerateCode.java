package publicizehub.club.model;

import static java.lang.Integer.parseInt;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ImagineRabbits
 */
public class GenerateCode {

    ConnectionBuilder cb = new ConnectionBuilder();
    Event ev = new Event();
    private int rand;
    private int randString;
    private String timestamp = "";
    private int timerand;
    private int Code;
    private String codeString ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private long stdId = 59130500012l;
    private int evId = 10051;
    private String evCode = "";

    public String getEvCode() {
        return evCode;
    }

    public GenerateCode() {
    }

    public GenerateCode(long stdId, int evId) {
        this.stdId = stdId;
        this.evId = evId;
    }

    public int getCode() {
        return Code;
    }

    public void generateCode() {
        rand = (int) (Math.random() * 7999) + 1000;
      	randString = (int) (Math.random() * codeString.length());
        timestamp += System.currentTimeMillis();
        timerand = parseInt(timestamp.substring(timestamp.length() - 5, timestamp.length()));
        Code = (int) (timerand + rand);
        System.out.println(codeString.charAt(randString)+"" + Code);
        evCode = "" + codeString.charAt(randString) + Code;
        if(evCode.length()<6){
            int fixCode = (int) (Math.random()*10);
            evCode += fixCode;
        }else if(evCode.length()>6){
            evCode = evCode.substring(0,7);
        }
        
    }

    public void pushCode(int eventId) {
        generateCode();
        ResultSet checkMember = ev.getSelect(eventId);
        cb.logout();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            if(checkMember.next()){
                if (checkMember.getInt("currentMember") < checkMember.getInt("evTicket")) {
                    int updateMember = checkMember.getInt("currentMember")+1;
                    ev.updateCurrentMember(updateMember, eventId);
                    cb.connecting();
                    PreparedStatement ps;
                    String sql = "INSERT INTO generatecode"
                            + "(evId,stdId,evCode,datestamp,timestamp) "
                            + "VALUES ('"
                            + this.evId + "','"
                            + this.stdId + "','"
                            + this.evCode + "','"
                            + LocalDate .now() + "','"
                            + timeFormat.format(LocalTime.now()) + "') ";
                    try {
                        ps = cb.getConnect().prepareStatement(sql);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ps = cb.getConnect().prepareStatement("INSERT into logJoining (stdId,evId,evCode,status,dateBuyTicket,timestamp) "
                                                  + "VALUES ('"+this.stdId+"','"+
                                                    this.evId+"','"+
                                                    this.evCode+"','"+
                                                    0+"','"+
                                                    LocalDate.now()+"','"+
                                                    timeFormat.format(LocalTime.now())+"')");
                    ps.executeUpdate();
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        cb.logout();
    }
    
}
