package scene;
import javax.media.opengl.GL;

/***
 * 
 * @author wdg7741 Timothy Grey
 *
 */
public class TopRotor //extends HeliBody
{
	public float topRAngle = 0.0f; //Rotor rotation angle
	
	public float [] ambienceRating = {0,0,0,0};
	public float [] diffuseRating = {0,0,0,0};
	public float [] specularRating = {0,0,0,0};
	public float shineRating = 0.0f;
	
	public void glSetMaterialType(GL gl)
	{
		//set colour to 4d before applying
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, ambienceRating, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, diffuseRating, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, specularRating, 0);
		gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, shineRating);
	}
	
	public void setMaterialToObsidian(GL gl) //set material to obsidian
	{
		ambienceRating[0] = 0.05375f;
		ambienceRating[1] = 0.05f;
		ambienceRating[2] = 0.06625f;
		ambienceRating[3] = 0.55f;
		diffuseRating[0] = 0.18275f;
		diffuseRating[1] = 0.17f;
		diffuseRating[2] = 0.22525f;
		diffuseRating[3] = 0.55f;
		specularRating[0] = 0.332741f;
		specularRating[1] = 0.328634f;
		specularRating[2] = 0.346435f;
		specularRating[3] = 0.55f;
		shineRating = 0.3f;
		glSetMaterialType(gl);
	}
	
	public void draw(GL gl)
	{
		//Top Rotors Location Process
		gl.glPushMatrix();
			gl.glTranslatef(0, 2, 0);
			gl.glRotatef(topRAngle, 0, 1, 0);
			//Top Rotor Draw Specifications
			gl.glColor4d(0, 0, 0, 1); 
			setMaterialToObsidian(gl);
			gl.glBegin(GL.GL_QUADS);
				gl.glVertex3d(-0.15, 0, 2.5);
				gl.glVertex3d(0.15, 0, 2.5);
				gl.glVertex3d(0.15, 0, -2.5);
				gl.glVertex3d(-0.15, 0, -2.5);
				gl.glVertex3d(-2.5, 0, 0.15);
				gl.glVertex3d(2.5, 0, 0.15);
				gl.glVertex3d(2.5, 0, -0.15);
				gl.glVertex3d(-2.5, 0, -0.15);
				gl.glEnable(GL.GL_NORMALIZE);
			gl.glEnd();
		gl.glPopMatrix();
	}
}
