package com.trippleit.android.tetris3d.shapes;

import javax.microedition.khronos.opengles.GL10;

public class ObjectL extends AbstractDraw implements IShape {

	String color = "#1D1DE7";

	boolean objectMatrix[][][];

	public ObjectL() {
		objectMatrix = createFalsMatrix(3);
		objectMatrix[0][0][0] = true;
		objectMatrix[0][0][1] = true;
		objectMatrix[0][0][2] = true;
		objectMatrix[1][0][0] = true;
	}

	@Override
	public void draw(GL10 gl) {
		gl.glPushMatrix();

		Cube c = new Cube(color);

		gl.glTranslatef(1, 0, 0);
		c.draw(gl);
		gl.glTranslatef(-1, 0, 0);
		c.draw(gl);
		gl.glTranslatef(0, 0, 1);
		c.draw(gl);
		gl.glTranslatef(0, 0, 1);
		c.draw(gl);

		gl.glPopMatrix();
	}

	@Override
	public boolean[][][] getObjectMatrix() {
		return objectMatrix;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public int getXsize() {
		return getXsize(objectMatrix);
	}

	@Override
	public int getYsize() {
		return getYsize(objectMatrix);
	}

}

