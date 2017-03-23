
package publicizehub.club.view;

import javax.swing.*;
import javax.swing.UIManager.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.*;
import java.util.ArrayList;
import publicizehub.club.controller.ConnectionBuilder;

/**
 *
 * @author JIL
 */
public class ForTesting extends JFrame implements ActionListener {
    
    public String name;
    public String descr;
    public String date;              
    public String place;               
    public int numTick;               
    public String time;               
    public int numPer;
    private static int runId=10000;
    private JFrame frame;
    private final JScrollPane scroll= new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
    ArrayList<String> myArrList = new ArrayList<String>();
    
    public void actionPerformed(ActionEvent e) {
		// remove the previous JFrame
		this.frame.setVisible(false);
		this.frame.dispose();
	}
    public void Run() {
        setTitle("publicizeHub");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(255,255,255));
        setBounds(100, 100, 1024, 768);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        //ใช้วนสร้างpanel
        PreparedStatement ps = null;
        ResultSet result;
        ConnectionBuilder cb = new ConnectionBuilder();
        cb.connecting(); //เรียกใช้ method connecting()เพื่อ connect database
        try {
            System.out.println("Done");
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_event" );
            result = ps.executeQuery();
            
            while (result.next()) {
                panelActivity();           
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

        cb.logout();
        
       
        finAct1();
        //finAct2();
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
        
        /*JScrollPane scrollPane = new JScrollPane();
        scrollPane.setLayout(null);
        scrollPane.setBounds(1, 1, 1024,768);
        scrollPane.setBackground(new java.awt.Color(255, 255, 255));
        
        pMain.add(scrollPane);
        scrollPane.add(pMain);*/
        
        JLabel activity = new JLabel();
        activity.setText("กิจกรรม");
        activity.setFont(new java.awt.Font("Tahoma", 1, 24));
        activity.setBounds(20,180, 250, 50);
        pMain.add(activity);
        
        //label กจก.ที่เสร็จสิ้น
        JLabel finActivity = new JLabel();
        finActivity.setText("กิจกรรมที่เสร็จสิ้นแล้ว");
        finActivity.setFont(new java.awt.Font("Tahoma", 1, 24));
        finActivity.setBounds(550,180, 250, 50);
        pMain.add(finActivity);
         
        //ปุ่ม CrateEvent
        JButton btnCrateEv = new JButton();
        btnCrateEv.setText("เพิ่มกิจกรรม");
        btnCrateEv.setFont(new java.awt.Font("Tahoma", 1, 17));
        btnCrateEv.setBounds(325,180, 135, 40);
        btnCrateEv.addActionListener((new ActionListener() {
                public void actionPerformed(ActionEvent e) {           
                       new CreateEvent().setVisible(true);
                }
        }));
        pMain.add(btnCrateEv);
        
        
    }

    
    public void panelActivity(){
        
        scroll.setBounds(20, 250, 470, 450);
        scroll.setBackground(new java.awt.Color(255,255,255));
        scroll.setOpaque(true);
        scroll.setLayout(null);
        getContentPane().add(scroll);
        
        PreparedStatement ps = null;
        ResultSet result;
        ConnectionBuilder cb = new ConnectionBuilder();
        cb.connecting(); //เรียกใช้ method connecting()เพื่อ connect database
        try {
            System.out.println("Done");
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_event" );
            result = ps.executeQuery();
            int yValue=20;
            
            while(result.next()){
                JPanel act = new JPanel();
                act.setOpaque(true);
                act.setBounds(15,(0)+yValue, 400, 90);
                act.setBackground(new java.awt.Color(240,240,240));
                getContentPane().add(act);
                act.setLayout(null);

                //event name
                JLabel lbEvName = new JLabel();
                lbEvName.setText(result.getString("evName"));
                lbEvName.setFont(new java.awt.Font("Tahoma", 1, 17));
                lbEvName.setBounds(10,5, 250, 50);
                act.add(lbEvName);

                //ปุ่ม check in
                JButton btnCheckIn = new JButton();
                btnCheckIn.setText("เช็คอิน");
                btnCheckIn.setFont(new java.awt.Font("Tahoma", 1, 15));
                btnCheckIn.setBackground(new java.awt.Color(153,153,153));
                btnCheckIn.setBounds(100,45, 80, 30);
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
                btnDetail.setBackground(new java.awt.Color(153,153,153));
                btnDetail.setBounds(190,45, 120, 30);
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
                btnDelete.setBackground(new java.awt.Color(255,102,51));
                btnDelete.setBounds(320,45, 70, 30);
                act.add(btnDelete);
                
                yValue+=100;
                scroll.add(act);
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

        cb.logout();
    }
    
    
    public void finAct1(){
        
        JPanel temp = new JPanel();
        temp.setBounds(700, 400, 450, 50);
        temp.setBackground(new java.awt.Color(255,255,255));
        getContentPane().add(BorderLayout.CENTER,temp);
        JScrollPane jsp= new JScrollPane(temp,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        jsp.setBounds(550, 250, 450, 50);
        jsp.setBackground(new java.awt.Color(255,255,255));
        jsp.setOpaque(true);
        jsp.setLayout(null);        
        getContentPane().add(jsp);
        

        JPanel act = new JPanel();
        act.setOpaque(true);
        act.setBounds(10, 10, 400, 90);
        act.setBackground(new java.awt.Color(240,240,240));
        getContentPane().add(act);
        act.setLayout(null);
        
        //event name
        JLabel lbEvName = new JLabel();
        lbEvName.setText("ชื่อกิจกรรม");
        lbEvName.setFont(new java.awt.Font("Tahoma", 1, 17));
        lbEvName.setBounds(10,5, 250, 50);
        act.add(lbEvName);
        
        //ปุ่ม feedback
        JButton btnFeedBack = new JButton();
        btnFeedBack.setText("ผลตอบรับ");
        btnFeedBack.setFont(new java.awt.Font("Tahoma", 1, 15));
        btnFeedBack.setBackground(new java.awt.Color(153,153,153));
        btnFeedBack.setBounds(270,40, 120, 30);
        btnFeedBack.addActionListener((new ActionListener() {
                public void actionPerformed(ActionEvent e) {           
                       new FormSumActivity().setVisible(true);
                }
        }));
        act.add(btnFeedBack);
        
        
    //}
    
    // public void finAct2(){
        JPanel act2 = new JPanel();
        act2.setOpaque(true);
        act2.setBounds(10, 110, 400, 90);
        act2.setBackground(new java.awt.Color(240,240,240));
        getContentPane().add(act2);
        act2.setLayout(null);
        
        //event name
        JLabel lbEvName2 = new JLabel();
        lbEvName2.setText("ชื่อกิจกรรม");
        lbEvName2.setFont(new java.awt.Font("Tahoma", 1, 17));
        lbEvName2.setBounds(10,5, 250, 50);
        act.add(lbEvName2);
        
        //ปุ่ม feedback
        JButton btnFeedBack2 = new JButton();
        btnFeedBack2.setText("ผลตอบรับ");
        btnFeedBack2.setFont(new java.awt.Font("Tahoma", 1, 15));
        btnFeedBack2.setBackground(new java.awt.Color(153,153,153));
        btnFeedBack2.setBounds(270,40, 120, 30);
        btnFeedBack2.addActionListener((new ActionListener() {
                public void actionPerformed(ActionEvent e) {           
                       new FormSumActivity().setVisible(true);
                }
        }));
        act.add(btnFeedBack2);
        jsp.add(act);
        jsp.add(act2);
        
    }
    
    public static void main(String[] args) {
        ForTesting sg = new ForTesting();
        sg.Run();
        sg.setVisible(true);
    }
}
