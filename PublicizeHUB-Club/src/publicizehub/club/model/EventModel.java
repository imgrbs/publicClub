package publicizehub.club.model;

import java.sql.*;
import java.text.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import publicizehub.club.controller.FormSumActivityController;
/**
 *
 * @author ImagineRabbits
 */
public class EventModel {
    
    private static final Logger LOGGER = Logger.getLogger( FormSumActivityController.class.getName() );
    private final ConnectionBuilder cb = new ConnectionBuilder();
    
    private long stdId;
    private String evName; 
    private String evDescrip;
    private LocalDate evDate;
    private LocalDate evEndDate;
    private LocalDate evStartRegis;
    private LocalDate evEndFeedback;
    private String evPlace;
    private int evTicket;
    private int currentMember;
    private LocalTime evTime;
    private LocalTime evEndTime;
    private int evType;
    private int evId;
    
    
    private SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    
    private PreparedStatement ps;
    private ResultSet rs;

    public EventModel() {
    }

    public EventModel(long stdId, String evName, String evDescrip, LocalDate evDate, LocalDate evEndDate, LocalDate evStartRegis,
            LocalDate evEndFeedback, String evPlace, int evTicket, LocalTime evTime, LocalTime evEndTime, int evType) {
        this.stdId = stdId;
        this.evName = evName;
        this.evDescrip = evDescrip;
        this.evDate = evDate;
        this.evEndDate = evEndDate;
        this.evStartRegis = evStartRegis;
        this.evEndFeedback = evEndFeedback;
        this.evPlace = evPlace;
        this.evTicket = evTicket;
        this.evTime = evTime;
        this.evEndTime = evEndTime;
        this.evType = evType;
    }
    
    public EventModel(String evName, String evDescrip, Date evDate, Date evEndDate, Date evStartRegis, 
                    Date evEndFeedback, String evPlace, int evTicket, 
                    Time evTime, Time evEndTime, int evType, int evId) {
        this.evName = evName;
        this.evDescrip = evDescrip;
        this.evDate = evDate.toLocalDate();
        this.evEndDate = evEndDate.toLocalDate();
        this.evStartRegis = evStartRegis.toLocalDate();
        this.evEndFeedback = evEndFeedback.toLocalDate();
        this.evTime = evTime.toLocalTime();
        this.evEndTime = evEndTime.toLocalTime();
        this.evPlace = evPlace;
        this.evTicket = evTicket;
        this.evType = evType;
        this.evId = evId;
    }
    
