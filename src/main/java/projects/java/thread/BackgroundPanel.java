package projects.java.thread;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
    
    Color color = Color.gray;
    
    int sizeP, sizeSquare, quantity;
    int res;
    
    public BackgroundPanel(int sizeP, int quantity) {
        this.sizeP = sizeP;
        this.quantity = quantity;
        this.sizeSquare = sizeP / quantity;
        this.res = sizeP % quantity;
    }
    

    
    
    // Es llamado cuando se renderiza la imagen
    @Override
    public void paint(Graphics painter) {
        super.paint(painter);
        painter.setColor(color);
        
        for (int i = 0; i < quantity; i++) {
            for (int j = 0; j < quantity; j++) {
                painter.fillRect(res/2+i*sizeSquare,res/2+j*sizeSquare, sizeSquare-1, sizeSquare-1);
            }
            
        }
    }

       
}
