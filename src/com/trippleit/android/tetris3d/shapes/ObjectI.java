package com.trippleit.android.tetris3d.shapes;

import javax.microedition.khronos.opengles.GL10;

public class ObjectI extends AbstractDraw implements IShape {

	String color = "#FF7400";

	boolean objectMatrix[][][];

	public ObjectI() {
		objectMatrix = createFalsMatrix(4);
		objectMatrix[0][0][0] = true;
		objectMatrix[0][0][1] = true;
		objectMatrix[0][0][2] = true;
		objectMatrix[0][0][3] = true;
	}

	@Override
	public void draw(GL10 gl) {
		gl.glPushMatrix();

		Cube c = new Cube(color);

		for (int i = 0; i < 4; i++) {
			c.draw(gl);
			gl.glTranslatef(0, 0, 1);
		}

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
