/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import java.time.LocalDate;

/**
 *
 * @author JIL
 */
public class TableEvent {
    
    private String evName; 
    private String evDescrip;
    private LocalDate evDate;
    private LocalDate evEndDate;
    private String evPlace;
    private int evTicket;
    private int currentMember;
    private String evTime;
    private String evEndTime;
    private int evType;
    private long stdId;

    public TableEvent() {
    }
    
    

    public TableEvent(String evName, String evDescrip, LocalDate evDate, LocalDate evEndDate, String evPlace, int evTicket, int currentMember, String evTime, String evEndTime, int evType, long stdId) {
        this.evName = evName;
        this.evDescrip = evDescrip;
        this.evDate = evDate;
        this.evEndDate = evEndDate;
        this.evPlace = evPlace;
        this.evTicket = evTicket;
        this.currentMember = currentMember;
        this.evTime = evTime;
        this.evEndTime = evEndTime;
        this.evType = evType;
        this.stdId = stdId;
    }

    public String getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName = evName;
    }

    public String getEvDescrip() {
        return evDescrip;
    }

    public void setEvDescrip(String evDescrip) {
        this.evDescrip = evDescrip;
    }

    public LocalDate getEvDate() {
        return evDate;
    }

    public void setEvDate(LocalDate evDate) {
        this.evDate = evDate;
    }

    public LocalDate getEvEndDate() {
        return evEndDate;
    }

    public void setEvEndDate(LocalDate evEndDate) {
        this.evEndDate = evEndDate;
    }

    public String getEvPlace() {
        return evPlace;
    }

    public void setEvPlace(String evPlace) {
        this.evPlace = evPlace;
    }

    public int getEvTicket() {
        return evTicket;
    }

    public void setEvTicket(int evTicket) {
        this.evTicket = evTicket;
    }

    public int getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(int currentMember) {
        this.currentMember = currentMember;
    }

    public String getEvTime() {
        return evTime;
    }

    public void setEvTime(String evTime) {
        this.evTime = evTime;
    }

    public String getEvEndTime() {
        return evEndTime;
    }

    public void setEvEndTime(String evEndTime) {
        this.evEndTime = evEndTime;
    }

    public int getEvType() {
        return evType;
    }

    public void setEvType(int evType) {
        this.evType = evType;
    }

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }
    
     
}
