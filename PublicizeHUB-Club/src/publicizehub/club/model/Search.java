package publicizehub.club.model;

import java.sql.*;

/**
 *
 * @author ImagineRabbits
 */
public class Search {
    
    ConnectionBuilder cb = new ConnectionBuilder();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public Search() {
    }
    
    public ResultSet resultSearch(String wording){
        cb.connecting();
        String sql="";
        sql = "SELECT * FROM tb_event WHERE evName LIKE '%"+wording+"%'";
        try{
            ps = cb.getConnect().prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLException resultSearch()");
        }
        
        return rs;
    }
    public ResultSet resultEventType(int evType){
        cb.connecting();
        String sql="";
        sql = "SELECT * FROM tb_event WHERE evType LIKE '%"+evType+"%'";
        try{
            ps = cb.getConnect().prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLException resultSearch()");
        }
        
        return rs;
    }
    
}
