package scene;
import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

/***
 * 
 * @author wdg7741 Timothy Grey
 *
 */
public class Ground //extends Origin 
{	
	private GLUquadric quadric;
	private GLU glu;
	
	private Texture grass;
	private Texture sky;
    
	private boolean groundType 	= false;
    private boolean clampS 	= false;
    private boolean clampT 	= false;
    
    public Ground()
    {
    	glu = new GLU();
    	quadric = glu.gluNewQuadric();
    	try
		{
			sky = TextureIO.newTexture(new File("./textures/Elegant-Sky-Texture-for-Free-Download-520x520.jpg"), true);
			grass = TextureIO.newTexture(new File("./textures/grass-texture-9.jpg"), true);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
    }
	
	public void draw(GL gl) throws RuntimeException
	{
		gl.glPushMatrix();
			grass.setTexParameteri(GL.GL_TEXTURE_WRAP_S, clampS ? GL.GL_CLAMP : GL.GL_REPEAT);
			grass.setTexParameteri(GL.GL_TEXTURE_WRAP_T, clampT ? GL.GL_CLAMP : GL.GL_REPEAT);
			grass.enable();
			grass.bind();
			gl.glTranslatef(-50, 0, -50);
			if(groundType) //Fill Grid
			{
				gl.glColor3f(.3f,.3f,.3f);
				
				gl.glBegin(GL.GL_QUADS); //FIXED JW
					gl.glTexCoord2d(0, 0);
					gl.glVertex3f(0, -0.001f, 0);
					gl.glTexCoord2d(1, 0);
					gl.glVertex3f(0, -0.001f, 100);
					gl.glTexCoord2d(1, 1);
					gl.glVertex3f(100, -0.001f, 100);
					gl.glTexCoord2d(0, 1);
					gl.glVertex3f(100, -0.001f, 0);
				gl.glEnd();
				
			}
			grass.disable();
			
			gl.glBegin(GL.GL_LINES); //FIXED JW
				for(int i = 0; i <= 100; i++) //Draw Grid
				{
				    if (i == 0) 
				    { 
				    	gl.glNormal3d(0, 1, 0);
				    	gl.glColor3f(.6f, .3f, .3f); 
				    } 
				    else 
				    { 
				    	gl.glNormal3d(0, 1, 0);
				    	gl.glColor3f(.25f, .25f, .25f); 
				    }
				   
				    gl.glVertex3f(i, 0, 0);
				    gl.glVertex3f(i, 0, 100);
				    
				    if (i == 0) 
				    { 
				    	gl.glNormal3d(0, 1, 0);
				    	gl.glColor3f(.3f, .3f, .6f); 
				    } 
				    else 
				    { 
				    	gl.glNormal3d(0, 1, 0);
				    	gl.glColor3f(.25f, .25f, .25f); 
				    }
				    
				    gl.glVertex3f(0, 0, i);
				    gl.glVertex3f(100, 0, i);
				}
			gl.glEnd();
		gl.glPopMatrix();
		
		drawTexturedSphere(gl);
	}

	//Method to have ground filled or grid layout
	public boolean changeGroundType() 
	{
		if(!groundType)
		{
			groundType = true;
		}
		else
		{
			groundType = false;
		}
		
		return groundType;
	}
	
	private void drawTexturedSphere(GL gl)
    {
        gl.glPushMatrix();
	        sky.setTexParameteri(GL.GL_TEXTURE_WRAP_S, clampS ? GL.GL_CLAMP : GL.GL_REPEAT);
	        sky.setTexParameteri(GL.GL_TEXTURE_WRAP_T, clampT ? GL.GL_CLAMP : GL.GL_REPEAT);
	        sky.enable();
	        sky.bind();
	        // skydome texture: disable lighting and enlarge sphere
	        gl.glDisable(GL.GL_LIGHTING);
	        gl.glColor3d(1, 1, 1);
	        gl.glScaled(50.0, 50.0, 50.0);
	        gl.glRotated(180, 0, 1, 0); // rotate upright to Y axis
	        gl.glRotated(-90, 1, 0, 0);
	        glu.gluQuadricTexture(quadric, true);
	        glu.gluSphere(quadric, 1.0, 40, 20);
	        sky.disable();
        gl.glPopMatrix();
    }
    
}
