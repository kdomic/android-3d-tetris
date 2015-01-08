package com.trippleit.android.tetris3d.shapes;

import javax.microedition.khronos.opengles.GL10;

public class ObjectS extends AbstractDraw implements IShape {

	String color = "#00DEDE";

	public ObjectS() {
	}

	@Override
	public void draw(GL10 gl) {
		gl.glPushMatrix();
		Cube c1 = new Cube(color);
		c1.draw(gl);

		Cube c2 = new Cube(color);
		gl.glTranslatef(1, 0, 0);
		c2.draw(gl);

		Cube c3 = new Cube(color);
		gl.glTranslatef(0, 0, 1);
		c3.draw(gl);

		Cube c4 = new Cube(color);
		gl.glTranslatef(1, 0, 0);
		c4.draw(gl);
		gl.glPopMatrix();
	}

}
