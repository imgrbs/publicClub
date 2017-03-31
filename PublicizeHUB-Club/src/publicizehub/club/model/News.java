/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 *
 * @author JIL
 */
public class News {
    private ConnectionBuilder cb = new ConnectionBuilder();
    private String cont;
    PreparedStatement ps;
    ResultSet rs;
    
    //ArrayList<String> myArrList = new ArrayList<String>();
    
    public News() {
    }

    public String getContent() {
        return cont;
    }

    public void setContent(String content) {
        this.cont = content;
    }
    public ResultSet getNews(){
        cb.connecting();
              
        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_news");
            rs = ps.executeQuery();
            
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQL ผิดพลาด");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        
        return rs;
    }
    
    
    public void insertNews(JTextField newsId,JTextArea content) {
        ConnectionBuilder cb = new ConnectionBuilder();
        cb.connectWithStatement("INSERT INTO tb_news"
                    + "(content) "
                    + "VALUES ('"
                    + content.getText() + "')",1);
        newsId.setText("");
        content.setText(cont);
        JOptionPane.showMessageDialog(null, "Record Inserted Successfully");
        
        cb.logout();
    }
    
    public void editNews() {
        JOptionPane.showMessageDialog(null, "Test Edit Button");
    }

    public void deleteNews() {
        JOptionPane.showMessageDialog(null, "Test Delete Button");
    }
}
