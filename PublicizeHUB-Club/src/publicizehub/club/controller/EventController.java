package publicizehub.club.controller;

import publicizehub.club.model.*;
import publicizehub.club.view.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
/**
 *
 * @author ImagineRabbits
 */
public class EventController {
    
    private int evId;
    
    TrackEvent ti =  new TrackEvent(evId);
    ConnectionBuilder cb = new ConnectionBuilder();
    Event ev = new Event();
    
    private static int yValueCurrent = 10;
    private int yValueEnd = 10;
    private int ySizeCurrent =10;
    private int ySizeComplete =10;
    java.util.Date d = new java.util.Date();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    
    PanelEventComponent pec = null;

    public static int getyValueCurrent() {
        return yValueCurrent;
    }

    public static void setyValueCurrent(int yValueCurrent) {
        EventController.yValueCurrent = yValueCurrent;
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
    public void AddCurrentEvent(JPanel jp,JPanel jp2) {
        ResultSet rs = ev.getEvent();
        int tempId=0;
        java.util.Date tempStart = new java.util.Date();
        java.util.Date tempEnd = new java.util.Date();
        String tempName="";
        String tempDesc="";
        String tempTime="";
        String tempEndTime="";
        String tempPlace="";
        int tempType=0;
        
        try{
            while(rs.next()){
                tempId = rs.getInt("evId");
                System.out.println("tempId "+tempId);
                evId = tempId;
                System.out.println("tempId->evId"+evId);
                tempName = rs.getString("evName");
                tempDesc = rs.getString("evDescrip");
                tempTime = rs.getString("evTime");
                tempEndTime = rs.getString("evEndTime");
                tempPlace = rs.getString("evPlace");
                tempStart = rs.getDate("evDate");
                tempEnd = rs.getDate("evEndDate");
                tempType = rs.getInt("evType");
//                Event track= new Event(rs.getInt("evId"),rs.getDate("evDate"),rs.getDate("evEndDate")
//                        ,rs.getTime("evTime"),rs.getTime("evEndTime"),rs.getString("evPlace")
//                        ,rs.getInt("evTicket"),rs.getInt("evType"),rs.getInt("stdId"));
                
                pec = new PanelEventComponent(tempName,tempId);
                System.out.println("pec id "+pec.getEvId());
                if (d.compareTo(tempEnd) <= 0) {
                    this.ySizeCurrent +=110;      
                    jp.setPreferredSize(new java.awt.Dimension(400, this.ySizeCurrent));        
//                    System.out.println(ySizeCurrent);
                    jp.add(pec.AddCurrentEvent());
//                    System.out.println(tempName);
//                    System.out.println("ADD CR Event");
                } else {
                    this.ySizeComplete += 110;
                    jp2.setPreferredSize(new java.awt.Dimension(400, this.ySizeComplete));
                    jp2.add(pec.AddCompleteEvent());
//                    System.out.println("ADD CP Event");
                }
//                if(this.ySizeCurrent<300){
//                    this.ySizeCurrent=150;
//                }
//                else if(this.ySizeComplete<300){
//                    this.ySizeComplete=150;
//                }
            }
            
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
//        TrackEvent ti= new TrackEvent(tempId,tempName,tempDesc,tempStart
//                      ,tempEnd,tempTime,tempEndTime,tempPlace,tempType);
        cb.logout();
        System.out.println("Add Success");
    }
    
    JPanel jptemp , jptemp2;
    public void refreshPanel(JPanel jp,JPanel jp2){
        jptemp = jp;
        jptemp2 = jp2;
        System.out.println("PEC ID REF"+pec.getEvId());
        System.out.println("Refresh");
        pec.setyValueCurrent(10);
        pec.setyValueComplete(10);
        this.ySizeCurrent=0;
        this.ySizeComplete=0;
        jp.removeAll();
        jp2.removeAll();
        jp.validate();
        jp.repaint();
        AddCurrentEvent(jp,jp2); 
        jp2.validate();
        jp2.repaint();
        jp2.validate();
        jp2.repaint();
    }
    
    public void DeleteAlert(int delId){
        System.out.println("DELID *"+delId);
        ev.DeleteEvent(delId);
        JOptionPane.showMessageDialog(null,"Delete Success");
    }
    
    
}
