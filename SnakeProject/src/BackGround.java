import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.util.Random;

public class BackGround extends JFrame implements KeyListener, ActionListener
{	
	Dimension screenDim;
	JFrame playSpace;
	PaintBG bgPaint;
	Random rNum;
	Timer timer;
	
	private int testX = 395;
	private int testY = 395;
	
	int time = 0;
	int velocityX = 0;
	int velocityY = 0;
	
	public BackGround()
	{
		playSpace();
	}
	
	public void playSpace()
	{
		screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		playSpace = new JFrame("Snake");
		bgPaint = new PaintBG();
		timer = new Timer(33, this);
		rNum = new Random();
		
		playSpace.setSize(800, 800);
		playSpace.setLocation(screenDim.width / 2 - playSpace.getWidth() / 2, screenDim.height / 2 - playSpace.getHeight() / 2);
		playSpace.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playSpace.setResizable(false);
		playSpace.setVisible(true);
		playSpace.add(bgPaint);
		playSpace.addKeyListener(this);
		
		bgPaint.setFoodX(200);
		bgPaint.setFoodY(200);
		
		bgPaint.setX(testX);
		bgPaint.setY(testY);
		
		timer.addActionListener(this);
		timer.start();
	}
	
	public static void main(String [] args)
	{
		BackGround ps = new BackGround();
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		//this area is solely for input
		// TODO Auto-generated method stub

		if(e.getKeyCode() == KeyEvent.VK_LEFT) //keychar for left
		{
			System.out.println("Left!");
			velocityX = -1;
			velocityY = 0;
		}

		if(e.getKeyCode() == KeyEvent.VK_RIGHT) //keychar for right
		{
			System.out.println("Right!");
			velocityX = 1;
			velocityY = 0;
		}

		if(e.getKeyCode() == KeyEvent.VK_UP) //keychar for up
		{
			System.out.println("Up!");
			velocityY = -1;
			velocityX = 0;
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN) //keychar for down
		{
			System.out.println("Down!");
			velocityY = 1;
			velocityX = 0;
		}

		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) //keychar for escape
		{
			System.out.println("Escape!");
			System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(testX == 800 || testY == 800 || testX == 0 || testY == 0)
		{
			System.out.println("GAME OVER!");
			System.exit(0);
		}
		
		//in here is where all the updating and game loop is happening
		// TODO Auto-generated method stub
		//Update the world
		testX += velocityX;
		testY += velocityY;
		
		bgPaint.setX(testX);
		bgPaint.setY(testY);
		
		if((testX + 5 >= bgPaint.getFoodX() && testX - 5 <= bgPaint.getFoodX()) && (testY + 5 >= bgPaint.getFoodY() && testY - 5 <= bgPaint.getFoodY()))
		{
			int fX = rNum.nextInt(700);
			int fY = rNum.nextInt(700);
			bgPaint.setFoodX(fX);
			bgPaint.setFoodY(fY);
		}
		//Draw the world
		bgPaint.repaint();
		//System.out.println("X: " + testX + " \nY: " + testY);
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
	}
}
