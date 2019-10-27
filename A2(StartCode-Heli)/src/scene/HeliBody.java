package scene;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/***
 * 
 * @author wdg7741 Timothy Grey
 *
 */
public class HeliBody //extends Origin removed as origin and helibody are not the same type of thing
{
	GLU glu = new GLU();
	GLUquadric quadric = glu.gluNewQuadric();
	
	public float[] bodyOrigin = {0,0,0}; 
	public static float angle = 0.0f;
	
	//pitch yaw angles
	private float pAngle = 0.0f;
	private float yAngle = 0.0f;
	
	//material arrays
	public float [] ambienceRating = {0,0,0,0};
	public float [] diffuseRating = {0,0,0,0};
	public float [] specularRating = {0,0,0,0};
	public float shineRating = 0.0f;
	
	public void glSetMaterialType(GL gl) //set materials
	{
		//set colour to 4d before applying
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, ambienceRating, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, diffuseRating, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, specularRating, 0);
		gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, shineRating);
	}
	
	//set material to ruby (body)
	public void setMaterialToRuby(GL gl)
	{
		ambienceRating[0] = 0.1745f;
		ambienceRating[1] = 0.01175f;
		ambienceRating[2] = 0.01175f;
		ambienceRating[3] = 0.55f;
		diffuseRating[0] = 0.61424f;
		diffuseRating[1] = 0.04136f;
		diffuseRating[2] = 0.04136f;
		diffuseRating[3] = 0.55f;
		specularRating[0] = 0.727811f;
		specularRating[1] = 0.626959f;
		specularRating[2] = 0.626959f;
		specularRating[3] = 0.55f;
		shineRating = 76.8f;
		glSetMaterialType(gl);
	}
	
	//set material to obsidian (rotors)
	public void setMaterialToObsidian(GL gl)
	{
		ambienceRating[0] = 0.05375f;
		ambienceRating[1] = 0.05f;
		ambienceRating[2] = 0.06625f;
		ambienceRating[3] = 0f;
		diffuseRating[0] = 0.18275f;
		diffuseRating[1] = 0.17f;
		diffuseRating[2] = 0.22525f;
		diffuseRating[3] = 0f;
		specularRating[0] = 0.332741f;
		specularRating[1] = 0.328634f;
		specularRating[2] = 0.346435f;
		specularRating[3] = 0f;
		shineRating = 0.3f;
		glSetMaterialType(gl);
	}
	
	//set material to yellow rubber (tail)
	public void setMaterialToYellowRubber(GL gl)
	{
		ambienceRating[0] = 0.05f;
		ambienceRating[1] = 0.05f;
		ambienceRating[2] = 0.0f;
		ambienceRating[3] = 0.55f;
		diffuseRating[0] = 0.5f;
		diffuseRating[1] = 0.5f;
		diffuseRating[2] = 0.4f;
		diffuseRating[3] = 0.55f;
		specularRating[0] = 0.7f;
		specularRating[1] = 0.7f;
		specularRating[2] = 0.4f;
		specularRating[3] = 0.55f;
		shineRating = 0.78125f;
		glSetMaterialType(gl);
	}

	public void draw(GL gl)
	{
		gl.glTranslatef(bodyOrigin[0], bodyOrigin[1], bodyOrigin[2]);
		gl.glRotatef(angle, 0, 1, 0);
		gl.glRotatef(pAngle, 1, 0, 0);
		gl.glRotatef(yAngle, 0, 0, 1);

		//Chopper Body
		gl.glPushMatrix();
			gl.glTranslatef(0, 1, 0);
			gl.glColor4d(1, 0, 0, 1); //change to 4d for material application may still work with 3d
			setMaterialToRuby(gl);
			glu.gluSphere(quadric, 1, 45, 180);
		gl.glPopMatrix();
		
		//Tail
		gl.glPushMatrix();
			gl.glTranslatef(0, 1, 0);
			gl.glColor4d(1, 1, 0, 1);
			setMaterialToYellowRubber(gl);
			glu.gluCylinder(quadric, 0.2, 0.2, 2.8, 180, 180);
		gl.glPopMatrix();
		
		//Top rotor cap
		gl.glPushMatrix();
			gl.glTranslatef(0, 2, 0);
			gl.glColor4d(0, 0.5, 0, 1);
			setMaterialToObsidian(gl);
			glu.gluSphere(quadric, 0.2, 45, 180);
		gl.glPopMatrix();
		
		//Rear Rotor Cap
		gl.glPushMatrix();
			gl.glTranslatef(0, 1, 2.75f);
			gl.glColor4d(0, 0.5, 0, 1);
			setMaterialToObsidian(gl);
			glu.gluSphere(quadric, 0.2, 45, 180);
		gl.glPopMatrix();
	}
	
	public void ascend() //Move Up
	{
		bodyOrigin[1] += 0.5; //too small an increment --now works?
	}

	public void descend() //Move Down
	{
		if(bodyOrigin[1] > 0.5)		
		{
			bodyOrigin[1] -= 0.5;
		}
	}
	
	public void pitchForward(boolean forward) //pitch forward
	{
		if(forward)
		{
			if(pAngle >= -25)
			{
				pAngle -= 1f;
			}
		}
		else if(pAngle < 0)
		{
			pAngle += 1f;
		}
	}
	
	public void pitchBackward(boolean backward) //pitch backward
	{
		if(backward)
		{
			if(pAngle <= 25)
			{
				pAngle += 1f;
			}
		}
		else if(pAngle > 0)
		{
			pAngle -= 1f;
		}
	}
	
	public void yawLeft(boolean left) //yaw left
	{
		if(left)
		{
			if(yAngle <= 25)
			{
				yAngle += 1f;
			}
		}
		else if(yAngle > 0)
		{
			yAngle -= 1f;
		}
	}
	
	public void yawRight(boolean right) //yaw right
	{
		if(right)
		{
			if(yAngle >= -25)
			{
				yAngle -= 1f;
			}
		}
		else if(yAngle < 0)
		{
			yAngle += 1f;
		}
	}
	
	public void strafeLeft() //Slide Left
	{
		bodyOrigin[0] -= Math.sin(Math.toRadians(angle+90));
		bodyOrigin[2] -= Math.cos(Math.toRadians(angle+90));
		if (angle < 0) angle += 360;
		if (angle > 360) angle -= 360;
	}

	public void strafeRight() //Slide Right
	{
		bodyOrigin[0] += Math.sin(Math.toRadians(angle+90));
		bodyOrigin[2] += Math.cos(Math.toRadians(angle+90));
		if (angle < 0) angle += 360;
		if (angle > 360) angle -= 360;
	}

	public void forward() //Move Forward
	{
		bodyOrigin[0] -= Math.sin(Math.toRadians(angle));
		bodyOrigin[2] -= Math.cos(Math.toRadians(angle));
		if (angle < 0) angle += 360;
		if (angle > 360) angle -= 360;
	}

	public void backward() //Move Backwards
	{
		bodyOrigin[0] += Math.sin(Math.toRadians(angle));
		bodyOrigin[2] += Math.cos(Math.toRadians(angle));
		if (angle < 0) angle += 360;
		if (angle > 360) angle -= 360;
	}
	
	public void rotateLeft() //Rotate Left
	{
		angle = (angle + 1.5f) % 360;
		if (angle < 0) angle += 360;
		if (angle > 360) angle -= 360;
	}
	
	public void rotateRight() //Rotate Right
	{
		angle = (angle - 1.5f) % 360;
		 if (angle < 0) angle += 360;
		 if (angle > 360) angle -= 360;
	}
	
	public void returnToOrigin() //Return to origin
	{
		bodyOrigin[0] = 0; 
		bodyOrigin[1] = 0; 
		bodyOrigin[2] = 0;
	}
}
