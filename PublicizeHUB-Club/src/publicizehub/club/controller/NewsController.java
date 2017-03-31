/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JOptionPane;
import publicizehub.club.model.*;

/**
 *
 * @author JIL
 */
public class NewsController {
    News nw = new News();
    ArrayList<String> myArrList = new ArrayList<String>();
    ConnectionBuilder cb = new ConnectionBuilder();
    
    public void addNewsToList(JList newsList){
        ResultSet rs = nw.getNews();
        String cont;
        try {
            
            while (rs.next()) {
                cont = rs.getString("content");
                myArrList.add(cont);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        
        String[] temp = new String[myArrList.size()];
        for (int i = 0; i < myArrList.size(); i++) {
            temp[i] = "- " + myArrList.get(i);
        }
        newsList.setListData(temp);
        cb.logout();
    }
    
}
