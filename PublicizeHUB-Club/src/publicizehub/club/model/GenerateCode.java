package publicizehub.club.model;

import static java.lang.Integer.parseInt;
import java.sql.*;
import publicizehub.club.controller.ConnectionBuilder;

/**
 *
 * @author ImagineRabbits
 */
public class GenerateCode {
    ConnectionBuilder cb = new ConnectionBuilder();
    private long rand;
    private String timestamp = "";
    private int timerand;
    private int Code;

    private long stdId = 59130500007l;
    private int evId = 10035;
    private String evCode ="";

    public GenerateCode() {
    }
    
    public GenerateCode(long stdId,int evId) {
        this.stdId=stdId;
        this.evId=evId;
    }
    
    
    public int getCode() {
        return Code;
    }

    public void generateCode(){
        rand = (long)(Math.random()*8999)+1000;
        timestamp += System.currentTimeMillis();
        timerand = parseInt(timestamp.substring(timestamp.length()-5,timestamp.length()));
        Code = (int)(timerand+rand);
        System.out.println("C"+Code);
        evCode="C"+Code;
    }
    
    public void pushCode(){
        generateCode();
        cb.connecting();
        PreparedStatement ps;
        String sql = "INSERT INTO generateCode"
                    + "(evId,stuId,evCode) "
                    + "VALUES ('" 
                    + this.evId + "','"
                    + this.stdId + "','"
                    + this.evCode + "') ";
        try{
            ps = cb.getConnect().prepareStatement(sql);
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        
    }
    
}
