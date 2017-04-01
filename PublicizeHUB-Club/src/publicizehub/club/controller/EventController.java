package publicizehub.club.controller;

import static java.lang.Integer.parseInt;
import publicizehub.club.model.*;
import publicizehub.club.view.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author ImagineRabbits
 */
public class EventController {

    private int evId;

    private int evType;

    TrackEvent ti = new TrackEvent(evId);
    ConnectionBuilder cb = new ConnectionBuilder();
    Event ev = new Event();

    private static int yValueCurrent = 10;
    private int yValueEnd = 10;
    private int ySizeCurrent = 10;
    private int ySizeComplete = 10;
    java.util.Date d = new java.util.Date();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm");

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

    public void setEventType(int evType) {
        this.evType = evType;
    }

    public int getEvType() {
        return evType;
    }

    public void setySizeComplete(int ySizeComplete) {
        this.ySizeComplete = ySizeComplete;
    }

    public void AddCurrentEvent(JPanel jp, JPanel jp2) {
        ResultSet rs = ev.getEvent();
        int tempId = 0;
        java.util.Date tempStart = new java.util.Date();
        java.util.Date tempEnd = new java.util.Date();
        String tempName = "";
        String tempDesc = "";
        String tempTime = "";
        String tempEndTime = "";
        String tempPlace = "";
        int tempType = 0;

        try {
            while (rs.next()) {
                tempId = rs.getInt("evId");
                System.out.println("tempId " + tempId);
                evId = tempId;
                System.out.println("tempId->evId" + evId);
                tempName = rs.getString("evName");
                tempDesc = rs.getString("evDescrip");
                tempTime = rs.getString("evTime");
                tempEndTime = rs.getString("evEndTime");
                tempPlace = rs.getString("evPlace");
                tempStart = rs.getDate("evDate");
                tempEnd = rs.getDate("evEndDate");
                tempType = rs.getInt("evType");

                pec = new PanelEventComponent(tempName, tempId);
                System.out.println("pec id " + pec.getEvId());
                if (d.compareTo(tempEnd) <= 0) {
                    this.ySizeCurrent += 110;
                    jp.setPreferredSize(new java.awt.Dimension(400, this.ySizeCurrent));
                    jp.add(pec.AddCurrentEvent());
                } else {
                    this.ySizeComplete += 110;
                    jp2.setPreferredSize(new java.awt.Dimension(400, this.ySizeComplete));
                    jp2.add(pec.AddCompleteEvent());
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        cb.logout();
        System.out.println("Add Success");
    }

    public void refreshPanel(JPanel jp, JPanel jp2) {
        System.out.println("PEC ID REF" + pec.getEvId());
        System.out.println("Refresh");
        pec.setyValueCurrent(10);
        pec.setyValueComplete(10);
        this.ySizeCurrent = 0;
        this.ySizeComplete = 0;
        jp.removeAll();
        jp2.removeAll();
        jp.validate();
        jp.repaint();
        AddCurrentEvent(jp, jp2);
        jp2.validate();
        jp2.repaint();
        jp2.validate();
        jp2.repaint();
    }

    public void DeleteAlert(int delId) {
        System.out.println("DELID *" + delId);
        ev.DeleteEvent(delId);
        JOptionPane.showMessageDialog(null, "Delete Success");
    }

    public void CreateEventValue(String name, String desc, String date, String endDate,
                                String time , String endTime, String place, String ticket,
                                int evType,long stdId) {
        java.util.Date tempDate=null;
        java.util.Date tempEndDate=null;
        try{
            tempDate = ft.parse(date);
            tempEndDate = ft.parse(endDate);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Date ERROR!");
        }
        int tempTicket = parseInt(ticket);
        ev.createEvent(name,desc,date,endDate,time,endTime,
                place,tempTicket,evType,stdId);
    }
}
