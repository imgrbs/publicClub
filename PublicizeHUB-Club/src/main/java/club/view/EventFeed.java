package club.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        
        // Layout
        setLayout(new FlowLayout());
        
        // add label
        add(lb);
        
        // add button
        add(btnJoin);
        add(btnDetail);
        
    }

}
