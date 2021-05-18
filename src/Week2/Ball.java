/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
/**
 *
 * @author Andrew Lee 17983766
 */
public class Ball implements Runnable{

    private int WORLD_WIDTH;
    private int WORLD_HEIGHT;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private Color ballColor;
    private int ballSize;
    public boolean stopRequest;
      
    public Ball(int width, int height){
        //Bounds of the JFrame
        WORLD_WIDTH = width;
        WORLD_HEIGHT = height;
        
        //Initial location
        x = WORLD_WIDTH/2;
        y = WORLD_HEIGHT/2;
        
        //Generate a random direction for the ball to bounce
        Random generator = new Random();
        do{
            dx =  generator.nextInt(20) - 10;
            dy =  generator.nextInt(20) - 10;
        }while(dx == 0 || dy == 0);
        
        //Give the ball a color
        ballColor = new Color(generator.nextFloat(), generator.nextFloat(), generator.nextFloat());
        ballSize = generator.nextInt(100)+2;      
    }
    
    public void draw(Graphics g){
        
        g.setColor(ballColor);
        g.fillOval(x, y, ballSize, ballSize);
    }
    
    @Override
    public void run() {
        
        stopRequest = false;
        
        while(!stopRequest){
            if (x <= 0 || (x+ballSize) >= WORLD_WIDTH){
                dx = -dx;
            }
            if (y <= 0 || (y+ballSize) >= WORLD_HEIGHT){
                dy = -dy;
            }
            
            x += dx;
            y += dy;
            
            try{
                Thread.sleep(20);
            }catch(InterruptedException e){
                
            }
        }
    }
    
}
