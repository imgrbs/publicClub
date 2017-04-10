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

/**
 *
 * @author JIL
 */
public class Join {
    private ConnectionBuilder cb = new ConnectionBuilder();
    private String cont;
    PreparedStatement ps;
    ResultSet rs;
    
    public ResultSet getEvent(){
        cb.connecting();
              
        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_event");
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
