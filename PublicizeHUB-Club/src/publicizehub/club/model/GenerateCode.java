package publicizehub.club.model;

import static java.lang.Integer.parseInt;
import java.sql.*;

/**
 *
 * @author ImagineRabbits
 */
public class GenerateCode {

    ConnectionBuilder cb = new ConnectionBuilder();
    Event ev = new Event();
    private long rand;
    private String timestamp = "";
    private int timerand;
    private int Code;

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
        rand = (long) (Math.random() * 7999) + 1000;
        timestamp += System.currentTimeMillis();
        timerand = parseInt(timestamp.substring(timestamp.length() - 5, timestamp.length()));
        Code = (int) (timerand + rand);
        System.out.println("C" + Code);
        evCode = "C" + Code;
    }

    public void pushCode(int eventId) {
        generateCode();
        ResultSet checkMember = ev.getSelect(eventId);
        cb.logout();
        try {
            if(checkMember.next()){
                if (checkMember.getInt("currentMember") < checkMember.getInt("evTicket")) {
                    int updateMember = checkMember.getInt("currentMember")+1;
                    ev.updateCurrentMember(updateMember, eventId);
                    cb.connecting();
                    PreparedStatement ps;
                    String sql = "INSERT INTO generatecode"
                            + "(evId,stdId,evCode) "
                            + "VALUES ('"
                            + this.evId + "','"
                            + this.stdId + "','"
                            + this.evCode + "') ";
                    try {
                        ps = cb.getConnect().prepareStatement(sql);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        cb.logout();
    }
    
}
