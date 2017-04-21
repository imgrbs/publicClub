/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public void toInsertNews(String content){
        cb.connecting();
              
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            PreparedStatement ps = cb.getConnect().prepareStatement("INSERT INTO tb_news"
                    + "(content,datestamp,timestamp) " + 
                    "VALUES ('" + content + "','"+LocalDate.now()+"','"+timeFormat.format(LocalTime.now())+"')");
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
