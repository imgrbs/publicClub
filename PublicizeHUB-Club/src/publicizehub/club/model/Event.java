package publicizehub.club.model;

import java.util.Date;
import java.sql.*;
import java.text.*;
import javax.swing.*;
/**
 *
 * @author ImagineRabbits
 */
public class Event {
    ConnectionBuilder cb = new ConnectionBuilder();
    
    private String evName="";
    private String evDescrip="";
    private Date evDate;
    private Date evEndDate;
    private Time evTime;
    private Time evEndTime;
    private String evPlace;
    private int evTicket;
    private int eventType;
    private long stdId;
    
    private int yValueCurrent = 10;
    private int yValueEnd = 10;
    private int ySizeCurrent;
    private int ySizeComplete;
    
    Date d = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    public int getyValueCurrent() {
        return yValueCurrent;
    }

    public void setyValueCurrent(int yValueCurrent) {
        this.yValueCurrent = yValueCurrent;
    }

    public int getyValueEnd() {
        return yValueEnd;
    }

    public void setyValueEnd(int yValueEnd) {
        this.yValueEnd = yValueEnd;
    }

    public int getySizeCurrent() {
        return ySizeCurrent;
    }

    public void setySizeCurrent(int ySizeCurrent) {
        this.ySizeCurrent = ySizeCurrent;
    }

    public int getySizeComplete() {
        return ySizeComplete;
    }

    public void setySizeComplete(int ySizeComplete) {
        this.ySizeComplete = ySizeComplete;
    }

    
    
    
    public void newEvent() {
        cb.connecting();
        Statement s = null;
       
        try {
            s = cb.getConnect().createStatement();
            // SQL Insert
            String sql = "INSERT INTO tb_event"
                    + "(evName,evDescrip,evDate,evEndDate,evTime,evEndTime,evPlace,evTicket,evType,stuId) "
                    + "VALUES ('" 
                    + evName + "','"
                    + evDescrip + "','"
                    + evDate + "','"
                    + evEndDate + "','"
                    + evTime + "','"
                    + evEndTime + "','"
                    + evPlace + "','"
                    + evTicket + "','"
                    + eventType + "','"
                    + stdId + "') ";
            s.executeUpdate(sql);
            
//            evName.setText("");
//            evDescrip.setText("");
//            evDate.setText("");
//            evEndDate.setText("");
//            evTime.setText("");
//            evEndTime.setText("");
//            evPlace.setText("");
//            evTicket.setText("");
//            evType.setText("");
//            JOptionPane.showMessageDialog(null, "Record Inserted Successfully");
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }

        cb.logout();
    }
    
    
    public void addEventToPanel(JPanel jp,JPanel jp2){
        PreparedStatement ps = null;
        ResultSet result;
        
        cb.connecting(); //เรียกใช้ method connecting()เพื่อ connect database
        try {
            System.out.println("Done");
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_event");
            result = ps.executeQuery();

            while (result.next()) {
                if (d.compareTo(result.getDate("evEndDate")) <= 0) {
                    ySizeCurrent +=110;      
                    jp.setPreferredSize(new java.awt.Dimension(400, ySizeCurrent));            
                    currentEvent(result, jp);
                } else {
                    ySizeComplete += 110;
                    jp2.setPreferredSize(new java.awt.Dimension(400, ySizeComplete));  
                    completeEvent(result, jp2);
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void currentEvent(ResultSet result, JPanel jp) {
        int tempId=0;
        Date tempStart = new Date();
        Date tempEnd = new Date();
        String tempName="";
        String tempDesc="";
        String tempTime="";
        String tempEndTime="";
        String tempPlace="";
        int tempType=0;
        try{
            tempId = result.getInt("evId");
            tempName = result.getString("evName");
            tempDesc = result.getString("evDescrip");
            tempTime = result.getString("evTime");
            tempEndTime = result.getString("evEndTime");
            tempPlace = result.getString("evPlace");
            tempStart = result.getDate("evDate");
            tempEnd = result.getDate("evEndDate");
            tempType = result.getInt("evType");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        TrackId ti = new TrackId(tempId,tempName,tempDesc,tempStart,tempEnd,tempTime,tempEndTime,tempPlace,tempType);
        System.out.println("Check");        
        
    }
    
    public void completeEvent(ResultSet result, JPanel jp) {

        
    }
    
    public void setLabel(ResultSet result,JLabel lbEvName){
        try {
            lbEvName.setText(result.getString("evName"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
