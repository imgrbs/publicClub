/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.view;

import java.sql.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.UIManager.*;
/**
 *
 * @author JIL
 */
public class AdminGUI extends JFrame {
    
    
    public void Run() {
        setTitle("publicizeHUB");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(255,255,255));
        setBounds(100, 100, 1024, 768);
        getContentPane().setLayout(null);

        panelActivity1();
        panelActivity2();
        panelClose();
        panelProfile();
        panelMain();
        
    }
    
    public void panelClose(){
        
        JPanel close = new JPanel();
        close.setOpaque(true);
        close.setBounds(1, 1, 257, 171);
        close.setBackground(new java.awt.Color(204,153,0));
        getContentPane().add(close);
        close.setLayout(null);
        
        //ปุ่มปิด
        JButton btnClose = new JButton();
        btnClose.setText("ปิด");
        btnClose.setFont(new java.awt.Font("Tahoma", 1, 15));
        btnClose.setBackground(new java.awt.Color(255,0,0));
        btnClose.setBounds(5,5, 63, 37);
        close.add(btnClose);
        
        //text ข้อมูลส่วนตัว
        JLabel txt = new JLabel();
        txt.setText("ข้อมูลส่วนตัว");
        txt.setFont(new java.awt.Font("Tahoma", 1, 30));
        txt.setBounds(60, 80, 200, 50);
        close.add(txt);    
        
    }
    
    public void panelProfile(){
        JPanel profile = new JPanel();
        profile.setOpaque(true);
        profile.setBounds(1, 1, 1024, 171);
        profile.setBackground(new java.awt.Color(255,204,102));
        getContentPane().add(profile);
        profile.setLayout(null);
        
        //ชื่อ สกุล
        JLabel headName = new JLabel();
        headName.setText("ชื่อ-สกุล : ");
        headName.setFont(new java.awt.Font("Tahoma", 1, 16));
        headName.setBounds(300, 10, 100, 50);
        profile.add(headName); 
        
        //stuId
        JLabel headId = new JLabel();
        headId.setText("รหัสนักศึกษา :");
        headId.setFont(new java.awt.Font("Tahoma", 1, 16));
        headId.setBounds(300,40, 130, 50);
        profile.add(headId); 
        
        //department
        JLabel headDp = new JLabel();
        headDp.setText("ภาควิชา :");
        headDp.setFont(new java.awt.Font("Tahoma", 1, 16));
        headDp.setBounds(300,70, 130, 50);
        profile.add(headDp);
        
        //edit
        JLabel headEd = new JLabel();
        headEd.setText("ประสงค์แก้ไขข้อมูล โปรดแจ้ง");
        headEd.setFont(new java.awt.Font("Tahoma", 1, 16));
        headEd.setBounds(780,120, 250, 50);
        profile.add(headEd); 
    }
    
    public void panelMain(){
        JPanel pMain = new JPanel();
        pMain.setOpaque(true);
        pMain.setBounds(1, 1, 1024, 768);
        pMain.setBackground(new java.awt.Color(255,204,153));
        getContentPane().add(pMain);
        pMain.setLayout(null);
        
        //lebel กิจกรรม
        JLabel activity = new JLabel();
        activity.setText("กิจกรรม");
        activity.setFont(new java.awt.Font("Tahoma", 1, 24));
        activity.setBounds(20,180, 250, 50);
        pMain.add(activity);
        
        
    }
    
    public void panelActivity1(){
    
        JPanel act = new JPanel();
        act.setOpaque(true);
        act.setBounds(100, 250, 800, 90);
        act.setBackground(new java.awt.Color(240,240,240));
        getContentPane().add(act);
        act.setLayout(null);
    }
    
    public void panelActivity2(){
        JPanel act = new JPanel();
        act.setOpaque(true);
        act.setBounds(100, 350, 800, 90);
        act.setBackground(new java.awt.Color(240,240,240));
        getContentPane().add(act);
        act.setLayout(null);
    }
    
    public static void main(String[] args) {
        AdminGUI sg = new AdminGUI();
        //sg.setTheme();
        sg.Run();
        sg.setVisible(true);
    }
}
