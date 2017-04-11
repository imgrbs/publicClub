/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import publicizehub.club.controller.LogIn;
/**
 *
 * @author JIL
 */
public class Join {
    private ConnectionBuilder cb = new ConnectionBuilder();
    private String cont;
    LogIn li = new LogIn();
    PreparedStatement ps;
    ResultSet rs;
    
    public ResultSet getGenCode(int eventId){
        cb.connecting();
              
        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM generatecode where evId = ? and stdId = ?");
            ps.setInt(1, eventId);
            ps.setLong(2, li.getStdId());
            rs = ps.executeQuery();
            
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQL ผิดพลาด");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        
        return rs;
    }
}
