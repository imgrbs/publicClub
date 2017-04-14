/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

/**
 *
 * @author JIL
 */

//จำลองเอาเด้อ
public class LoginController {
    private long stdId = 59130500007l;
    private String name = "อิอิ";
    private String surname = "เจียรจินดารัตน์";
    private String department = "Information Technnology";
    private int status = 1;
    private boolean checkLogin = true;

    
    public LoginController() {
    }
    
    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public boolean getCheckLogin() {
        return checkLogin;
    }

    
}
