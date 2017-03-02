package club.view;

import java.awt.*;
import javax.swing.*;
// import button,frame,label


public class EventFeed extends JFrame {
    public static final int WIDTH=1024;
    public static final int HEIGHT=768;
    
    public EventFeed() {
        // basic config JFrame
        setTitle("PublicizeHUB");        
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Label
        JLabel lb = new JLabel();
        lb.setText("Event");

        // Button
        JButton btnJoin = new JButton("Join");
        JButton btnDetail = new JButton("Detail");
        
        // Grid
//        GridLayout grid = new GridLayout(3,3,10,50);
        
        // Layout
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        
        // add label
        add(lb);
        
        // add button
        add(btnJoin);
        add(btnDetail);
        
    }

}
