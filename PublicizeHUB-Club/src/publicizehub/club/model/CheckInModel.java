/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author JIL
 */
public class CheckInModel {
    private static final Logger LOGGER = Logger.getLogger( GenerateCode.class.getName() );
    ConnectionBuilder cb = new ConnectionBuilder();
    private long stdId;
    private int evId;
    private String evCode;
    private LocalDate dateCheckIn;
    private LocalTime timeCheckIn;
    private PreparedStatement ps;
    private ResultSet rs;

    public CheckInModel() {
    }
    
    
    public CheckInModel(long stdId, int evId, String evCode, LocalDate dateCheckIn, LocalTime timeCheckIn) {
        this.stdId = stdId;
        this.evId = evId;
        this.evCode = evCode;
        this.dateCheckIn = dateCheckIn;
        this.timeCheckIn = timeCheckIn;
    }

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }

    public int getEvId() {
        return evId;
    }

    public void setEvId(int evId) {
        this.evId = evId;
    }

    public String getEvCode() {
        return evCode;
    }

    public void setEvCode(String evCode) {
        this.evCode = evCode;
    }

    public LocalDate getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(LocalDate dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public LocalTime getTimeCheckIn() {
        return timeCheckIn;
    }

    public void setTimeCheckIn(LocalTime timeCheckIn) {
        this.timeCheckIn = timeCheckIn;
    }
    
    
    public ResultSet getSelect(String evCode){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT * FROM generatecode where evCode = ?");
            ps.setString(1,evCode);
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"getSelect long : SQLException Bug !");
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"getSelect long : SQLException Bug !");
        }
        return rs;
    }
    
    public ResultSet getName(long stdId){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT * FROM students where std_id = ?");
            ps.setLong(1,stdId);
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"getName long : SQLException Bug !");
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"getName long : SQLException Bug !");
        }
        return rs;
    }
    
    public void sentLogCheckin(CheckInModel check){
        cb.connecting();
        Statement s = null;
        try {
            s = cb.getConnect().createStatement();
            String sql = "INSERT INTO logCheckIn"
                    + "(stdId,evId,evCode,dateCheckin,timestamp) "
                    + "VALUES ('"  + check.getStdId() + "','" + check.getEvId() + "','"+ check.getEvCode() + "','"+ check.getDateCheckIn() + "','" +check.getTimeCheckIn() + "') ";
            s.executeUpdate(sql);
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"keepDataCheckIn long : SQLException Bug !");
        }
        catch (Exception e) {
            LOGGER.log(Level.SEVERE ,"keepDataCheckIn long : SQLException Bug !");
        }
        cb.logout();
    }
    public void updateStatusCheckIn(String evCode,long stdId,int evId){
        cb.connecting();
        String command;
        System.out.println("evCode : "+evCode+"stdId : "+stdId+"evId : "+evId);
        try {
            command = "UPDATE logJoining set statusCheckIn = '1' where stdId = '"+stdId+"' and evId='"+evId+"' and evCode='"+evCode+"'";
            ps = cb.getConnect().prepareStatement(command); 
            ps.executeUpdate();
        }
        catch(SQLException ex){
            
        }catch(Exception e){
            
        }
        cb.logout();
    }
    
    public void deleteCode(String evCode){
        String command;
        PreparedStatement s;
        cb.connecting();
        try{
            command ="DELETE FROM generatecode WHERE evCode = ?";
            s = cb.getConnect().prepareStatement(command);
            s.setString(1,evCode);
            s.executeUpdate();
        }
        catch(NullPointerException e){
            LOGGER.log(Level.SEVERE ,"DeleteEvent : NullPointerException Bug !");
        }
        catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"DeleteEvent : SQLException Bug !");
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"DeleteEvent : Exception Bug !");
        }
        finally{
            cb.logout();
        }
    }
    public ResultSet getChecedName(int evId){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT * FROM logCheckIn where evId = ?");
            ps.setInt(1,evId);
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"getName long : SQLException Bug !");
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"getName long : SQLException Bug !");
        }
        return rs;
    }
    
}
