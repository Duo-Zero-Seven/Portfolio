/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BouncingBallGUI extends JPanel implements ActionListener
{
	ArrayList<Ball> balls = new ArrayList<>();
	
    private JButton addBall;
    private JButton addTenBalls;
    private JButton addHundredBalls;
    private JButton stopThreads;
    
    private DrawPanel drawPanel;
    
    private int ballCount = 0;

    public BouncingBallGUI() 
    {
        super(new BorderLayout());
        
        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        
        addBall = new JButton("Add Ball");
        addBall.addActionListener(this);
        buttonPanel.add(addBall);
        
        addTenBalls = new JButton("Add 10 Balls");
        addTenBalls.addActionListener(this);
        buttonPanel.add(addTenBalls);
        
        addHundredBalls = new JButton("Add 100 Balls");
        addHundredBalls.addActionListener(this);
        buttonPanel.add(addHundredBalls);
        
        stopThreads = new JButton("Stop");
        stopThreads.addActionListener(this);
        buttonPanel.add(stopThreads);
        
		
        // call actionPerformed method every 25 using Swing timer
        // you can use this to update your GUI
        Timer timer = new Timer(25, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object source = e.getSource();
        
        if (source == addBall) 
        {
        	Ball b = new Ball(drawPanel.getHeight(), drawPanel.getWidth());
        	balls.add(b);
        	Thread ballThread = new Thread(b);
        	ballThread.start();
        	ballCount++;
        	System.out.println("Threads Running: " + ballCount);
        }
        else if (source == addTenBalls) 
        {
        	for(int i = 0; i < 10; i++)
        	{
        		Ball b = new Ball(drawPanel.getHeight(), drawPanel.getWidth());
        		balls.add(b);
        		Thread ballThread = new Thread(b);
            	ballThread.start();
        	}
        	ballCount += 10;
        	System.out.println("Threads Running: " + ballCount);
        }
        else if (source == addHundredBalls) 
        {
        	for(int i = 0; i < 100; i++)
        	{
        		Ball b = new Ball(drawPanel.getHeight(), drawPanel.getWidth());
        		balls.add(b);
        		Thread ballThread = new Thread(b);
            	ballThread.start();
        	}
        	ballCount += 100;
        	System.out.println("Threads Running: " + ballCount);
        }
        else if (source == stopThreads)
        {
        	for(Ball b : balls)
        	{
        		b.stop();
        	}
        	System.out.println("[Threads Stopped: " + ballCount + "]");
    		ballCount = 0;
        }
        
        drawPanel.repaint();  // this will invoke DrawPanel to redraw itself, (paintComponent will be called)
    }

    private class DrawPanel extends JPanel 
    {
        public DrawPanel() 
        {
            super();
            setPreferredSize(new Dimension(800, 800));
            setBackground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            
            for(Ball b : balls)
            {
            	b.drawball(g);
            }
        }
    }

    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Lab 2 Bouncing Balls");
        // kill all threads when frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BouncingBallGUI());
        frame.pack();
        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width - frameDimension.width) / 2,
                (screenDimension.height - frameDimension.height) / 2);
        frame.setVisible(true);
        // now display something while the main thread is still alive
    }
}