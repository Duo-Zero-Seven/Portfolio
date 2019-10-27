package scene;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Camera 
{
	private static final double FOV = 60;
	
	 double windowWidth	= 1;
	 double windowHeight = 1;
	 private double camZ = -10;
     private double camX = -10;
	 private double camY = 2;
	// the point to look at
	private double lookAt[] = {0, 0, 0};

	public void draw(GL gl)
	{
		// set up projection first
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU glu = new GLU();
        glu.gluPerspective(FOV, (float) windowWidth / (float) windowHeight, 0.1, 150);
        // set up the camera position and orientation
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(camX, camY, camZ, //eye
                	     lookAt[0], lookAt[1], lookAt[2], // looking at 
                         0.0, 1.0, 0.0); // up   
	}
	
	/**
     * Sets up the lookAt point - could be a specified object's location
     * @param x X coordinate of the lookAt point
     * @param y Y coordinate of the lookAt point
     * @param z Z coordinate of the lookAt point
     */
    public void setLookAt(double x, double y, double z) 
    {
        lookAt = new double[]{x, y, z};
    }
    
    public void setEye(double x, double y, double z)
    {
    	camX = x;
    	camY = y;
    	camZ = z;
    }
	
	 /**
     * Passes a new window size to the camera.
     * This method should be called from the <code>reshape()</code> method
     * of the main program.
     *
     * @param width the new window width in pixels
     * @param height the new window height in pixels
     */
    public void newWindowSize(int width, int height) 
    {
        windowWidth = Math.max(1.0, width);
        windowHeight = Math.max(1.0, height);
    }
}
