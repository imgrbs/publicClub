package publicizehub.club.model;

import java.sql.*;
import java.text.*;
import java.time.LocalDate;
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
    private LocalDate evDate;
    private LocalDate evEndDate;
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
    
    
    /* รับค่าที่ประมวลผลแล้วจาก Controller เพื่อ ส่งไป Database
    โดยใช้คำสั่ง SQL และ ประมวลผลคำสั่งโดย 
    การสร้าง Statement และเรียกใช้ method */
    public void createEvent(String name,String desc,LocalDate date,LocalDate endDate,
                        String time,String endTime,String place,int ticket,
                        int evType,long stdId) {
        cb.connecting(); // Connect ไป Database ผ่าน Connection Builder
        Statement s = null; // สร้างตัวแปร Statement
        try {
            s = cb.getConnect().createStatement();  // สร้าง Statement
            // SQL Insert (คำสัง SQL)
            String sql = "INSERT INTO tb_event"
                    + "(evName,evDescrip,evDate,evEndDate,evTime,evEndTime,evPlace,evTicket,evType,stdId) "
                    + "VALUES ('"  + name + "','" + desc + "','"+ date + "','"+ endDate + "','" + time + "','"
                    + endTime + "','" + place + "','" + ticket + "','" + evType + "','" + stdId + "') ";
            s.executeUpdate(sql); // ส่งข้อมูลไป Database
        } 
        /* ดัก SQLException และ Exception ปกติ */
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
        cb.logout(); // ปิดการ connection กับ database
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
    
    public ResultSet getSelect(long stdId){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT * FROM generatecode where stdId = ?");
            ps.setLong(1,stdId);
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
    
    public ResultSet updateCurrentMember(int updateMember,int evId){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("UPDATE tb_event set currentMember='"+updateMember+"' where evId = "+evId);
            ps.executeUpdate();
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
