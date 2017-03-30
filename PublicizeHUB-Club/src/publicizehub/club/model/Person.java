/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.model;

/**
 *
 * @author budsagorn_ss
 */
public class Person {
    private long stdId;
    private String stdName;
    private String stdSurname;
    private String department;
    private int status;

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdSurname() {
        return stdSurname;
    }

    public void setStdSurname(String stdSurname) {
        this.stdSurname = stdSurname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
