package publicizehub.club.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Imagine
 */
public class ConnectionBuilder {
    private static final Logger LOGGER = Logger.getLogger( GenerateCode.class.getName() );
    private Connection connect = null;
    private Statement s = null;
    private PreparedStatement ps = null;

    public ConnectionBuilder() {
        System.setProperty("ip","10.4.56.11");
    }
    
    public void connecting(){
        String ip = System.getProperty("ip");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://"+ip+"/publicClub?user=public-1&password=public&characterEncoding=UTF-8");
        }
        catch(SQLException e){
            LOGGER.log(Level.SEVERE ,"ConnectionBuilder : connecting Failed");
        }
        catch(Exception e){
            LOGGER.log(Level.SEVERE ,"ConnectionBuilder : connecting Failed");
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
            LOGGER.log(Level.SEVERE ,"ConnectionBuilder : logout Failed");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE ,"ConnectionBuilder : logout Failed");
        }
        
    }
}
