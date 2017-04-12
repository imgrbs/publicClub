/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.model;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import publicizehub.club.controller.FeedbackController;

/**
 *
 * @author budsagorn_ss
 */
public class FeedbackModel {

    ArrayList<FeedbackStd> myArrList = new ArrayList<FeedbackStd>();
//    FeedbackController fbc = new FeedbackController();

    public void insertValue(int evId, long stdId, int valueRadio1, int valueRadio2, int valueRadio3, int valueRadio4, int valueRadio5,
            int valueRadio6, int valueRadio7, int valueRadio8, int valueRadio9, int valueRadio10) {
        Statement s = null;
        String sql;
        ConnectionBuilder cb = new ConnectionBuilder();
        cb.connecting(); //connect database
        try {
            s = cb.getConnect().createStatement();  // สร้าง Statement
            sql = "INSERT INTO std_feedback (evId,stdId,sumQ1,sumQ2,sumQ3,sumQ4,sumQ5,sumQ6,sumQ7,sumQ8,sumQ9,sumQ10) VALUES ('" + evId + "','" + stdId + "',"
                    + "'" + valueRadio1 + "','" + valueRadio2 + "','" + valueRadio3 + "','" + valueRadio4 + "','" + valueRadio5 + "','" + valueRadio6 + "','" + valueRadio7 + "','" + valueRadio8 + "','" + valueRadio9 + "','" + valueRadio10 + "') ";

            s.executeUpdate(sql); // ส่งข้อมูลไป Database 

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            e.printStackTrace();
        }

        cb.logout();
    }

    public void setSumQ() {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet result, result2;
        ConnectionBuilder cb = new ConnectionBuilder();
        cb.connecting(); //เรียกใช้ method connecting()เพื่อ connect database
        int sumQ1 = 0;
        int sumQ2 = 0;
        int sumQ3 = 0;
        int sumQ4 = 0;
        int sumQ5 = 0;
        int sumQ6 = 0;
        int sumQ7 = 0;
        int sumQ8 = 0;
        int sumQ9 = 0;
        int sumQ10 = 0;
        int numPeple = 0;
        int evId = 10048;
        long stdId=59130500045L;
        
        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM std_feedback");
            ps2 = cb.getConnect().prepareStatement("SELECT COUNT(*) number FROM std_feedback");//คำสั่งดูจำนวน row ทั้งหมด (จำนวนคน)
            result = ps.executeQuery();
            result2 = ps2.executeQuery();

            result2.next();
            numPeple = result2.getInt("number");

            while (result.next()) {
                /*FeedbackStd fb = new FeedbackStd(result.getInt("evId"), result.getLong("stdId"), result.getInt("sumQ1"),
                        result.getInt("sumQ2"), result.getInt("sumQ3"), result.getInt("sumQ4"),
                        result.getInt("sumQ5"), result.getInt("sumQ6"), result.getInt("sumQ7"), result.getInt("sumQ8"),
                        result.getInt("sumQ9"), result.getInt("sumQ10"));*/

               // evId = result.getInt("evId");
                //stdId = result.getLong("stdId");
                sumQ1 += result.getInt("sumQ1");
                sumQ2 += result.getInt("sumQ2");
                sumQ3 += result.getInt("sumQ3");
                sumQ4 += result.getInt("sumQ4");
                sumQ5 += result.getInt("sumQ5");
                sumQ6 += result.getInt("sumQ6");
                sumQ7 += result.getInt("sumQ7");
                sumQ8 += result.getInt("sumQ8");
                sumQ9 += result.getInt("sumQ9");
                sumQ10 += result.getInt("sumQ10");
                
                
                
                //myArrList.add(fb);
            }
            
                int averQ1 = sumQ1 / numPeple;
                int averQ2 = sumQ2 / numPeple;
                int averQ3 = sumQ3 / numPeple;
                int averQ4 = sumQ4 / numPeple;
                int averQ5 = sumQ5 / numPeple;
                int averQ6 = sumQ6 / numPeple;
                int averQ7 = sumQ7 / numPeple;
                int averQ8 = sumQ8 / numPeple;
                int averQ9 = sumQ9 / numPeple;
                int averQ10 = sumQ10 / numPeple;
               System.out.println(averQ1);
                double x = 0.2;

                int percentQ1 = (int) (averQ1 * x);
                int percentQ2 =(int) (averQ2 * x);
                int percentQ3 = (int) (averQ3 * x);
                int percentQ4 = (int)(averQ4 * x);
                int percentQ5 = (int)(averQ5 * x);
                int percentQ6 = (int)(averQ6 * x);
                int percentQ7 = (int)(averQ7 * x);
                int percentQ8 = (int)(averQ8 * x);
                int percentQ9 = (int)(averQ9 * x);
                int percentQ10 = (int)(averQ10 * x);
                
            
                int setSumQ1 = (int) (percentQ1 + percentQ2 + percentQ3 + percentQ4 + percentQ5);
                int setSumQ2 = (int) (percentQ6 + percentQ7 + percentQ8 + percentQ9 + percentQ10);

                insertAvgrValue(evId, numPeple,  averQ1,  averQ2,  averQ3, averQ4,  averQ5,  averQ6,  
                                averQ7,  averQ8, averQ9,  averQ10,  setSumQ1,  setSumQ2);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

        cb.logout();
    }

    //ส่งข้อมูลหลังคำนวณไป tb_feedback
    public void insertAvgrValue(int evId,int numPeple, int averQ1, int averQ2, int averQ3,
            int averQ4, int averQ5, int averQ6, int averQ7, int averQ8, int averQ9, int averQ10, int setSumQ1, int setSumQ2 ) {
        Statement s = null;
        String sql;
        ConnectionBuilder cb = new ConnectionBuilder();
        cb.connecting(); //connect database
        try {
            s = cb.getConnect().createStatement();  // สร้าง Statement
            sql = "INSERT INTO tb_feedback (evId,stdEstimated,sumQ1,sumQ2,sumQ3,sumQ4,sumQ5,sumQ6,sumQ7,sumQ8,sumQ9,sumQ10,setSumQ1,setSumQ2) "
                    + "VALUES ('" + evId + "','" + numPeple + "'," + "'" + averQ1 + "','" + averQ2 + "','" + averQ3 + "','"
                    + averQ4 + "','" + averQ5 + "','" + averQ6 + "','" + averQ7 + "','" + averQ8 + "','" + averQ9
                    + "','" + averQ10 + "','" + setSumQ1 + "','" + setSumQ2 + "') ";

            s.executeUpdate(sql); // ส่งข้อมูลไป Database 

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            e.printStackTrace();
        }

        cb.logout();
    }

    
    
}
