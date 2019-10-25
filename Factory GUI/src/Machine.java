import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Machine implements Runnable
{
	//Class variables
	private Cooler connectedCooler;
	private Color myColor;
	private String coolerStatus = " -";
	private Random random;
	
	//boolean
	private boolean isRunning = false;
	private boolean requestStop = false;
	private boolean isCoolerConnected = false;
	public boolean isCooling = false; //one step way to check connection validity
	
	//int
	private int minTemp, maxTemp;
	private int currentTemp;
	private int machineX = nextMachineX;
	private final int COOLER_SYMBOL_Y = 795;
	public static int nextMachineX = 15; //used for keeping track of machine draw locations
	
	//Constructor
	public Machine(int minTemp, int maxTemp)
	{
		random = new Random();
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		currentTemp = 50;
		startMachine();
	}

	public void startMachine()
	{
		Thread machineThread = new Thread(this);
		machineThread.start();
		isRunning = true;
	}
	
	public boolean isRunning()
	{
		if(!requestStop)
		{
			return true;
		}
		else
		{
			isRunning = false;
			return false;
		}
	}
	
	public void stopMachine()
	{
		requestStop = true;
		isRunning = false;
	}
	
	public int getCurrentTemp()
	{
		return this.currentTemp;
	}
	
	public int getMinTemp()
	{
		return this.minTemp;
	}
	
	public int getMaxTemp()
	{
		return this.maxTemp;
	}
	
	public boolean connectCooler(Cooler cooler)
	{	
		//Check if cooler connection valid
		connectedCooler = cooler;
		if(connectedCooler.isConnectedToMachine() && !isCoolerConnected())
		{	
			isCoolerConnected = true;
			return true;
		}
		else
			return false;
	}
	
	//Check cooler connection status, reflect with icon
	public boolean isCoolerConnected()
	{
		if(isCoolerConnected)
		{
			coolerStatus = " +";
			return true;
		}	
		else
		{
			isCoolerConnected = false;
			coolerStatus = " -";
			return false;
		}
	}
	
	public void disconnectCooler()
	{
		if(isCoolerConnected)
		{
			coolerStatus = " -";
			isCoolerConnected = false;
		}	
		else
		{
			coolerStatus = " -";
		}
	}
	
	//Primary machine draw and colour control
	public void drawMachine(Graphics g)
	{
		//Draw Connected Cooler Connected Symbol
		g.setColor(Color.BLACK);
		g.drawString(coolerStatus, machineX, COOLER_SYMBOL_Y);
		
		//Draw Machine Temperature as bar graph
		if(currentTemp > 0 && currentTemp < 50)
		{
			//Cold Ice Blue
			myColor = new Color(0,255,255);
		}
		else if(currentTemp > 50 && currentTemp < 125)
		{
			//Blue
			myColor = new Color(0,0,170);
		}
		else if(currentTemp > 125 && currentTemp < 200)
		{
			//Yellow
			myColor = new Color(255,255,100);
		}
		else if(currentTemp > 200 && currentTemp < 250)
		{
			//Red, HOT!
			myColor = new Color(255,0,0);
		}
		
		//Draw Machine
		int machineY = 775 - (currentTemp*3);
		g.setColor(myColor);
		g.fillRect(machineX, machineY, 12, (currentTemp*3)); //better way to draw, thanks Adam!
	}
	
	public void run() throws MachineTemperatureException
	{
		requestStop = false;
		while(!requestStop)
		{
			if(isCoolerConnected() && isRunning())
			{
				synchronized(this) //make sure the dance is in step
				{
					isCooling = true;
					currentTemp -= connectedCooler.getCoolingFactor();
					if(currentTemp <= minTemp + connectedCooler.DANGER_ZONE)
					{
						disconnectCooler();
					}
				}
			}
			else if(currentTemp > minTemp && currentTemp < maxTemp && isRunning())
			{
				synchronized(this)
				{
					currentTemp += random.nextInt(5);
				}
			}
			else
			{
				stopMachine();
			}
			
			try
			{
				if(currentTemp > maxTemp && !isRunning()) 
				{
					System.out.println("Machine Overheated!");
					stopMachine();
				}	
				else if(currentTemp < minTemp && !isRunning())
				{
					System.out.println("Machine Overcooled!");
					stopMachine();
				}
			}
			catch(MachineTemperatureException e)
			{
				System.out.println("Machine Temperature Exception thrown!");
			}
			
			try 
			{
				Thread.sleep(50);
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
