import com.sun.opengl.util.FPSAnimator;

import scene.Camera;
import scene.Ground;
import scene.HeliBody;
import scene.Lighting;
import scene.Origin;
import scene.TailRotor;
import scene.TopRotor;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;

/***
 * 
 * @author Jacqueline Whalley
 * @modified wdg7741 Timothy Grey
 *
 */
public class Heli16Flight implements GLEventListener, KeyListener
{
	//scene objects
	private Camera camera;
	private Lighting lights;
	private Origin origin;
	private Ground land;
	private HeliBody heliB;
	private TopRotor topR;
	private TailRotor tailR;
	private static Frame frame;
	
	boolean theLights = true;
	boolean light0 = true;
	boolean light1 = true;
	boolean light2 = true;
	
	private boolean upRelease = false;
	private boolean downRelease = false;
	private boolean leftRelease = false;
	private boolean rightRelease = false;
	private boolean forwardRelease = false;
	private boolean backwardRelease = false;
	private boolean rotateLeftRelease = false;
	private boolean rotateRightRelease = false;
	private boolean isBirdsEye = false;
	
	private int topRSpeed = 10;
	private int tailRSpeed = 20;
	
	public static void main(String[] args) 
	{
		frame = new Frame("HELICOPPER!");
		GLCanvas canvas = new GLCanvas();
		Heli16Flight app = new Heli16Flight();
		canvas.addGLEventListener(app);
		canvas.addKeyListener(app); //NEEDED THIS! FOR EVENTS TO REGISTER WITH CANVAS!! JW
		frame.add(canvas);
		frame.setSize(800, 800);
		final FPSAnimator animator = new FPSAnimator(canvas,60);
		frame.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				// Run this on another thread than the AWT event queue to
				// make sure the call to Animator.stop() completes before
				// exiting
				new Thread(new Runnable() 
				{
					@Override
					public void run() 
					{
						animator.stop();
						System.exit(0);
					}
				}).start();
			}
		});
		// Center frame
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		animator.start();
		
		//instructions for terminal
		System.out.println("[[[HELICOPTER CONTROLS:]]]");
		System.out.println("[[ARROW KEYS:]]");
		System.out.println("[Forward] - Descend");
		System.out.println("[Backward] - Ascend");
		System.out.println("[Left] - Rotate Left");
		System.out.println("[Right] - Rotate Right");
		System.out.println("[[KEYBOARD:]]");
		System.out.println("[W] - Accelerate");
		System.out.println("[S] - Decelerate");
		System.out.println("[A] - Strafe Left");
		System.out.println("[D] - Strafe Right");
		System.out.println("[[TOOL KEYS:]]");
		System.out.println("[L] - Toggle Wireframe/Fill ground");
		System.out.println("[R] - Return to Origin X = 0, Y = 0, Z = 0");
		System.out.println("[1] - Toggle Light0");
		System.out.println("[2] - Toggle Light1");
		System.out.println("[3] - Toggle Light2");
		System.out.println("[H] - Toggle All Lighting");
	}

	@Override
	public void init(GLAutoDrawable drawable) 
	{
		GL gl = drawable.getGL();
		// Enable VSync
		gl.setSwapInterval(1);
		// Setup the drawing area and shading mode
		gl.glEnable (GL.GL_BLEND);
		gl.glBlendFunc (GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
		gl.glClearColor(0.1f, 0.1f, 0.6f, 0.8f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		
		//CAMERA TYPES
		camera = new Camera();
		//Instantiate Objects
		lights = new Lighting(gl);
		origin = new Origin();
		land = new Ground();
		heliB = new HeliBody();
		topR = new TopRotor();
		tailR = new TailRotor();
		
		gl.glEnable(GL.GL_NORMALIZE);
		gl.glShadeModel(GL.GL_SMOOTH);
		gl.glEnable(GL.GL_COLOR_MATERIAL);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) 
	{
		camera.newWindowSize(width, height);
	}

	@Override
	public void display(GLAutoDrawable drawable) 
	{
		GL gl = drawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL.GL_COLOR_MATERIAL);
		frame.addKeyListener(this);
		//have to set up camera before you draw it!
		topR.topRAngle += topRSpeed;
		tailR.tailRAngle += tailRSpeed;
		if(!isBirdsEye) //toggle birdseye camera
		{
			float cameraX = heliB.bodyOrigin[0] + ((float)Math.sin(Math.toRadians(heliB.angle)) * 10);
			float cameraY = heliB.bodyOrigin[1] +  5;
			float cameraZ = heliB.bodyOrigin[2] + ((float)Math.cos(Math.toRadians(heliB.angle )) * 10);
			camera.setEye(cameraX, cameraY, cameraZ);
		}
		else
		{
			float cameraX = heliB.bodyOrigin[0] + ((float)Math.sin(Math.toRadians(heliB.angle)) * 10);
			float cameraY = heliB.bodyOrigin[1] +  20;
			float cameraZ = heliB.bodyOrigin[2] + ((float)Math.cos(Math.toRadians(heliB.angle )) * 10);
			camera.setEye(cameraX, cameraY, cameraZ);
		}
		
		camera.setLookAt(heliB.bodyOrigin[0], heliB.bodyOrigin[1], heliB.bodyOrigin[2]); //FIXED TO MATCH CHANGES IN HELI CLASS -JW
		lights.setLightLoc(heliB.bodyOrigin[0], heliB.bodyOrigin[1], heliB.bodyOrigin[2]);
		camera.draw(gl);	
		
		//enable fog and it's settings
		gl.glEnable(GL.GL_FOG);
        gl.glFogi(GL.GL_FOG_MODE, GL.GL_LINEAR);
        gl.glFogf(GL.GL_FOG_END, 80f);
        gl.glFogf(GL.GL_FOG_DENSITY, 0.1f);
		
		lights.draw(gl);
		origin.draw(gl);
		land.draw(gl);
		
		//toggle lighting block
		if(theLights)
		{
			lights.enable();
		}
		else
		{
			lights.disable();
		}
		
		if(light0)
		{
			lights.enableLight0();
		}
		else
		{
			lights.disableLight0();
		}
		
		if(light1)
		{
			lights.enableLight1();
		}
		else
		{
			lights.disableLight1();
		}
		
		if(light2)
		{
			lights.enableLight2();
		}
		else
		{
			lights.disableLight2();
		}
		
		//Draw chopper
		gl.glPushMatrix();
			heliB.draw(gl);			
			topR.draw(gl);		
			tailR.draw(gl);	
		gl.glPopMatrix();
		
		
		checkKeyHold();
        
		// Flush all drawing operations to the graphics card
		gl.glFlush();
	}
	
	//Check if key held for multi-input movement
	public void checkKeyHold()
	{
		if(!upRelease)
		{
			heliB.ascend();
		}
		
		if(!downRelease)
		{
			heliB.descend();
		}
		
		if(!leftRelease)
		{
			heliB.strafeLeft();
		}
		
		if(!rightRelease)
		{
			heliB.strafeRight();
		}
		
		if(!forwardRelease)
		{
			heliB.forward();
		}
		
		if(!backwardRelease)
		{
			heliB.backward();
		}
		
		if(!rotateLeftRelease)
		{
			heliB.rotateLeft();
		}
		
		if(!rotateRightRelease)
		{
			heliB.rotateRight();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int pressedKey = e.getKeyCode();
		
		if(pressedKey == KeyEvent.VK_UP) //Up
		{
			upRelease = false;
		}
		
		if(pressedKey == KeyEvent.VK_DOWN) //Down
		{
			downRelease = false;
		}
		
		if(pressedKey == KeyEvent.VK_A) //Left
		{
			leftRelease = false;
			heliB.yawLeft(true);
		}
		
		if(pressedKey == KeyEvent.VK_D) //Right
		{
			rightRelease = false;
			heliB.yawRight(true);
		}
		
		if(pressedKey == KeyEvent.VK_W) //Forward
		{
			forwardRelease = false;
			heliB.pitchForward(true);
		}
		
		if(pressedKey == KeyEvent.VK_S) //Backward
		{
			backwardRelease = false;
			heliB.pitchBackward(true);
		}
		
		if(pressedKey == KeyEvent.VK_LEFT) //Rotate Left
		{
			rotateLeftRelease = false;
		}
		
		if(pressedKey == KeyEvent.VK_RIGHT) //Rotate Right
		{
			rotateRightRelease = false;
		}
		
		if(pressedKey == KeyEvent.VK_L) //Change land from solid to wireframe
		{
			land.changeGroundType();
		}
		
		if(pressedKey == KeyEvent.VK_C) //Change camera from chase to birds eye
		{
			isBirdsEye = !isBirdsEye;
		}
		
		if(pressedKey == KeyEvent.VK_R) //Return chopper to origin
		{
			heliB.returnToOrigin();
		}
		
		//Toggle lighting
		if(pressedKey == KeyEvent.VK_H)
		{
			if(theLights)
			{
				theLights = false;
			}
			else
			{
				theLights = true;
			}
		}
		
		if(pressedKey == KeyEvent.VK_1)
		{
			if(light0)
			{
				light0 = false;
			}
			else
			{
				light0 = true;
			}
		}
		
		if(pressedKey == KeyEvent.VK_2)
		{
			if(light1)
			{
				light1 = false;
			}
			else
			{
				light1 = true;
			}
		}
		
		if(pressedKey == KeyEvent.VK_3)
		{
			if(light2)
			{
				light2 = false;
			}
			else
			{
				light2 = true;
			}
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		int keyReleased = e.getKeyCode();
		
		if(keyReleased == KeyEvent.VK_UP)
		{
			upRelease = true;
		}
		
		if(keyReleased == KeyEvent.VK_DOWN)
		{
			downRelease = true;
		}
		
		if(keyReleased == KeyEvent.VK_A)
		{
			leftRelease = true;
			heliB.yawLeft(false);
		}
		
		if(keyReleased == KeyEvent.VK_D)
		{
			rightRelease = true;
			heliB.yawRight(false);
		}
		
		if(keyReleased == KeyEvent.VK_W)
		{
			forwardRelease = true;
			heliB.pitchForward(false);
		}
		
		if(keyReleased == KeyEvent.VK_S)
		{
			backwardRelease = true;
			heliB.pitchBackward(false);
		}
		
		if(keyReleased == KeyEvent.VK_LEFT)
		{
			rotateLeftRelease = true;
		}
		
		if(keyReleased == KeyEvent.VK_RIGHT)
		{
			rotateRightRelease = true;
		}
	}
	
	//Unused methods
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
	
	public void keyTyped(KeyEvent e) {}
}
