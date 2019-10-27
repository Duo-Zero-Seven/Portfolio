package scene;
import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

/***
 * 
 * @author wdg7741 Timothy Grey
 *
 */
public class Origin 
{
	GLUT glut = new GLUT();
	
	public void draw(GL gl)
	{
		gl.glPushMatrix();
				//X
			gl.glBegin(GL.GL_LINES);
				gl.glColor3f(1, 0, 0);
				gl.glVertex3f(1, 0, 0);
				gl.glVertex3f(100, 0, 0);
			gl.glEnd();
				//Y
			gl.glBegin(GL.GL_LINES);
				gl.glColor3f(0, 1, 0);
				gl.glVertex3f( 0, 1, 0);
				gl.glVertex3f( 0, 100, 0);
			gl.glEnd();
				//Z
			gl.glBegin(GL.GL_LINES);
				gl.glColor3f(0, 0, 1);
				gl.glVertex3f( 0, 0, 1);
				gl.glVertex3f( 0, 0, 100);
			gl.glEnd();
			
			//Centre Sphere
			gl.glColor3d(0, 0, 0);
			glut.glutSolidSphere(0.2, 45, 180);
		gl.glPopMatrix();
	}
}
