package publicizehub.club.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author budsagorn_ss
 */
public class LoginModel {

    ConnectionBuilder cb = new ConnectionBuilder();
    private static final Logger LOGGER = Logger.getLogger(LoginModel.class.getName());

    public ResultSet selectLogin(long stdId) {
        PreparedStatement ps = null;
        ResultSet result = null;
        cb.connecting(); //เรียกใช้ method connecting()เพื่อ connect database
        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM students where std_id=? ");
            ps.setLong(1, stdId );
            result = ps.executeQuery();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "selectLogin : selectLogin Failed");
        }
        return result;
    }
    
}
