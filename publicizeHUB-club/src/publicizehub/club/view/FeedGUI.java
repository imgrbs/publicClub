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
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.UIManager.*;
import publicizehub.club.model.*;

public class FeedGUI extends JFrame {

    private int userStatus = 1;
    private String userName = "กีรติ";
    private String userSurname = "เจียรจินดารัตน์";
    private long stdId = 59130500007l;
    private News ns = new News();
    
    ArrayList<String> myArrList = new ArrayList<String>();
    
    public void Run() {
        setTitle("publicizeHUB");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(255, 204, 204));
        setBounds(100, 100, 1024, 650);
        getContentPane().setLayout(null);

        panelProfiles();
        panelSearch();
        panelNews();
        eventRec();
    }

    public void setTheme() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    public void panelProfiles() {
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
        idLabel.setBounds(60, 25, 120, 30);
        profile.add(idLabel);

        // ID
        JLabel idUser = new JLabel();
        idUser.setText("" + stdId);
        idUser.setFont(new java.awt.Font("Tahoma", 1, 25));
        idUser.setForeground(new java.awt.Color(255, 255, 255));
        idUser.setBounds(30, 60, 240, 30);
        profile.add(idUser);

        // Name
        JLabel nameUser = new JLabel();
        nameUser.setText(userName + " " + userSurname);
        nameUser.setFont(new java.awt.Font("Tahoma", 1, 15));
        nameUser.setBounds(50, 90, 240, 30);
        profile.add(nameUser);

        // Button Profile
        JButton btnProfiles = new JButton();
        if (userStatus == 1) {
            btnProfiles.setText("Admin");
            btnProfiles.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    AdminGUI ag = new AdminGUI();
                    ag.Run();
                    ag.setVisible(true);
                    ag.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                }
            });
        } else {
            btnProfiles.setText("Profiles");
            btnProfiles.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    ProfileGUI pg = new ProfileGUI();
                    pg.Run();
                    pg.setVisible(true);
                    pg.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                }
            });
        }
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
        JButton evBtnCamp = new JButton();
        evBtnCamp.setText("ค่าย");
        evBtnCamp.setFont(new java.awt.Font("Tahoma", 1, 15));
        evBtnCamp.setBackground(new java.awt.Color(153, 255, 204));
        evBtnCamp.setBounds(35, 290, 170, 35);
        profile.add(evBtnCamp);

        // Event button Seminar
        JButton evBtnSeminar = new JButton();
        evBtnSeminar.setText("สัมมนา");
        evBtnSeminar.setFont(new java.awt.Font("Tahoma", 1, 15));
        evBtnSeminar.setBackground(new java.awt.Color(153, 255, 204));
        evBtnSeminar.setBounds(35, 330, 170, 35);
        profile.add(evBtnSeminar);

    }

    public void panelSearch() {
        // Panel Search
        JPanel searchBox = new JPanel();
        searchBox.setOpaque(true);
        searchBox.setBounds(600, 20, 370, 50);
        searchBox.setBackground(new java.awt.Color(255, 153, 153));
        getContentPane().add(searchBox);
        searchBox.setLayout(null);

        //Search Button
        JButton btnSearch = new JButton();
        btnSearch.setText("ค้นหา");
        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnSearch.setBackground(new java.awt.Color(153, 255, 204));
        btnSearch.setBounds(5, 10, 100, 30);
        searchBox.add(btnSearch);

        //Search Field
        JTextField searchField = new JTextField();
        searchField.setBounds(110, 10, 250, 30);
        searchField.setBackground(new java.awt.Color(255, 255, 255));
        searchField.setSelectionColor(new java.awt.Color(255, 153, 0));
        searchField.setSelectedTextColor(new java.awt.Color(204, 204, 204));
        searchField.setEnabled(true);
        searchBox.add(searchField);
    }

    public void panelNews() {
        
        // Panel News
        JPanel newsBox = new JPanel();
        newsBox.setOpaque(true);
        newsBox.setBounds(270, 95, 715, 210);
        newsBox.setBackground(new java.awt.Color(255, 204, 153));
        getContentPane().add(newsBox);
        newsBox.setLayout(null);
        
        // Label News
        JLabel newsEvText = new JLabel();
        newsEvText.setText("ข่าวใหม่");
        newsEvText.setFont(new java.awt.Font("Tahoma", 1, 18));
        newsEvText.setBounds(30, 5, 240, 30);
        newsBox.add(newsEvText);

        // List
        JList newsList = new JList();
        newsList.setBounds(0, 0, 655, 150);
        newsList.setBackground(new java.awt.Color(255, 255, 255));
        
        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(newsList);
        scrollPane.setBounds(30, 40, 655,150);
        scrollPane.setBackground(new java.awt.Color(255, 255, 255));
        
        ns.addNewsToList(newsList);
        scrollPane.setViewportView(newsList);
        newsBox.add(scrollPane);
        
        
    }

    public void eventRec() {
        JLabel evRecText = new JLabel();
        evRecText.setText("กิจกรรมแนะนำ");
        evRecText.setFont(new java.awt.Font("Tahoma", 1, 18));
        evRecText.setBounds(300, 315, 240, 30);
        getContentPane().add(evRecText);

        JPanel evBox1 = new JPanel();
        evBox1.setBounds(270, 350, 345, 150);
        evBox1.setBackground(new java.awt.Color(255, 255, 255));
        evBox1.setLayout(null);
        getContentPane().add(evBox1);
        
        JLabel recEvent1 = new JLabel();
        recEvent1.setText("ชื่อกิจกรรม : "+"IT 3K"); // รอ get จาก Database
        recEvent1.setFont(new java.awt.Font("Tahoma", 1, 18));
        recEvent1.setBounds(20, 10, 240,30);
        evBox1.add(recEvent1);
        
        JLabel dateEvent1 = new JLabel();
        dateEvent1.setText("วัน/เวลา/สถานที่ : "+""); // รอ get จาก Database
        dateEvent1.setFont(new java.awt.Font("Tahoma", 1, 12));
        dateEvent1.setBounds(20, 40, 240,30);
        evBox1.add(dateEvent1);       
        
        JLabel detailEvent1 = new JLabel();
        detailEvent1.setText("รายละเอียด : "+"IT 3K"); // รอ get จาก Database
        detailEvent1.setFont(new java.awt.Font("Tahoma", 1, 12));
        detailEvent1.setBounds(20, 65, 240,30);
        evBox1.add(detailEvent1);      
        
        JButton joinEvRec1 = new JButton();
        joinEvRec1.setText("เข้าร่วม");
        joinEvRec1.setFont(new java.awt.Font("Tahoma", 1, 12));
        joinEvRec1.setBounds(150, 100, 90, 30);
        joinEvRec1.addActionListener((new ActionListener() {
            int checkClick=1;
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click");
                System.out.println(checkClick);
                if(checkClick!=0){
                    joinEvRec1.setText("ยกเลิก");
                    checkClick=0;
                }
                else {
                    joinEvRec1.setText("เข้าร่วม");
                    checkClick=1;
                }
            }
        }));
        evBox1.add(joinEvRec1);
        
        JButton detEvRec1 = new JButton();
        detEvRec1.setText("รายละเอียด");
        detEvRec1.setFont(new java.awt.Font("Tahoma", 1, 12));
        detEvRec1.setBounds(240, 100, 90, 30);
        evBox1.add(detEvRec1);
        
        JPanel evBox2 = new JPanel();
        evBox2.setBounds(640, 350, 345, 150);
        evBox2.setBackground(new java.awt.Color(255, 255, 255));
        evBox2.setLayout(null);
        getContentPane().add(evBox2);
        
        JLabel recEvent2 = new JLabel();
        recEvent2.setText("ชื่อกิจกรรม : "+"BrownBag"); // รอ get จาก Database
        recEvent2.setFont(new java.awt.Font("Tahoma", 1, 18));
        recEvent2.setBounds(20, 10, 240,30);
        evBox2.add(recEvent2);
        
        JLabel dateEvent2 = new JLabel();
        dateEvent2.setText("วัน/เวลา/สถานที่ : "+""); // รอ get จาก Database
        dateEvent2.setFont(new java.awt.Font("Tahoma", 1, 12));
        dateEvent2.setBounds(20, 40, 240,30);
        evBox2.add(dateEvent2);       
        
        JLabel detailEvent2 = new JLabel();
        detailEvent2.setText("รายละเอียด : "+"BrownBag"); // รอ get จาก Database
        detailEvent2.setFont(new java.awt.Font("Tahoma", 1, 12));
        detailEvent2.setBounds(20, 65, 240,30);
        evBox2.add(detailEvent2);      
        
        JButton joinEvRec2 = new JButton();
        joinEvRec2.setText("เข้าร่วม");
        joinEvRec2.setFont(new java.awt.Font("Tahoma", 1, 12));
        joinEvRec2.setBounds(150, 100, 90, 30);
        joinEvRec2.addActionListener((new ActionListener() {
            int checkClick=1;
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click");
                System.out.println(checkClick);
                if(checkClick!=0){
                    joinEvRec2.setText("ยกเลิก");
                    checkClick=0;
                }
                else {
                    joinEvRec2.setText("เข้าร่วม");
                    checkClick=1;
                }
            }
        }));
        evBox2.add(joinEvRec2);
        
        JButton detEvRec2 = new JButton();
        detEvRec2.setText("รายละเอียด");
        detEvRec2.setFont(new java.awt.Font("Tahoma", 1, 12));
        detEvRec2.setBounds(240, 100, 90, 30);
        evBox2.add(detEvRec2);
    }

    public static void main(String[] args) {
        FeedGUI feed = new FeedGUI();
        feed.setTheme();
        feed.Run();
        feed.setVisible(true);
    }

}
