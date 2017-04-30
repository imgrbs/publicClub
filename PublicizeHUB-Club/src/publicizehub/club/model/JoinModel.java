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
import publicizehub.club.controller.LoginController;
/**
 *
 * @author JIL
 */
public class JoinModel {
    private ConnectionBuilder cb = new ConnectionBuilder();
    LoginController li = new LoginController();
    PreparedStatement ps;
    ResultSet rs;
    
    public ResultSet getGenCode(int eventId,long stdId){
        cb.connecting();
        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM generatecode where evId = ? and stdId = ?");
            ps.setInt(1, eventId);
            ps.setLong(2, stdId);
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
    
    public void deleteCode(String evCode,int eventId){
//        getGenCode(eventId);
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("UPDATE logJoining set status = '1' where evCode = ?");
            ps.setString(1, evCode);
            ps.executeUpdate();
            ps = cb.getConnect().prepareStatement("DELETE FROM generatecode where evCode = ?");
            ps.setString(1, evCode);
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        cb.logout();
    }
    
    public int getCurrentMember(int evId){
        int member = 0;
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("SELECT currentMember FROM tb_event where evId = ?");
            ps.setInt(1, evId);
            rs = ps.executeQuery();
            if(rs.next()){
                member = rs.getInt("currentMember");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        cb.logout();
        return member;
    }
    
    public void updateCurrentMember(int evId, int member){
        cb.connecting();
        try{
            ps = cb.getConnect().prepareStatement("UPDATE tb_event set currentMember = '"+ member +"' where evId = ?");
            ps.setInt(1, evId);
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        cb.logout();
    }
    
}
