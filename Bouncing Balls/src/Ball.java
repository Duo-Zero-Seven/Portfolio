import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball implements Runnable 
{
	private Random random;
	private int x, y;
	private int moveX, moveY;
	private int ballsize;
	private int world_w, world_h;
	private Color myColor;
	private boolean requestStop;
	
	public Ball(int world_w, int world_h)
	{
		random = new Random();
		ballsize = random.nextInt(20) + 10;
		this.world_w = world_w;
		this.world_h = world_h;
		x = world_w / 2;
		y = world_h / 2;
		
		//Generate Positive Or Negative X Co-Ords
		if(random.nextBoolean())
		{
			moveX = random.nextInt(10);
		}
		else
		{
			moveX = random.nextInt(10) - 10;
		}
		
		//Generate Positive Or Negative Y Co-Ords
		if(random.nextBoolean())
		{
			moveY = random.nextInt(10);
		}
		else
		{
			moveY = random.nextInt(10) - 10;
		}
		
		//Set the colour of the new ball to be anything within the 255 spectrum of each RGB element
		myColor = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
		
	}
	
	private void moveBall()
	{
		x += moveX;
		y += moveY;	
		
		if (x - ballsize < 0) 
		{
			moveX = -moveX; // Reflect along normal
			x = ballsize; // Re-position the ball at the edge
        } 
		else if (x + ballsize > world_w) 
		{
			moveX = -moveX;
			x = world_w - ballsize;
        }
         // May cross both x and y bounds
		if (y - ballsize < 0) 
		{
			moveY = -moveY;
			y = ballsize;
		} 
		else if (y + ballsize > world_h) 
		{
			moveY = -moveY;
			y = world_h - ballsize;
		}
	}
	
	public void drawball(Graphics g)
	{
		
		g.setColor(myColor);
		g.fillOval(x,y, ballsize, ballsize);
	}
	
	public void stop()
	{
		requestStop = true;
	}

	@Override
	public void run() 
	{
		requestStop = false;
		while(!requestStop)
		{
			moveBall();
			
			try 
			{
				Thread.sleep(25);
			} catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
