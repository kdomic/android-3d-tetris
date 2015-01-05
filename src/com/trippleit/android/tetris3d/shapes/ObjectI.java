package com.trippleit.android.tetris3d.shapes;

import javax.microedition.khronos.opengles.GL10;

public class ObjectI extends AbstractDraw implements IShape {

	public ObjectI(){
	}

	@Override
	public void draw(GL10 gl) {
		gl.glPushMatrix();
		Cube c1 = new Cube();
		c1.draw(gl);
		
		Cube c2 = new Cube();
		gl.glTranslatef(0, 0, 1);
		c2.draw(gl);
		
		Cube c3 = new Cube();
		gl.glTranslatef(0, 0, 1);
		c3.draw(gl);
		gl.glPopMatrix();
	}

}
