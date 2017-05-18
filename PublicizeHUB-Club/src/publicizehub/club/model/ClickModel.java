package publicizehub.club.model;

import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author ImagineRabbits
 */
public class ClickModel {
    ConnectionBuilder cb = new ConnectionBuilder();
    
    

    public ClickModel() {
    }
    
    public void addRecord(int eventId,LocalDate startRegis,LocalDate EndDate){
        PreparedStatement ps = null;
        String sql = "INSERT INTO logClick (evId,numClick,evStartRegis,evEndDate) "
                + "VALUES('"+eventId+"','"+1+"','"+startRegis+"','"+EndDate+"')";
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement(sql); 
            ps.executeUpdate();
        }catch(SQLException e){
            
        }
        cb.logout();
    }
    
    public ResultSet getClick(int eventId,LocalDate startRegis,LocalDate EndDate){
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "SELECT numClick FROM logClick where evId = ?";
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement(sql); 
            ps.setInt(1, eventId);
            rs = ps.executeQuery();
        }catch(SQLException e){
            
            e.printStackTrace();
            
        }
        return rs;
    }
    
    public void increaseClick(int eventId,LocalDate startRegis,LocalDate EndDate){
        ResultSet getClick = getClick(eventId,startRegis,EndDate);
        int numClick = 0;
        try{
            if(getClick.next()){
                numClick = getClick.getInt("numClick");
            }else
                addRecord(eventId,startRegis,EndDate);
        }catch(SQLException e){
            addRecord(eventId,startRegis,EndDate);
            e.printStackTrace();
        }
        PreparedStatement ps = null;
        String sql = "UPDATE logClick set numClick = ? where evId = ?";
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement(sql); 
            ps.setInt(1, numClick+1);
            ps.setInt(2, eventId);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        cb.logout();
    }
    
    
    public ResultSet getRankEvent(){
        LocalDate now = LocalDate.now();
        Date date = Date.valueOf(now); 
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM logClick where evEndDate > ? AND evStartRegis >= ? ORDER BY numClick DESC  LIMIT 2";
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement(sql); 
            ps.setDate(1, date);
            ps.setDate(2, date);
            rs = ps.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rs;
    }
}
