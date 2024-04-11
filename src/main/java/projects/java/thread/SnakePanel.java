package projects.java.thread;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SnakePanel extends JPanel{
    
    int sizeP, sizeSquare, quantity, res;
    Color colorSnake = Color.GREEN;
    Color colorFood = Color.red;
    List<int[]> snake = new ArrayList<>();
    int[] food = new int[2];
    
    String address = "right";
    String keyedAddress = "right";

    
    Thread thread; // = new Thread(new Walker(this));
    
    Walker walker;
    
    public SnakePanel(int sizeP, int quantity) {
        
        this.sizeP = sizeP;
        this.quantity = quantity;
        this.sizeSquare = sizeP / quantity;
        this.res = sizeP % quantity;
        int[] a = {quantity/2-1, quantity/2-1};
        int[] b = {quantity/2, quantity/2-1};
        
        snake.add(a);
        snake.add(b);
        
        generateFood();
        
        walker = new Walker(this);
        thread = new Thread(walker);
        thread.start();
    }
    
    
    
    // Es llamado cuando se renderiza la imagen
    @Override
    public void paint(Graphics painter) {
        super.paint(painter);
        painter.setColor(colorSnake);
        
        // Pintando serpiente
        for (int[] par: snake) {
            painter.fillRect(res/2+par[0]*sizeSquare, res/2+par[1]*sizeSquare, sizeSquare-1, sizeSquare-1);
        }
        
        // pintando comida
        painter.setColor(colorFood);
        painter.fillRect(res/2+food[0]*sizeSquare, res/2+food[1]*sizeSquare, sizeSquare-1, sizeSquare-1);
         
    }
    
    public void advance() {
        equalAddress();
        boolean exists = false;
        int[] last = snake.get(snake.size()-1);
        int addX = 0;
        int addY = 0;
        
        
        switch (address) {
            case "right":
                addX = 1;
                break;
            case "left":
                addX = -1;
                break;
            case "top":
                addY = -1;
                break;
            case "bottom":
                addY = 1;
                break;
            default:
                throw new AssertionError();
        }
        
        int[] newS = {Math.floorMod(last[0]+addX, quantity), 
            Math.floorMod(last[1]+addY, quantity)}; // INVESTIGAR
        
        for (int i = 0; i < snake.size(); i++) {
            if (newS[0]==snake.get(i)[0] && newS[1] == snake.get(i)[1]) {
                exists = true;
                break;
            }
        }
        
        if (exists) {
            JOptionPane.showMessageDialog(this, "Haz perdido bro");
        } else if(newS[0] == food[0] && newS[1] == food[1]) {
            snake.add(newS);
            generateFood();
        } else {
            snake.add(newS);
            snake.remove(0);
        }
        
        snake.add(newS);
        snake.remove(0);
    }
    
    public void generateFood() {
        boolean exists = false;
        int a =(int) (Math.random()*quantity);
        int b =(int) (Math.random()*quantity);
        
        for (int[] par: snake) {
            if (par[0] == a && par[1] == b) {
                exists = true;
                generateFood();
                break;
            }
            
            if (!exists) {
                this.food[0] = a;
                this.food[1] = b;
                
                System.out.println("a ->" + a + " b " + b);
            }
        }
        
    }
    
    public void changeAddress(String addr) {
        if (this.address.equals("right") || this.address.equals("left") && this.address.equals("top") || this.address.equals("bottom")) {
            this.keyedAddress = addr;
        }
        
        if (this.address.equals("top") || this.address.equals("bottom") && this.address.equals("right") || this.address.equals("left")) {
            this.keyedAddress = addr;
        }
        
    }
    
    public void equalAddress() {
        this.address = this.keyedAddress;
    }
    
}
