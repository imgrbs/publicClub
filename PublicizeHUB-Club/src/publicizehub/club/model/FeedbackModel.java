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

    public void insertValue(int evId, long stdId, int valueRadio1, int valueRadio2, int valueRadio3, int valueRadio4, int valueRadio5, int valueRadio6, int valueRadio7, int valueRadio8, int valueRadio9, int valueRadio10) {
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

        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM std_feedback");
            ps2 = cb.getConnect().prepareStatement("SELECT COUNT(*) number FROM std_feedback");
            result = ps.executeQuery();
            result2 = ps2.executeQuery();

            result2.next();
            numPeple = result2.getInt("number");

            while (result.next()) {
                FeedbackStd fb = new FeedbackStd(result.getInt("evId"), result.getLong("stdId"), result.getInt("sumQ1"),
                        result.getInt("sumQ2"), result.getInt("sumQ3"), result.getInt("sumQ4"),
                        result.getInt("sumQ5"), result.getInt("sumQ6"), result.getInt("sumQ7"), result.getInt("sumQ8"),
                        result.getInt("sumQ9"), result.getInt("sumQ10"));

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

            int percentQ1 = sumQ1 / numPeple;
            int percentQ2 = sumQ2 / numPeple;
            int percentQ3 = sumQ3 / numPeple;
            int percentQ4 = sumQ4 / numPeple;
            int percentQ5 = sumQ5 / numPeple;
            int percentQ6 = sumQ6 / numPeple;
            int percentQ7 = sumQ7 / numPeple;
            int percentQ8 = sumQ8 / numPeple;
            int percentQ9 = sumQ9 / numPeple;
            int percentQ10 = sumQ10 / numPeple;

            System.out.println(percentQ1);

            //fbc.getArrayList(myArrList);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

        cb.logout();
    }
    
    public void insertAvgrValue(int evId, long stdId, int percentQ1, int percentQ2, int percentQ3,
            int percentQ4, int percentQ5, int percentQ6, int percentQ7, int percentQ8, int percentQ9, int percentQ10,int setSumQ1,int setSumQ2 ) {
        Statement s = null;
        String sql;
        ConnectionBuilder cb = new ConnectionBuilder();
        cb.connecting(); //connect database
        try {
            s = cb.getConnect().createStatement();  // สร้าง Statement
            sql = "INSERT INTO std_feedback (evId,stdId,sumQ1,sumQ2,sumQ3,sumQ4,sumQ5,sumQ6,sumQ7,sumQ8,sumQ9,sumQ10,setSumQ1,setSumQ2) "
                    + "VALUES ('" + evId + "','" + stdId + "'," + "'" + percentQ1 + "','" + percentQ2 + "','" + percentQ3 + "','" 
                    + percentQ4 + "','" + percentQ5 + "','" + percentQ6 + "','" + percentQ7 + "','" + percentQ8 + "','" + percentQ9 
                    + "','" + percentQ10 + "') ";

            s.executeUpdate(sql); // ส่งข้อมูลไป Database 

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

            e.printStackTrace();
        }

        cb.logout();
    }

}
