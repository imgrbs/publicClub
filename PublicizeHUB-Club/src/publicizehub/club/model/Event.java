package publicizehub.club.model;

import java.util.Date;
import java.sql.*;
import java.text.*;
import javax.swing.*;
/**
 *
 * @author ImagineRabbits
 */
public class Event {
    ConnectionBuilder cb = new ConnectionBuilder();
    
    private int evId;
    private String evName="";
    private String evDescrip="";
    private Date evDate;
    private Date evEndDate;
    private Time evTime;
    private Time evEndTime;
    private String evPlace;
    private int evTicket;
    private int eventType;
    private long stdId;
    
    Date date = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    
    PreparedStatement ps;
    ResultSet rs;

    public Event() {
    }

    public Event(int evId, Date evDate, Date evEndDate, Time evTime, Time evEndTime, 
            String evPlace, int evTicket, int eventType, long stdId) {
        this.evId = evId;
        this.evDate = evDate;
        this.evEndDate = evEndDate;
        this.evTime = evTime;
        this.evEndTime = evEndTime;
        this.evPlace = evPlace;
        this.evTicket = evTicket;
        this.eventType = eventType;
        this.stdId = stdId;
    }
    
    
    
    public void newEvent() {
        cb.connecting();
        Statement s = null;
       
        try {
            s = cb.getConnect().createStatement();
            // SQL Insert
            String sql = "INSERT INTO tb_event"
                    + "(evName,evDescrip,evDate,evEndDate,evTime,evEndTime,evPlace,evTicket,evType,stuId) "
                    + "VALUES ('" 
                    + evName + "','"
                    + evDescrip + "','"
                    + evDate + "','"
                    + evEndDate + "','"
                    + evTime + "','"
                    + evEndTime + "','"
                    + evPlace + "','"
                    + evTicket + "','"
                    + eventType + "','"
                    + stdId + "') ";
            s.executeUpdate(sql);
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }

        cb.logout();
    }
    
    public void EditEvent(){
     // update
    }
    
    public void DeleteEvent(int delId){
        System.out.println("Call deleteEv");
        String command;
        PreparedStatement s;
        try{
            command ="DELETE FROM tb_event WHERE evId = ?";
            System.out.println("DelID"+delId);
            System.out.println(command);
            s = cb.getConnect().prepareStatement(command);
            s.setInt(1,delId);
            s.executeUpdate();
            System.out.println("Delete Success");
        }
        catch(SQLException e){
//            e.printStackTrace();
            System.out.println("SQLException! - event");
        }
        catch(Exception e){
//            e.printStackTrace();
            System.out.println("EXCEPTION - event");
        }
        finally{
            cb.logout();
        }
    }
    
    public ResultSet getEvent(){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_event");
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQL ผิดพลาด");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("getEvent() Exception!");
        }
        return rs;
    }

}
