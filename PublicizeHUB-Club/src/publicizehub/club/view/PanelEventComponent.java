package publicizehub.club.view;

import java.awt.event.*;
import publicizehub.club.controller.*;
import javax.swing.*;

/**
 *
 * @author ImagineRabbits
 */
public class PanelEventComponent {
    EventController ec = new EventController();
    private static int yValueCurrent = 10;
    private static int yValueComplete = 10;
    
    private String evName;
    private int evId;

    public static int getyValueCurrent() {
        return yValueCurrent;
    }

    public static void setyValueCurrent(int yValueCurrent) {
        PanelEventComponent.yValueCurrent = yValueCurrent;
    }

    public static int getyValueComplete() {
        return yValueComplete;
    }

    public static void setyValueComplete(int yValueComplete) {
        PanelEventComponent.yValueComplete = yValueComplete;
    }

    public int getEvId() {
        return evId;
    }

    public void setEvId(int evId) {
        this.evId = evId;
    }
    
    
    
    public PanelEventComponent() {
    }

    public PanelEventComponent(String evName, int evId) {
        this.evName = evName;
        this.evId = evId;
    }
    
    public JPanel AddCurrentEvent(){
        System.out.println("Add Current Event");
        JPanel act = new JPanel();
        act.setOpaque(true);
        act.setBounds(15, this.yValueCurrent, 400, 90);
        act.setBackground(new java.awt.Color(240, 240, 240));
        act.setLayout(null);

        //event name
        JLabel lbEvName = new JLabel();
        try {
            lbEvName.setText(evName);
        } catch (Exception e) {
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
//                new EditEvent(ti.getId(),ti.getName(),ti.getDesc(),ti.getStDate()
//                        ,ti.getEdDate(),ti.getTime(),ti.getEdTime(),ti.getPlace(),ti.getTicket()).setVisible(true);
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
                new CheckIn(evId).setVisible(true);
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
        
        btnDelete.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog 
                (null, "จะลบกิจกรรมใช่หรือไม่","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                    ec.DeleteAlert(evId);
                }
            }

        }));
        
        this.yValueCurrent += 100; 
        
        return act;
    }
    
    public JPanel AddCompleteEvent(){
        System.out.println("Add Complete Event");
        JPanel act = new JPanel();
        act.setOpaque(true);
        act.setBounds(15, this.yValueComplete, 400, 90);
        act.setBackground(new java.awt.Color(240, 240, 240));
        act.setLayout(null);

        //event name
        JLabel lbEvName = new JLabel();
        try {
            lbEvName.setText(evName);
        }
        catch(Exception e){
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

        this.yValueComplete += 100; 
        
        return act;
    }
    
}
