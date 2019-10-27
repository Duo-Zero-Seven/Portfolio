package scene;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 * Basic Lighting setup for scene
 * Consists of global ambient lighting and 
 * @author Jacqueline Whalley
 *
 */
public class Lighting 
{
	public GLU glu;
	public GL gl;
	public GLUquadric quadric;
	float globalAmbient[] = { 0.4f, 0.4f, 0.4f, 1 }; 	// global light properties
	
	public float[] spotLightPosition = 	{ 5.0f, 5.0f, 5.0f, 1.0f }; //spotlight point light
	
	public float[] lightPosition01 = 	{ 5.0f, 10.0f, 5.0f, 1.0f }; //Secondary Light
	public float[] lightPosition02 = 	{ -5.0f, 10.0f, -5.0f, 1.0f }; //Secondary Light
	
	//Spotlight settings
	public float[] ambientLight = 	{ 0, 0, 0, 1 };
	public float[] diffuseLight = 	{ 1, 1, 1, 1 };
	public float[] specularLight = 	{ 1, 1, 1, 1 };
	
	//Secondary Light 1 settings
	public float[] ambientLight01 = { 0, 0, 0, 1 };
	public float[] diffuseLight01 = { 1, 1, 1, 1 };
	public float[] specularLight01 = { 1, 1, 1, 1 };
	
	//secondary light 2 settings
	public float[] ambientLight02 = { 0, 0, 0, 1 };
	public float[] diffuseLight02 = { 1, 1, 1, 1 };
	public float[] specularLight02 = { 1, 1, 1, 1 };

	/**
	 * Creates and enables scene's lights using default light0 position
	 * @param gl
	 */
	public Lighting(GL gl) 
	{
		glu = new GLU();
		this.gl = gl;
		// set the global ambient light level
	    gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, globalAmbient, 0);
	    
		// setup the spotlight light 0 properties
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambientLight, 0);
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuseLight, 0);
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, specularLight, 0);
		gl.glLightf(GL.GL_LIGHT0, GL.GL_SPOT_CUTOFF, -20.0f);	
		gl.glLightf(GL.GL_LIGHT0, GL.GL_LINEAR_ATTENUATION, 1.0f);
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, spotLightPosition, 0);
		gl.glLightfv(GL.GL_LIGHT0,GL.GL_SPOT_EXPONENT, spotLightPosition, 0);
		
		// setup the light 1 properties
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, ambientLight01, 0);
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE, diffuseLight01, 0);
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPECULAR, specularLight01, 0);
		
		// setup the light 2 properties
		gl.glLightfv(GL.GL_LIGHT2, GL.GL_AMBIENT, ambientLight02, 0);
		gl.glLightfv(GL.GL_LIGHT2, GL.GL_DIFFUSE, diffuseLight02, 0);
		gl.glLightfv(GL.GL_LIGHT2, GL.GL_SPECULAR, specularLight02, 0);
	    
	   // position the lights
	    gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, spotLightPosition, 0);
	    gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, lightPosition01, 0);
	    gl.glLightfv(GL.GL_LIGHT2, GL.GL_POSITION, lightPosition02, 0);
	    
	 // normalize the normals
	    gl.glEnable(GL.GL_NORMALIZE);
	}

	/**
	 * Creates and enables scene's light and specifies a position for light 0
	 * @param position light0's position
	 * @param gl
	 */
	public Lighting(float[] position, GL gl) {}
	
	public void setLightLoc(float lightX, float lightY, float lightZ) //set the location of the spotlight at the chopper's position
	{
		spotLightPosition[0] = lightX;
		spotLightPosition[1] = lightY - 1;
		spotLightPosition[2] = lightZ;
		spotLightPosition[3] = 1f;
	}
	
	/**
	 * enable both global ambient and light0
	 */
	public void enable() //enable all lights
	{
		// enable lighting
	    gl.glEnable(GL.GL_LIGHTING);
	    gl.glEnable(GL.GL_LIGHT0);
	    gl.glEnable(GL.GL_LIGHT1);
	    gl.glEnable(GL.GL_LIGHT2);
	}
	
	/**
	 * disables light0 only
	 * global ambient light remains enabled
	 */
	public void disable() //disable all lights
	{
		gl.glDisable(GL.GL_LIGHTING);
		gl.glDisable(GL.GL_LIGHT0);
		gl.glDisable(GL.GL_LIGHT1);
	    gl.glDisable(GL.GL_LIGHT2);
	}
	
	public void enableLight0() //toggle light 0 on
	{
		gl.glEnable(GL.GL_LIGHT0); 
	}
	
	public void enableLight1() //toggle light 1 on
	{
		gl.glEnable(GL.GL_LIGHT1);

	}
	
	public void enableLight2() //toggle light 2 on
	{
		gl.glEnable(GL.GL_LIGHT2);
	}
	
	public void disableLight0() //toggle light 0 off
	{
		gl.glDisable(GL.GL_LIGHT0); 
	}
	
	public void disableLight1() //toggle light 1 off
	{
		gl.glDisable(GL.GL_LIGHT1);

	}
	
	public void disableLight2() //toggle light 2 off
	{
		gl.glDisable(GL.GL_LIGHT2);
	}

	/**
	 * Function to aid rendering and debugging
	 * draws a small white sphere for the light source
	 * draws a line from the light position through the XZ
	 * plane - helps once other objects are in scene.
	 * helps locate light0 in the scene
	 * @param gl
	 */
	public void draw(GL gl) 
	{
		quadric = glu.gluNewQuadric();
		gl.glPushMatrix();
			gl.glDisable(GL.GL_LIGHTING);
			gl.glColor4d(1, 1, 1, 1);
			gl.glTranslated(spotLightPosition[0], spotLightPosition[1], spotLightPosition[2]);
			glu.gluSphere(quadric, 0.1, 16, 8); // draw sphere translated to light position
			gl.glBegin(GL.GL_LINES);
				gl.glVertex3f(0, 0, 0); 
				gl.glVertex3f(0, -100, 0); 
			gl.glEnd();
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glColor4d(1, 1, 1, 1);
			gl.glTranslated(lightPosition01[0], lightPosition01[1], lightPosition01[2]);
			glu.gluSphere(quadric, 0.1, 16, 8); // draw sphere translated to light position
			gl.glBegin(GL.GL_LINES);
				gl.glVertex3f(0, 0, 0); 
				gl.glVertex3f(0, -100, 0); 
			gl.glEnd();
		gl.glPopMatrix();
		gl.glPushMatrix();
			gl.glColor4d(1, 1, 1, 1);
			gl.glTranslated(lightPosition02[0], lightPosition02[1], lightPosition02[2]);
			glu.gluSphere(quadric, 0.1, 16, 8); // draw sphere translated to light position
			gl.glBegin(GL.GL_LINES);
				gl.glVertex3f(0, 0, 0); 
				gl.glVertex3f(0, -100, 0); 
			gl.glEnd();
			gl.glEnable(GL.GL_LIGHTING);
		gl.glPopMatrix();
	}
}