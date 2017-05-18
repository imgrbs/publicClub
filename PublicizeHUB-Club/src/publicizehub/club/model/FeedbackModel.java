package publicizehub.club.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author budsagorn_ss
 */
public class FeedbackModel {
    private static final Logger LOGGER = Logger.getLogger( GenerateCode.class.getName() );
    private ConnectionBuilder cb = new ConnectionBuilder();
    
    public void insertValue(int evId, long stdId, int valueRadio1, int valueRadio2, int valueRadio3,
            int valueRadio4, int valueRadio5, int valueRadio6, int valueRadio7, int valueRadio8,
            int valueRadio9, int valueRadio10) {
        Statement s = null;
        String sql;
        cb.connecting();
        try {
            s = cb.getConnect().createStatement();
            sql = "INSERT INTO std_feedback (evId,stdId,sumQ1,sumQ2,sumQ3,sumQ4,sumQ5,sumQ6,sumQ7,sumQ8,sumQ9,sumQ10) "
                    + "VALUES ('" + evId + "','" + stdId + "',"
                    + "'" + valueRadio1 + "','" + valueRadio2 + "','" + valueRadio3 + "','" + valueRadio4 + "','"
                    + valueRadio5 + "','" + valueRadio6 + "','" + valueRadio7 + "','" + valueRadio8 + "','"
                    + valueRadio9 + "','" + valueRadio10 + "') ";

            s.executeUpdate(sql);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"insertValue : insertValue Failed");
        }
        cb.logout();
    }

    
    public ResultSet getSumQ(int evId) {
        PreparedStatement ps = null;
        ResultSet result = null;
        cb.connecting();

        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM std_feedback where evId = ?");
            ps.setInt(1, evId); 
            result = ps.executeQuery();
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"getSumQ : getSumQ Failed");
        }

        return result;

    }

    public int numPeople(int evId) {
        PreparedStatement ps = null;
        int numPeople = 1;
        cb.connecting();
        ResultSet result;
        try {
            ps = cb.getConnect().prepareStatement("SELECT COUNT(*) AS total FROM std_feedback where evId = ?");//คำสั่งดูจำนวน row ทั้งหมด (จำนวนคน)
            ps.setInt(1, evId); 
            result = ps.executeQuery();
            if(result.next()) numPeople = result.getInt("total");

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"numPeople : numPeople Failed");
        }

        cb.logout();
        return numPeople;
    }

    public void insertAvgrValue(int evId, int numPeple, int averQ1, int averQ2, int averQ3,
            int averQ4, int averQ5, int averQ6, int averQ7, int averQ8, int averQ9, int averQ10, int setSumQ1, int setSumQ2) {
        Statement s;
        String sql;
        cb.connecting();
        try {
            s = cb.getConnect().createStatement();
            sql = "INSERT INTO tb_feedback (evId,stdEstimated,sumQ1,sumQ2,sumQ3,sumQ4,sumQ5,sumQ6,sumQ7,sumQ8,sumQ9,sumQ10,setSumQ1,setSumQ2) "
                    + "VALUES ('" + evId + "','" + numPeple + "'," + "'" + averQ1 + "','" + averQ2 + "','" + averQ3 + "','"
                    + averQ4 + "','" + averQ5 + "','" + averQ6 + "','" + averQ7 + "','" + averQ8 + "','" + averQ9
                    + "','" + averQ10 + "','" + setSumQ1 + "','" + setSumQ2 + "') ";

            s.executeUpdate(sql);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"insertAvgrValue : insertAvgrValue Failed");
        }
        cb.logout();
    }

    public ResultSet selectValueFeedback(int eventId) {
        PreparedStatement ps;
        ResultSet result = null;
        cb.connecting();
        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_feedback where evId = ?");
            ps.setInt(1, eventId);
            result = ps.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"selectValueFeedback : selectValueFeedback Failed");
        }
        return result;
    }

    public void insertToLog(int eventId, long stdId) {
        ResultSet log = null;
        cb.connecting();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            PreparedStatement ps = cb.getConnect().prepareStatement("INSERT into logFeedback (evId,stdId,datestamp,timestamp) VALUES('" + eventId + "','"
                    + +stdId + "','" + LocalDate.now() + "','" + timeFormat.format(LocalTime.now()) + "')");
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"insertToLog : insertToLog Failed");
        }
    }

    public ResultSet getFormLog(int eventId, long stdId) {
        ResultSet log = null;
        cb.connecting();
        try {
            PreparedStatement ps = cb.getConnect().prepareStatement("SELECT * FROM logFeedback where evId = ? and stdId = ?");
            ps.setInt(1, eventId);
            ps.setLong(2, stdId);
            log = ps.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"getFormLog : getFormLog Failed");
        }
        return log;
    }

    public int getStdBuy(int evId){
        PreparedStatement ps;
        int numPeople = 1;
        cb.connecting();
        ResultSet result;
        try {
            ps = cb.getConnect().prepareStatement("SELECT COUNT(*) AS total FROM logJoining where evId = ?");
            ps.setInt(1, evId); 
            result = ps.executeQuery();
            if(result.next()) numPeople = result.getInt("total");

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"getStdBuy : getStdBuy Failed");
        }
        cb.logout();
        return numPeople;
    }
    
    public int getStdJoin(int evId){
        PreparedStatement ps;
        ResultSet result;
        int numPeople = 1;
        cb.connecting();
        try {
            ps = cb.getConnect().prepareStatement("SELECT COUNT(*) AS total FROM logJoining where evId = ? and statusCheckIn = ?");
            ps.setInt(1, evId); 
            ps.setInt(2, 1);
            result = ps.executeQuery();
            if(result.next()) numPeople = result.getInt("total");

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"getStdJoin : getStdJoin Failed");
        }
        cb.logout();
        return numPeople;
    }
    
    public ResultSet getStdFormLog(int evId){
        ResultSet rs = null;
        PreparedStatement ps;
        cb.connecting();
        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM logJoining where evId = ?");
            ps.setInt(1, evId);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"getStdFormLog : getStdFormLog Failed");
        }
        return rs;
    }
    
    public boolean checkStdEva(int eventId,long stdId){
        boolean check = true;
        String sql = "SELECT statusCheckIn FROM logJoining where stdId = ? and evId = ?";
        cb.connecting();
        try{
            PreparedStatement ps = cb.getConnect().prepareStatement(sql);
            ps.setLong(1, stdId);
            ps.setInt(2, eventId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getInt("statusCheckIn")==1){
                    check = false;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        cb.logout();
        return check;
    }
    
}
