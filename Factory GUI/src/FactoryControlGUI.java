import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class FactoryControlGUI extends JPanel implements ActionListener
{
	//Lists for storing machines and coolers
	ArrayList<Machine> machineCollection = new ArrayList<>();
	ArrayList<MonitoringCooler> coolerCollection = new ArrayList<>();
	
	//Panel based variables
	private DrawPanel drawPanel;
	
	private JRadioButton start;
    private JRadioButton stopThreads;
    
    private JLabel descriptLabel;
    private JLabel coolerStatus;
    
    //machine and cooler variables
    private final int MACHINE_SPACING = 15;
    private final int MIN_TEMP = 0;
    private final int MAX_TEMP = 250;
    private final int COOLING_FACTOR = 5;
    private final int coolerCount = 5;
    private final int machineCount = 50;
    
    private String coolerStatusText = "";
    
    //Primary GUI setup
    public FactoryControlGUI() 
    {
        super(new BorderLayout());

        drawPanel = new DrawPanel();
        JPanel buttonPanel = new JPanel();
        add(drawPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);
        
        descriptLabel = new JLabel("Graph of Machine Temperatures in Degrees Celsius:");
        drawPanel.add(descriptLabel);
        
        coolerStatus = new JLabel(coolerStatusText);
        drawPanel.add(coolerStatus);
        
        start = new JRadioButton("Start");
        start.addActionListener(this);
        buttonPanel.add(start);
        
        stopThreads = new JRadioButton("Stop");
        stopThreads.addActionListener(this);
        buttonPanel.add(stopThreads);
        //toggle stop selected on panel creation
        stopThreads.setSelected(true);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(start);
        bg.add(stopThreads);
        
        // call actionPerformed method every 25 using Swing timer
        // you can use this to update your GUI
        Timer timer = new Timer(25, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object source = e.getSource();
        if (source == start) //start factory
    	{	
        	System.out.println("Starting...");
        	for(int i = 0; i < machineCount; i++)
        	{
        		//Machine setup
        		Machine m = new Machine(MIN_TEMP, MAX_TEMP);
        		Machine.nextMachineX += MACHINE_SPACING;
        		machineCollection.add(m);
        	}
        	
        	for(int i = 0; i < coolerCount; i++)
        	{
        		//Cooler setup
        		MonitoringCooler c = new MonitoringCooler(machineCollection, COOLING_FACTOR);
        		coolerCollection.add(c);
        	}
        }
        else if (source == stopThreads) //stop factory
        {
        	System.out.println("Stopping...");
        	for(Machine m : machineCollection)
        	{
        		m.stopMachine();
        	}
        	
        	for(MonitoringCooler c : coolerCollection)
        	{
        		c.stopCooler();
        	}
        }
        
        drawPanel.repaint();  // this will invoke DrawPanel to redraw itself, (paintComponent will be called) 
    }

    private class DrawPanel extends JPanel 
    {
    	//Primary panel draw settings
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
            
            //UI Temperature Measurement Lines
            //0 (Zero)
            g.drawLine(0, 775, 775, 775);
            g.drawString("0", 778, 775);
            //50 (Fifty)
            g.setColor(Color.BLUE);
            g.drawLine(0, 625, 775, 625);
            g.drawString("50", 778, 625);
            //125 (One Hundred and Twenty Five)
            g.setColor(Color.BLACK);
            g.drawLine(0, 400, 775, 400);
            g.drawString("125", 778, 400);
            //200 (Two Hundred)
            g.setColor(Color.RED);
            g.drawLine(0, 175, 775, 175);
            g.drawString("200", 778, 175);
            //250 (Two Hundred and Fifty)
            g.setColor(Color.BLACK);
            g.drawLine(0, 25, 775, 25);
            g.drawString("250", 778, 25);

            //Draw machines
            for(Machine m : machineCollection)
            {
            	m.drawMachine(g);
            }
        }
    }

    //Main
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Factory Control GUI");
        // kill all threads when frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new FactoryControlGUI());
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
