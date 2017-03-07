/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.view;

/**
 *
 * @author Imagine
 */
import java.sql.*;
import java.awt.*;
import javax.swing.*;
public class FeedGUI extends JFrame {
    private int userStatus=0;
    private String userName="กีรติ";
    private String userSurname="เจียรจินดารัตน์";
    private long stdId=59130500007l;
    
    public void Run(){
        setTitle("publicizeHUB");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(255, 204, 204));
        setBounds(100, 100, 1024, 650);
        getContentPane().setLayout(null);
        
        panelNews();
        panelProfiles();
        panelSearch();
        
    }
    
    public void panelProfiles(){
        // Panel Profile
        JPanel profile = new JPanel();
        profile.setOpaque(true);
        profile.setBounds(1, 1, 240, 650);
        profile.setBackground(new java.awt.Color(255, 153, 153));
        getContentPane().add(profile);
        
        profile.setLayout(null);
        
        // Text ID
        JLabel idLabel = new JLabel();
        idLabel.setText("รหัสผู้ใช้งาน");
        idLabel.setFont(new java.awt.Font("Tahoma", 1, 20));
        idLabel.setBounds(50, 25, 120, 30);
        profile.add(idLabel);
        
        // ID
        JLabel idUser = new JLabel();
        idUser.setText(""+stdId);
        idUser.setFont(new java.awt.Font("Tahoma", 1, 25));
        idUser.setForeground(new java.awt.Color(255, 255, 255));
        idUser.setBounds(30, 60, 240, 30);
        profile.add(idUser);
        
        // Name
        JLabel nameUser = new JLabel();
        nameUser.setText(userName+" "+userSurname);
        nameUser.setFont(new java.awt.Font("Tahoma", 1, 15));
        nameUser.setBounds(50, 90, 240, 30);
        profile.add(nameUser);
        
        // Button Profile
        JButton btnProfiles = new JButton();
        if(userStatus==1) btnProfiles.setText("Admin");
        else btnProfiles.setText("Profiles");
        btnProfiles.setFont(new java.awt.Font("Tahoma", 1, 15));
        btnProfiles.setBackground(new java.awt.Color(255, 153, 0));
        btnProfiles.setBounds(65, 140, 110, 35);
        profile.add(btnProfiles);
        
        // typeEvent text
        JLabel typeEvText = new JLabel();
        typeEvText.setText("ประเภทกิจกรรม");
        typeEvText.setFont(new java.awt.Font("Tahoma", 1, 23));
        typeEvText.setForeground(new java.awt.Color(255, 255, 255));
        typeEvText.setBounds(35, 210, 240, 30);
        profile.add(typeEvText);
        
        // Event button All
        JButton evBtnAll = new JButton();
        evBtnAll.setText("ทั้งหมด");
        evBtnAll.setFont(new java.awt.Font("Tahoma", 1, 15));
        evBtnAll.setBackground(new java.awt.Color(153, 255, 204));
        evBtnAll.setBounds(35, 250, 170, 35);
        profile.add(evBtnAll);
        
        // Event button Camp
        JButton evBtnCamp= new JButton();
        evBtnCamp.setText("ค่าย");
        evBtnCamp.setFont(new java.awt.Font("Tahoma", 1, 15));
        evBtnCamp.setBackground(new java.awt.Color(153, 255, 204));
        evBtnCamp.setBounds(35, 290, 170, 35);
        profile.add(evBtnCamp);
        
        // Event button Seminar
        JButton evBtnSeminar= new JButton();
        evBtnSeminar.setText("สัมมนา");
        evBtnSeminar.setFont(new java.awt.Font("Tahoma", 1, 15));
        evBtnSeminar.setBackground(new java.awt.Color(153, 255, 204));
        evBtnSeminar.setBounds(35, 330, 170, 35);
        profile.add(evBtnSeminar);

    }
    
    public void panelSearch(){
        // Panel Search
        JPanel searchBox = new JPanel();
        searchBox.setOpaque(true);
        searchBox.setBounds(600, 20, 370, 50);
        searchBox.setBackground(new java.awt.Color(255, 153, 153));
        getContentPane().add(searchBox);
        
        //Search Button
        JButton btnSearch = new JButton();
        btnSearch.setText("ค้นหา");
        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnSearch.setBackground(new java.awt.Color(153, 255, 204));
        btnSearch.setBounds(0, 0, 100, 30);
        searchBox.add(btnSearch);
        
        //Search Field
        JTextField searchField = new JTextField();
        searchField.setBounds(0, 0, 250, 30);
        searchField.setBackground(new java.awt.Color(255, 255, 255));
        searchField.setSelectionColor(new java.awt.Color(255, 153, 0));
        searchField.setSelectedTextColor(new java.awt.Color(204, 204, 204));
        searchBox.add(searchField);
    }
    
    public void panelNews(){
        // Panel News
        JPanel newsBox = new JPanel();
        newsBox.setOpaque(true);
        newsBox.setBounds(270, 95, 715, 200);
        newsBox.setBackground(new java.awt.Color(255, 204, 153));
        getContentPane().add(newsBox);
    }
    
    public static void main(String[] args) {
       FeedGUI feed = new FeedGUI();
       feed.Run();
       feed.setVisible(true);
    }

}
