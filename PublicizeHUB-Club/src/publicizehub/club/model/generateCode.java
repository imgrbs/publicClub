package publicizehub.club.model;

import static java.lang.Integer.parseInt;

/**
 *
 * @author ImagineRabbits
 */
public class generateCode {
    
    public static void main(String[] args) {
        long rand = (long)(Math.random()*8999)+1000;
        String timestamp = ""+System.currentTimeMillis();
        int timerand = parseInt(timestamp.substring(timestamp.length()-5,timestamp.length()));
        int result = (int)(timerand+rand);
        System.out.println(rand);
        System.out.println(timestamp);
        System.out.println(timerand);
        System.out.println(timerand+rand);
        System.out.println("C"+result);
    }
}
