import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class PaintBG extends JPanel
{
	private int x;
	private int y;
	
	private int foodX;
	private int foodY;
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void setFoodX(int x)
	{
		this.foodX = x;
	}
	
	public void setFoodY(int y)
	{
		this.foodY = y;
	}
	
	public int getFoodX()
	{
		return this.foodX;
	}
	
	public int getFoodY()
	{
		return this.foodY;
	}
	
	protected void paintComponent(Graphics g)
	{
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 800, 800);

		g.setColor(Color.PINK);
		g.fillOval(x, y, 10, 10);
		
		g.setColor(Color.RED);
		g.fillRect(foodX, foodY, 10, 10);
	}
}
