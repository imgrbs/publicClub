package publicizehub.club.model;

import static java.lang.Integer.parseInt;

/**
 *
 * @author ImagineRabbits
 */
public class generateCode {
    private long rand;
    private String timestamp = "";
    private int timerand;
    private int Code;

    private long stdId = 59130500007l;
    private int evId = 10004;
    
    public int getCode() {
        return Code;
    }

    public void generateCode(){
        rand = (long)(Math.random()*8999)+1000;
        timestamp += System.currentTimeMillis();
        timerand = parseInt(timestamp.substring(timestamp.length()-5,timestamp.length()));
        Code = (int)(timerand+rand);
        System.out.println("C"+Code);
    }
    
    public static void main(String[] args) {

    }
}
