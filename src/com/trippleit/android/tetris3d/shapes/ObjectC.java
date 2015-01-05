package com.trippleit.android.tetris3d.shapes;

import javax.microedition.khronos.opengles.GL10;

public class ObjectC extends AbstractDraw implements IShape {

	public ObjectC(){
	}

	@Override
	public void draw(GL10 gl) {
		gl.glPushMatrix();
		Cube c1 = new Cube();
		c1.draw(gl);
		
		Cube c2 = new Cube();
		gl.glTranslatef(1, 0, 0);
		c2.draw(gl);
		
		Cube c3 = new Cube();
		gl.glTranslatef(0, 1, 0);
		c3.draw(gl);
		
		Cube c4 = new Cube();
		gl.glTranslatef(-1, 0, 0);
		c4.draw(gl);
		gl.glPopMatrix();
	}

}
