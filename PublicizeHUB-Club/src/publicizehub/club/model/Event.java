package publicizehub.club.model;

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
    
    Date date;
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    
    PreparedStatement ps;
    ResultSet rs;

    public Event() {
    }

//    public Event(int evId, Date evDate, Date evEndDate, Time evTime, Time evEndTime, 
//            String evPlace, int evTicket, int eventType, long stdId) {
//        this.evId = evId;
//        this.evDate = evDate;
//        this.evEndDate = evEndDate;
//        this.evTime = evTime;
//        this.evEndTime = evEndTime;
//        this.evPlace = evPlace;
//        this.evTicket = evTicket;
//        this.eventType = eventType;
//        this.stdId = stdId;
//    }
    
    
    
    public void createEvent(String name,String desc,String date,String endDate,
                        String time,String endTime,String place,int ticket,
                        int evType,long stdId) {
        cb.connecting();
        Statement s = null;
       
        try {
            s = cb.getConnect().createStatement();
            // SQL Insert
            String sql = "INSERT INTO tb_event"
                    + "(evName,evDescrip,evDate,evEndDate,evTime,evEndTime,evPlace,evTicket,evType,stdId) "
                    + "VALUES ('" 
                    + name + "','"
                    + desc + "','"
                    + date + "','"
                    + endDate + "','"
                    + time + "','"
                    + endTime + "','"
                    + place + "','"
                    + ticket + "','"
                    + evType + "','"
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
    
    public void updateEvent(String name,String desc,String date,
                            String endDate,String time,String endTime,
                            String place,int ticket,int evType,int evId){
        // update
        System.out.println(this.evId);
        PreparedStatement ps = null;    
        cb.connecting();
        String command;
        try {
            command = "UPDATE tb_event set evName = '"+name+"' , "
                    + "evDescrip = '"+desc+"' , evDate = '"+date+"' , "
                    + "evEndDate = '"+endDate+"' , evTime = '"+time+"' , "
                    + "evEndTime = '"+endTime+"' , evPlace = '"+place+"' , "
                    + "evTicket = '"+ticket+"' , evType = '"+evType+"' "
                    + "where evId = "+evId;
            ps = cb.getConnect().prepareStatement(command); 
            System.out.println("Before Update");
            ps.executeUpdate();
            System.out.println("Success Update");
        }
        catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL ERROR at updateEvent()");
        }
        cb.logout();
    }
    
    public void DeleteEvent(int deleteId){
        System.out.println("Call deleteEv");
        String command;
        PreparedStatement s;
        cb.connecting();
        try{
            command ="DELETE FROM tb_event WHERE evId = ?";
            System.out.println("DelID "+deleteId);
            System.out.println(command);
            s = cb.getConnect().prepareStatement(command);
            s.setInt(1,deleteId);
            
            s.executeUpdate();
            System.out.println("Delete Success");
        }
        catch(NullPointerException e){
            e.printStackTrace();
            System.out.println("NullPointerException");
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLException! - event");
        }
        catch(Exception e){
            e.printStackTrace();
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
    
    public ResultSet getSelect(int evId){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_event where evId = ?");
            ps.setInt(1,evId);
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLException at getSelect()");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception at getSelect()");
        }
        return rs;
    }
}
