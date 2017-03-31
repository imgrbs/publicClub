package publicizehub.club.model;

//import java.sql.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ImagineRabbits
 */
public class TrackEvent {
    private int id;
    
    private String name;
    private String desc;
    Date stDate = new Date();
    Date edDate = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    String time;
    String edTime;
    String place;
    int ticket;
    
    public TrackEvent(int id) {
        this.id = id;
    }

    public TrackEvent(int id, String name, String desc, Date stDate, Date edDate, String time, String edTime, String place, int ticket) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.stDate = stDate;
        this.edDate = edDate;
        this.time = time;
        this.edTime = edTime;
        this.place = place;
        this.ticket = ticket;
    }
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getStDate() {
        return stDate;
    }

    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    public Date getEdDate() {
        return edDate;
    }

    public void setEdDate(Date edDate) {
        this.edDate = edDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEdTime() {
        return edTime;
    }

    public void setEdTime(String edTime) {
        this.edTime = edTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
