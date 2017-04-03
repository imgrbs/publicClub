package publicizehub.club.driver;

import publicizehub.club.view.FeedGUI;

/**
 *
 * @author ImagineRabbits
 */
public class PublicizeHUB {
    
    public static void main(String[] args) {
        FeedGUI fg = new FeedGUI();
        fg.setTheme();
        fg.Run();
        fg.setVisible(true);
    }
    
}
