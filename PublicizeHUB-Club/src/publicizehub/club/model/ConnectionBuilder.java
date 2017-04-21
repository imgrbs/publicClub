package publicizehub.club.model;

import java.sql.*;

/**
 *
 * @author Imagine
 */
public class ConnectionBuilder {
    private Connection connect = null;
    private Statement s = null;
    private PreparedStatement ps = null;
    private ResultSet result;
    
    private String account = "?user=public-1&password=public&characterEncoding=UTF-8";
    
    public void connecting(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://10.4.56.11/publicClub" + account);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }
    
    public void logout(){
        try {
            if(s!=null){
                s.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(connect!=null){
                connect.close();  
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
