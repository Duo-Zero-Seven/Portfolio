
//import java.time.ZonedDateTime;
import java.util.ArrayList;

public class MonitoringCooler implements Cooler, Runnable
{
	//Machine Collection for monitoring
	ArrayList <Machine> machineCollection = new ArrayList<>();
	
	private boolean isConnectedToMachine = false;
	private boolean requestStop = false;
	
	private int coolingFactor = 0;
	
	//Constructor
	public MonitoringCooler(ArrayList <Machine> machineCollection, int coolingFactor)
	{
		this.coolingFactor = coolingFactor;
		this.machineCollection = machineCollection;
		startCooler();
	}
	
	public void startCooler()
	{
		Thread coolerThread = new Thread(this);
		coolerThread.start();
	}
	
	public void stopCooler() 
	{
		requestStop = true;
	}
	
	//Shorthand temperature check method
	public boolean isMachineTooHot(Machine m)
	{
		if(m.getCurrentTemp() > (m.getMaxTemp() - DANGER_ZONE))
			return true;
		else
			return false;
	}
	
	public void run()
	{
		requestStop = false;
		while(!requestStop)
		{
			for(Machine m : machineCollection)
			{
				synchronized(this)
				{
					//Check if machine able to be connected to
					if(isMachineTooHot(m) && m.isRunning() && !this.isConnectedToMachine && !m.isCoolerConnected())
					{
						isConnectedToMachine = true;
						m.connectCooler(this);
						/*  I used this to try and figure out if it was cooling thing concurrently or separately as it was still super hard to tell using one cooler.
						 *  from what I can tell, it was finally a single unit attaching to a single machine.
							System.out.println("Getting time in milliseconds in Java 8: " + 
					      	ZonedDateTime.now().toInstant().toEpochMilli());
						 */
					}
					else if(m.isCoolerConnected() && m.isCooling && this.isConnectedToMachine)
					{
						isConnectedToMachine = true;
					}
					else if(m.isCooling)
					{
						isConnectedToMachine = false;
					}
				}
			}
			
			try 
			{
				Thread.sleep(200); //sleeeeeeeeep
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public int getCoolingFactor() //Get amount to sequentially cool machine by
	{
		return coolingFactor;
	}

	@Override
	public boolean isConnectedToMachine() 
	{
		return isConnectedToMachine;
	}
}
