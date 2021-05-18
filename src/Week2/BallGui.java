/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Week2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Lee5
 */
public class BallGui extends JPanel implements ActionListener{
    public final int PANEL_WIDTH = 500;
    public final int PANEL_HEIGHT = 500;
    private List<Ball> balls;
    private animationPanel aniPanel;
    private JPanel buttonPanel;
    private Timer timer;
    private boolean isStop;
    private JButton addButton, removeButton, exitButton, stopButton, resumeButton; 

    public BallGui(){
        super(new BorderLayout());
        balls = new ArrayList<>();
        aniPanel = new animationPanel();
        timer = new Timer(5, this);
        timer.start();
        
        buttonPanel = new JPanel();
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        exitButton = new JButton("Exit");
        stopButton = new JButton("Stop");
        resumeButton = new JButton("Resume");
        
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        exitButton.addActionListener(this);
        stopButton.addActionListener(this);
        resumeButton.addActionListener(this);
        
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resumeButton);
        buttonPanel.add(exitButton);
        
        this.add(aniPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source ==  addButton){
            Ball current = new Ball(aniPanel.getWidth(), aniPanel.getHeight());
            balls.add(current);
            Thread t = new Thread(current);
            t.start();
        }
        if(source ==  removeButton){
            if(!balls.isEmpty()){
                balls.remove(balls.size()-1);
            }
        }
        if(source ==  exitButton){
            System.exit(0);
        }
        if(source ==  resumeButton){
            if (isStop == true){
                for (Ball b : balls){
                    Thread t = new Thread(b);
                    t.start();
                }
                isStop = false;
            }    
        }
        if(source ==  stopButton){
            for(Ball ball: balls){
                ball.stopRequest = true;
            }
            isStop = true;
        }
        
        aniPanel.repaint();
        
    }
    
    private class animationPanel extends JPanel{
        
        public animationPanel(){
            this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            this.setBackground(Color.WHITE);
        }
        
        @Override
        public void paintComponent(Graphics g){
            
            super.paintComponent(g);
            
            if(!balls.isEmpty()){
                
                for (Ball b : balls){
                    b.draw(g);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        BallGui myPanel = new BallGui(); //create instance of the GUI
        
        JFrame frame = new JFrame("Bouncing Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(myPanel);  //adding the panel into the frame
        frame.pack(); 
        frame.setResizable(true);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        frame.setLocation(new Point((screenWidth / 2) - (frame.getWidth() / 2), (screenHeight / 2) - (frame.getHeight() / 2)));

        frame.setVisible(true);
    }
    
}
