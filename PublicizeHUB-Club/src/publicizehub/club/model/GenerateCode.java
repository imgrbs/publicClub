package publicizehub.club.model;

import static java.lang.Integer.parseInt;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ImagineRabbits
 */
public class GenerateCode {
    private static final Logger LOGGER = Logger.getLogger( GenerateCode.class.getName() );
    ConnectionBuilder cb = new ConnectionBuilder();
    EventModel ev = new EventModel();
    private String codeString ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private long stdId;
    private int evId;
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


    public void generateCode() {
        Random r = new Random();
        int rand;
        int randString;
        int timerand;
        String timestamp = "";
        int Code;
        rand = r.nextInt(99999);
      	randString =  r.nextInt(codeString.length()-1);
        timestamp += Long.toString(System.currentTimeMillis());
        timerand = parseInt(timestamp.substring(timestamp.length() - 5, timestamp.length()));
        Code = (int) (timerand + rand);
        evCode = "" + Character.toString(codeString.charAt(randString)) + Code;
        if(evCode.length()<6){
            int fixCode = r.nextInt(9);
            evCode += Long.toString(fixCode);
        }
        if(evCode.length()>6){
            evCode = evCode.substring(0,6);
        }
        
    }

    public void pushCode(int eventId) {
        generateCode();
        ResultSet checkMember = ev.getSelect(eventId);
        cb.logout();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            if(checkMember.next()){
                int member = checkMember.getInt("currentMember");
                int chckTicket = checkMember.getInt("evTicket");
                if ( member < chckTicket) {
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
                    ps = cb.getConnect().prepareStatement(sql);
                    ps.executeUpdate();
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
            LOGGER.log(Level.SEVERE ,"pushCode : pushCode Bug !");
        }
        cb.logout();
    }
    
}
