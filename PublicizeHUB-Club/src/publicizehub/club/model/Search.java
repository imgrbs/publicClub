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
        String sql = "SELECT * FROM tb_event where = evName LIKE = %"+wording+"%";
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
