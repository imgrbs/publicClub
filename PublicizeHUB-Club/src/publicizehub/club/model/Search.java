package publicizehub.club.model;

import java.sql.*;

/**
 *
 * @author ImagineRabbits
 */
public class Search {
    
    ConnectionBuilder cb = new ConnectionBuilder();
    Statement ps = null;
    ResultSet rs = null;
    
    public Search() {
    }
    
    public ResultSet resultSearch(String wording){
        cb.connecting();
        String sql = "SELECT * FROM tb_event WHERE evName LIKE '%"+wording+"%'";
        try{
            ps = cb.getConnect().createStatement();
            rs = ps.executeQuery(sql);
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLException resultSearch()");
        }
        
        return rs;
    }
    
}
