package publicizehub.club.view;

import javax.swing.*;
import javax.swing.UIManager.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import publicizehub.club.controller.ConnectionBuilder;
import publicizehub.club.model.trackId;

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
    private static int runId = 10000;
    private JFrame frame;
    ConnectionBuilder cb = new ConnectionBuilder();

    private int yValueCurrent = 10;
    private int yValueEnd = 10;
    private int ySizeCurrent;
    private int ySizeComplete;
    Date d = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    ArrayList<String> myArrList = new ArrayList<String>();
    JPanel mainPanel;
    JPanel mainPanel2;
    public void actionPerformed(ActionEvent e) {
        // remove the previous JFrame
        this.frame.setVisible(false);
        this.frame.dispose();
    }

    public void Run() {
        setTitle("publicizeHUB");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(255, 255, 255));
        setBounds(100, 100, 1024, 600);
        getContentPane().setLayout(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(100, 100, 450, 600);
        mainPanel.setPreferredSize(new java.awt.Dimension(400, ySizeCurrent));
        mainPanel.setBackground(new java.awt.Color(220, 204, 153));

        mainPanel2 = new JPanel();
        mainPanel2.setLayout(null);
        mainPanel2.setBounds(100, 100, 450, 600);
        mainPanel2.setPreferredSize(new java.awt.Dimension(400, ySizeComplete));
        mainPanel2.setBackground(new java.awt.Color(220, 204, 153));
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(20, 225, 450, 340);
        scrollPane.setBackground(new java.awt.Color(220, 204, 153));

        JScrollPane scrollPane2 = new JScrollPane(mainPanel2);
        scrollPane2.setBounds(550, 225, 450, 340);
        scrollPane2.setBackground(new java.awt.Color(220, 204, 153));

        addEventToPanel();

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
    public void addEventToPanel(){
        PreparedStatement ps = null;
        ResultSet result;
        cb.connecting(); //เรียกใช้ method connecting()เพื่อ connect database
        try {
            System.out.println("Done");
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_event");
            result = ps.executeQuery();

            while (result.next()) {
                if (d.compareTo(result.getDate("evEndDate")) <= 0) {
                    ySizeCurrent +=110;      
                    mainPanel.setPreferredSize(new java.awt.Dimension(400, ySizeCurrent));            
                    currentEvent(result, mainPanel);
                } else {
                    ySizeComplete += 110;
                    mainPanel2.setPreferredSize(new java.awt.Dimension(400, ySizeComplete));  
                    completeEvent(result, mainPanel2);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

        
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

        //stuId
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
        btnCrateEv.setBounds(490, 120, 135, 40);
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
        btnAddNews.setBounds(630, 120, 110, 40);
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
        headEd.setBounds(780, 120, 250, 50);
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
        btnRefresh.setBounds(325, 180, 135, 40);
        btnRefresh.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshPanel();
            }
        }));
        pMain.add(btnRefresh);
    }
    public void refreshPanel(){
        System.out.println("Refresh");
        mainPanel.removeAll();
        mainPanel.validate();
        mainPanel.repaint();
        yValueCurrent = 10;
        yValueEnd = 10;
        addEventToPanel(); 
        mainPanel.validate();
        mainPanel.repaint();
    }
    public void currentEvent(ResultSet result, JPanel jp) {
        
        int tempId=0;
        Date tempStart = new Date();
        Date tempEnd = new Date();
        String tempName="";
        String tempDesc="";
        String tempTime="";
        String tempEndTime="";
        String tempPlace="";
        int tempType=0;
        try{
            tempId = result.getInt("evId");
            tempName = result.getString("evName");
            tempDesc = result.getString("evDescrip");
            tempTime = result.getString("evTime");
            tempEndTime = result.getString("evEndTime");
            tempPlace = result.getString("evPlace");
            tempStart = result.getDate("evDate");
            tempEnd = result.getDate("evEndDate");
            tempType = result.getInt("evType");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        trackId ti= new trackId(tempId,tempName,tempDesc,tempStart,tempEnd,tempTime,tempEndTime,tempPlace,tempType);
        
        System.out.println("Check");
        JPanel act = new JPanel();
        act.setOpaque(true);
        act.setBounds(10, this.yValueCurrent, 400, 90);
        act.setBackground(new java.awt.Color(240, 240, 240));
        act.setLayout(null);

        //event name
        JLabel lbEvName = new JLabel();
        try {
            lbEvName.setText(result.getString("evName"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lbEvName.setFont(new java.awt.Font("Tahoma", 1, 17));
        lbEvName.setBounds(30, 5, 250, 50);
        act.add(lbEvName);
        
        //ปุม edit
        JButton btnEdit = new JButton();
        btnEdit.setText("แก้ไข");
        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 15));
        btnEdit.setBackground(new java.awt.Color(153, 153, 153));
        btnEdit.setBounds(320, 5, 70, 30);
        btnEdit.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditEvent(ti.getId(),ti.getName(),ti.getDesc(),ti.getStDate()
                        ,ti.getEdDate(),ti.getTime(),ti.getEdTime(),ti.getPlace(),ti.getTicket()).setVisible(true);
            }
        }));
        act.add(btnEdit);
        //ปุ่ม check in
        JButton btnCheckIn = new JButton();
        btnCheckIn.setText("เช็คอิน");
        btnCheckIn.setFont(new java.awt.Font("Tahoma", 1, 15));
        btnCheckIn.setBackground(new java.awt.Color(153, 153, 153));
        btnCheckIn.setBounds(100, 45, 80, 30);
        btnCheckIn.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CheckIn().setVisible(true);
            }
        }));
        act.add(btnCheckIn);

        //ปุม detail
        JButton btnDetail = new JButton();
        btnDetail.setText("รายละเอียด");
        btnDetail.setFont(new java.awt.Font("Tahoma", 1, 15));
        btnDetail.setBackground(new java.awt.Color(153, 153, 153));
        btnDetail.setBounds(190, 45, 120, 30);
        btnDetail.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Detail().setVisible(true);
            }
        }));
        act.add(btnDetail);
        
        

        //ปุ่ม ลบ
        JButton btnDelete = new JButton();
        btnDelete.setText("ลบ");
        
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 15));
        btnDelete.setBackground(new java.awt.Color(255, 102, 51));
        btnDelete.setBounds(320, 45, 70, 30);
        act.add(btnDelete);
        
        ActionListener al;
        btnDelete.addActionListener((
            al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteEvent(ti.getId());
                System.out.println(ti.getId());
                jp.remove(act);
                getContentPane().revalidate();
                getContentPane().repaint();
                System.out.println("ลบ");
                refreshPanel();
            }

        }));
        
        jp.add(act);
        this.yValueCurrent += 100;
    }

    public void completeEvent(ResultSet result, JPanel jp) {
        System.out.println("Check");
        JPanel act = new JPanel();
        act.setOpaque(true);
        act.setBounds(10, this.yValueEnd, 400, 90);
        act.setBackground(new java.awt.Color(240, 240, 240));
        act.setLayout(null);

        //event name
        JLabel lbEvName = new JLabel();
        try {
            lbEvName.setText(result.getString("evName"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lbEvName.setFont(new java.awt.Font("Tahoma", 1, 17));
        lbEvName.setBounds(10, 5, 250, 50);
        act.add(lbEvName);

        //ปุ่ม feedback
        JButton btnFeedBack = new JButton();
        btnFeedBack.setText("ผลตอบรับ");
        btnFeedBack.setFont(new java.awt.Font("Tahoma", 1, 15));
        btnFeedBack.setBackground(new java.awt.Color(153, 153, 153));
        btnFeedBack.setBounds(270, 40, 120, 30);
        btnFeedBack.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FormSumActivity().setVisible(true);
            }
        }));
        act.add(btnFeedBack);

        jp.add(act);
        this.yValueEnd += 100;
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