package publicizehub.club.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author budsagorn_ss
 */
public class LoginModel {
    
     ConnectionBuilder cb = new ConnectionBuilder();
     
     public boolean isLogin(long stdId,String password) throws Exception {
        PreparedStatement ps = null;
        ResultSet result;
        String evId = "";
        cb.connecting(); //เรียกใช้ method connecting()เพื่อ connect database
        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM username where stdId = ? and password = ?");
            ps.setLong(1,stdId);
            ps.setString(1,password);
            result = ps.executeQuery();
            
            if(result.next()){
                return true;
            } else 
                return false;
            
        } catch (Exception e) {
           return false;
            
        }       
     
     }
}
