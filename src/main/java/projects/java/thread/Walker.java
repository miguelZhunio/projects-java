package projects.java.thread;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Walker implements Runnable{
    
    private SnakePanel panel;
    boolean status = true;

    public Walker(SnakePanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while(status) {
            panel.advance();
            panel.repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Walker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void stop() {
        this.status = false;
    }
    
}