    public EventModel(String evName, String evDescrip, Date evDate, Date evEndDate, Date evStartRegis, 
                    Date evEndFeedback, String evPlace, int evTicket,int currentMember,
                    Time evTime, Time evEndTime, int evType, int evId) {
        this.evName = evName;
        this.evDescrip = evDescrip;
        this.evDate = evDate.toLocalDate();
        this.evEndDate = evEndDate.toLocalDate();
        this.evStartRegis = evStartRegis.toLocalDate();
        this.evEndFeedback = evEndFeedback.toLocalDate();
        this.evTime = evTime.toLocalTime();
        this.evEndTime = evEndTime.toLocalTime();
        this.currentMember = currentMember;
        this.evPlace = evPlace;
        this.evTicket = evTicket;
        this.evType = evType;
        this.evId = evId;
    }
    public EventModel(String evName, String evDescrip, LocalDate evDate, LocalDate evEndDate, LocalDate evStartRegis, 
                    LocalDate evEndFeedback, String evPlace, int evTicket, 
                    LocalTime evTime, LocalTime evEndTime, int evType, int evId) {
        this.evName = evName;
        this.evDescrip = evDescrip;
        this.evDate = evDate;
        this.evEndDate = evEndDate;
        this.evStartRegis = evStartRegis;
        this.evEndFeedback = evEndFeedback;
        this.evTime = evTime;
        this.evEndTime = evEndTime;
        this.evPlace = evPlace;
        this.evTicket = evTicket;
        this.evType = evType;
        this.evId = evId;
    }

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }
    
    public String getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName = evName;
    }

    public String getEvDescrip() {
        return evDescrip;
    }

    public void setEvDescrip(String evDescrip) {
        this.evDescrip = evDescrip;
    }

    public LocalDate getEvDate() {
        return evDate;
    }

    public void setEvDate(LocalDate evDate) {
        this.evDate = evDate;
    }

    public LocalDate getEvEndDate() {
        return evEndDate;
    }

    public void setEvEndDate(LocalDate evEndDate) {
        this.evEndDate = evEndDate;
    }

    public LocalDate getEvStartRegis() {
        return evStartRegis;
    }

    public void setEvStartRegis(LocalDate evStartRegis) {
        this.evStartRegis = evStartRegis;
    }

    public LocalDate getEvEndFeedback() {
        return evEndFeedback;
    }

    public void setEvEndFeedback(LocalDate evEndFeedback) {
        this.evEndFeedback = evEndFeedback;
    }

    public String getEvPlace() {
        return evPlace;
    }

    public void setEvPlace(String evPlace) {
        this.evPlace = evPlace;
    }

    public int getEvTicket() {
        return evTicket;
    }

    public void setEvTicket(int evTicket) {
        this.evTicket = evTicket;
    }

    public int getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(int currentMember) {
        this.currentMember = currentMember;
    }

    public LocalTime getEvTime() {
        return evTime;
    }

    public void setEvTime(LocalTime evTime) {
        this.evTime = evTime;
    }

    public LocalTime getEvEndTime() {
        return evEndTime;
    }

    public void setEvEndTime(LocalTime evEndTime) {
        this.evEndTime = evEndTime;
    }

    public int getEvType() {
        return evType;
    }

    public void setEvType(int evType) {
        this.evType = evType;
    }

    public int getEvId() {
        return evId;
    }

    public void setEvId(int evId) {
        this.evId = evId;
    }
    
    public void createEvent(EventModel thisEvent) {
        cb.connecting();
        Statement s = null;
        try {
            s = cb.getConnect().createStatement();
            String sql = "INSERT INTO tb_event"
                    + "(evName,evDescrip,evStartDate,evEndDate,evStartRegis,evEndFeedback,evTime,evEndTime,evPlace,evTicket,evType,stdId) "
                    + "VALUES ('"  + thisEvent.getEvName() + "','" + thisEvent.getEvDescrip() + "','"+ thisEvent.getEvDate() + "','"+ thisEvent.getEvEndDate() + "','" +thisEvent.getEvStartRegis() + "','"+thisEvent.getEvEndFeedback() + "','" + thisEvent.getEvTime() + "','"
                    + thisEvent.getEvEndTime() + "','" + thisEvent.getEvPlace() + "','" + thisEvent.getEvTicket() + "','" + thisEvent.getEvType() + "','" + thisEvent.getStdId() + "') ";
            s.executeUpdate(sql);
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"createEvent : SQLException Bug !");
        }
        catch (Exception e) {
            LOGGER.log(Level.SEVERE ,"createEvent : Exception Bug !");
        }
        cb.logout();
    }
    
    public void updateEvent(EventModel event){
        cb.connecting();
        String command;
        try {
            command = "UPDATE tb_event set evName = '"+event.getEvName()+"' , "
                    + "evDescrip = '"+event.getEvDescrip()+"' , evStartDate = '"+event.getEvDate()+"' , "
                    + "evEndDate = '"+event.getEvEndDate()+"' , evStartRegis = '"+event.getEvStartRegis()+"' , "
                    +"evTime = '"+event.getEvTime()+"' , evEndFeedback = '"+event.getEvEndFeedback()+"' , "
                    + "evEndTime = '"+event.getEvEndTime()+"' , evPlace = '"+event.getEvPlace()+"' , "
                    + "evTicket = '"+event.getEvTicket()+"' , evType = '"+event.getEvType()+"' "
                    + "where evId = "+event.getEvId();
            ps = cb.getConnect().prepareStatement(command); 
            ps.executeUpdate();
        }
        catch(SQLException ex){
            LOGGER.log(Level.SEVERE ,"updateEvent : SQLException Bug !");
        }catch(Exception e){
            LOGGER.log(Level.SEVERE ,"updateEvent : Exception Bug !");
        }
        cb.logout();
    }
    
    public void deleteEvent(int deleteId){
        String command;
        PreparedStatement s;
        cb.connecting();
        try{
            command ="DELETE FROM tb_event WHERE evId = ?";
            s = cb.getConnect().prepareStatement(command);
            s.setInt(1,deleteId);
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
    
    public ResultSet getEvent(){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_event");
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"getEvent : SQLException Bug !");
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"getEvent : Exception Bug !");
        }
        return rs;
    }
    public ResultSet getEventType(){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT * FROM eventType");
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"getEvent : SQLException Bug !");
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"getEvent : Exception Bug !");
        }
        return rs;
    }
    public ResultSet getEventType(int evType){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT * FROM eventType where typeValue=?");
            ps.setInt(1,evType);
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"getEvent : SQLException Bug !");
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"getEvent : Exception Bug !");
        }
        return rs;
    }
    public ResultSet getEventType(String evType){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT * FROM eventType where typeName=?");
            ps.setString(1,evType);
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"getEvent : SQLException Bug !");
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"getEvent : Exception Bug !");
        }
        return rs;
    }
    
    public ResultSet getSelect(int evId){
        ResultSet result = null;
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_event where evId = ?");
            ps.setInt(1,evId);
            result = ps.executeQuery();
        }
        catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"getSelect int : SQLException Bug !");
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"getSelect int : Exception Bug !");
        }
        return result;
    }
    
    public ResultSet getSelect(long stdId){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT * FROM generatecode where stdId = ?");
            ps.setLong(1,stdId);
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"getSelect long : SQLException Bug !");
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"getSelect long : Exception Bug !");
        }
        return rs;
    }
    
    public ResultSet getEventByStdId(long stdId){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_event where stdId = ?");
            ps.setLong(1,stdId);
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"getSelect long : SQLException Bug !");
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"getSelect long : Exception Bug !");
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
            LOGGER.log(Level.SEVERE ,"updateCurrentMember long : SQLException Bug !");
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"updateCurrentMember long : Exception Bug !");
        }
        return rs;
    }
    
     //เอาจำนวนคนที่กด join event ไปโชว์ตอนแสดงรายชื่อ ex. จำนวนคนที่จอง 10/x คน
    public int JoinTicket(int evId){
        PreparedStatement ps = null;
        int numJoinTicket = 0;
        cb.connecting();
        ResultSet result;
        try {
            ps = cb.getConnect().prepareStatement("SELECT COUNT(*) AS total FROM generatecode where evId = ?");//คำสั่งดูจำนวน row ทั้งหมด (จำนวนคน)
            ps.setInt(1, evId); 
            result = ps.executeQuery();
            if(result.next()) numJoinTicket = result.getInt("total");

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"JoinTicket : JoinTicket Failed");
        }
//        cb.logout();        
        return numJoinTicket;
    } 
    
    //เอาจำนวน ticket ไปโชว์ตอนแสดงรายชื่อ ex. จำนวนคนที่จอง 10/x คน
    public ResultSet getTicket(int evId){
        PreparedStatement ps = null;
        cb.connecting();
        ResultSet result;
        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_event where evId = ?");
            ps.setInt(1, evId); 
            rs = ps.executeQuery();
            

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE ,"getTicket : getTicket Failed");
        }
        
    
        return rs;
    } 
}
