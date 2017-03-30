/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 *
 * @author JIL
 */
public class News {
    private String cont;
    private ConnectionBuilder cb = new ConnectionBuilder();
    
    ArrayList<String> myArrList = new ArrayList<String>();
    
    public News() {
    }

    public String getContent() {
        return cont;
    }

    public void setContent(String content) {
        this.cont = content;
    }
    
    public void addNewsToList(JList newsList){
        cb.connecting();

        PreparedStatement ps = null;
        ResultSet result;
        try {
            ps = cb.getConnect().prepareStatement("SELECT * FROM tb_news");
            result = ps.executeQuery();
            while (result.next()) {
                String temp = result.getString("content");
                myArrList.add(temp);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

        cb.logout();

        String[] temp = new String[myArrList.size()];
        for (int i = 0; i < myArrList.size(); i++) {
            temp[i] = "- " + myArrList.get(i);
        }
        newsList.setListData(temp);
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
