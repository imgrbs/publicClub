package publicizehub.club.view;

import javax.swing.*;
import javax.swing.UIManager.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import publicizehub.club.controller.*;
import publicizehub.club.model.*;
/**
 *
 * @author JIL
 */
public class AdminGUI extends JFrame {

    public String name;
    public String descr;
    public String date;
    public String place;
    public int numTick;
    public String time;
    public int numPer;
    private JFrame frame;
    ConnectionBuilder cb = new ConnectionBuilder();


    ArrayList<String> myArrList = new ArrayList<String>();
    JPanel mainPanel;
    JPanel mainPanel2;
    
    EventController ec = new EventController();
    JScrollPane scrollPane;
    JScrollPane scrollPane2; 
    
    public void actionPerformed(ActionEvent e) {
        // remove the previous JFrame
        this.frame.setVisible(false);
        this.frame.dispose();
    }

    public void Run() {
        setTitle("publicizeHUB");
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(255, 255, 255));
        setBounds(100, 100, 1024, 600);
        getContentPane().setLayout(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(100, 100, 450, 600);
        mainPanel.setPreferredSize(new java.awt.Dimension(400, ec.getySizeCurrent()));
        mainPanel.setBackground(new java.awt.Color(220, 204, 153));

        mainPanel2 = new JPanel();
        mainPanel2.setLayout(null);
        mainPanel2.setBounds(100, 100, 450, 600);
        mainPanel2.setPreferredSize(new java.awt.Dimension(400, ec.getySizeComplete()));
        mainPanel2.setBackground(new java.awt.Color(220, 204, 153));
        
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(20, 225, 450, 340);
        scrollPane.setBackground(new java.awt.Color(220, 204, 153));

        scrollPane2 = new JScrollPane(mainPanel2);
        scrollPane2.setBounds(550, 225, 450, 340);
        scrollPane2.setBackground(new java.awt.Color(220, 204, 153));
        
        System.out.println("LOADPANEL");
        ec.AddCurrentEvent(mainPanel,mainPanel2);
        System.out.println("LOADSUCCESS");

        scrollPane.setViewportView(mainPanel);
        scrollPane.setWheelScrollingEnabled(true);
        
        scrollPane2.setViewportView(mainPanel2);
        scrollPane2.setWheelScrollingEnabled(true);
        
        getContentPane().add(scrollPane);
        getContentPane().add(scrollPane2);
        
        panelClose();
        panelProfile();
        panelMain();
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

    public void panelClose() {

        JPanel close = new JPanel();
        close.setOpaque(true);
        close.setBounds(1, 1, 257, 171);
        close.setBackground(new java.awt.Color(204, 153, 0));
        getContentPane().add(close);
        close.setLayout(null);
        //ปุ่มปิด
        JButton btnClose = new JButton();
        btnClose.setText("ปิด");
        btnClose.setFont(new java.awt.Font("Tahoma", 1, 15));
        btnClose.setBackground(new java.awt.Color(255, 0, 0));
        btnClose.setBounds(5, 5, 63, 37);
        btnClose.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        }));
        close.add(btnClose);

        //text ข้อมูลส่วนตัว
        JLabel txt = new JLabel();
        txt.setText("ข้อมูลส่วนตัว");
        txt.setFont(new java.awt.Font("Tahoma", 1, 30));
        txt.setBounds(60, 80, 200, 50);
        close.add(txt);

    }

    public void panelProfile() {
        JPanel profile = new JPanel();
        profile.setOpaque(true);
        profile.setBounds(1, 1, 1024, 171);
        profile.setBackground(new java.awt.Color(255, 204, 102));
        getContentPane().add(profile);
        profile.setLayout(null);

        //ชื่อ สกุล
        JLabel headName = new JLabel();
        headName.setText("ชื่อ-สกุล : ");
        headName.setFont(new java.awt.Font("Tahoma", 1, 16));
        headName.setBounds(300, 10, 100, 50);
        profile.add(headName);
        
        //stdId
        JLabel headId = new JLabel();
        headId.setText("รหัสนักศึกษา :");
        headId.setFont(new java.awt.Font("Tahoma", 1, 16));
        headId.setBounds(300, 40, 130, 50);
        profile.add(headId);

        //department
        JLabel headDp = new JLabel();
        headDp.setText("ภาควิชา :");
        headDp.setFont(new java.awt.Font("Tahoma", 1, 16));
        headDp.setBounds(300, 70, 130, 50);
        profile.add(headDp);
        
        //ปุ่ม CrateEvent
        JButton btnCrateEv = new JButton();
        btnCrateEv.setText("เพิ่มกิจกรรม");
        btnCrateEv.setFont(new java.awt.Font("Tahoma", 1, 17));
        btnCrateEv.setBounds(850,5, 135, 40);
        btnCrateEv.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateEvent().setVisible(true);
            }
        }));
        profile.add(btnCrateEv);
        
        //ปุ่ม addNews
        JButton btnAddNews = new JButton();
        btnAddNews.setText("เพิ่มข่าว");
        btnAddNews.setFont(new java.awt.Font("Tahoma", 1, 17));
        btnAddNews.setBounds(875, 65, 110, 40);
        btnAddNews.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new addNews().setVisible(true);
            }
        }));
        profile.add(btnAddNews);
           
        //edit
        JLabel headEd = new JLabel();
        headEd.setText("ประสงค์แก้ไขข้อมูล โปรดแจ้ง");
        headEd.setFont(new java.awt.Font("Tahoma", 1, 16));
        headEd.setBounds(770, 110, 250, 50);
        profile.add(headEd);
    }

    public void panelMain() {
        JPanel pMain = new JPanel();
        pMain.setOpaque(true);
        pMain.setBounds(0, 0, 1024, 768);
        pMain.setBackground(new java.awt.Color(255, 204, 153));
        getContentPane().add(pMain);
        pMain.setLayout(null);

        JLabel activity = new JLabel();
        activity.setText("กิจกรรม");
        activity.setFont(new java.awt.Font("Tahoma", 1, 24));
        activity.setBounds(20, 180, 250, 50);
        pMain.add(activity);

        //label กจก.ที่เสร็จสิ้น
        JLabel finActivity = new JLabel();
        finActivity.setText("กิจกรรมที่เสร็จสิ้นแล้ว");
        finActivity.setFont(new java.awt.Font("Tahoma", 1, 24));
        finActivity.setBounds(550, 180, 250, 50);
        pMain.add(finActivity);
        
        JButton btnRefresh = new JButton();
        btnRefresh.setText("รีเฟรช");
        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 17));
        btnRefresh.setBounds(330, 180, 135, 40);
        btnRefresh.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ec.refreshPanel(mainPanel,mainPanel2);
                scrollPane.validate();
                scrollPane.repaint();
                scrollPane2.validate();
                scrollPane2.repaint();
            }
        }));
        pMain.add(btnRefresh);
 
    }
    
    public void deleteEvent(int id){
        System.out.println("Call deleteEv");
        String command;
        PreparedStatement s;
        try{
            command ="DELETE FROM tb_event WHERE evId = ?";
            
            System.out.println(id);
            System.out.println(command);
            s = cb.getConnect().prepareStatement(command);
            System.out.println(s);
            s.setInt(1,id);
//            s.setInt(1, result.getInt("evId"));
//            s.execute();
            s.executeUpdate();
            System.out.println("Delete Success");
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cb.logout();
        }
    }

    public static void main(String[] args) {
        AdminGUI sg = new AdminGUI();
        sg.setTheme();

        sg.Run();
        sg.setVisible(true);

    }
}