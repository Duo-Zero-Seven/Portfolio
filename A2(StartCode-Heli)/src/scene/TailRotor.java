package scene;
import javax.media.opengl.GL;

/***
 * 
 * @author wdg7741 Timothy Grey
 *
 */
public class TailRotor //extends HeliBody
{
	public float tailRAngle = 0.0f;
	
	public void draw(GL gl)
	{
		//Rear Rotors Location Process
		gl.glPushMatrix();
			gl.glTranslatef(0, 1, 2.75f);
			gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
			gl.glRotatef(tailRAngle, 0, 1, 0);
			//Rear Rotor Draw Specifications
			gl.glColor3d(1, 0, 1); 
				gl.glBegin(GL.GL_QUADS);
				gl.glVertex3d(-0.05, 0, .75);
				gl.glVertex3d(0.05, 0, .75);
				gl.glVertex3d(0.05, 0, -.75);
				gl.glVertex3d(-0.05, 0, -.75);
				gl.glVertex3d(-.75, 0, 0.05);
				gl.glVertex3d(.75, 0, 0.05);
				gl.glVertex3d(.75, 0, -0.05);
				gl.glVertex3d(-.75, 0, -0.05);
				gl.glEnable(GL.GL_NORMALIZE);
			gl.glEnd();	
		gl.glPopMatrix();
	}
}
