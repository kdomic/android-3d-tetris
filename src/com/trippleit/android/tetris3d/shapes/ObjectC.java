package com.trippleit.android.tetris3d.shapes;

import javax.microedition.khronos.opengles.GL10;

public class ObjectC extends AbstractDraw implements IShape {

	String color = "#AA3939";

	boolean objectMatrix[][][];

	public ObjectC() {
		objectMatrix = createFalsMatrix(2);
		objectMatrix[0][0][0] = true;
		objectMatrix[0][1][0] = true;
		objectMatrix[1][0][0] = true;
		objectMatrix[1][1][0] = true;
	}

	@Override
	public void draw(GL10 gl) {
		drawObject(gl, objectMatrix, color);
		/*gl.glPushMatrix();		
		Cube c = new Cube(color);		
		c.draw(gl);
		gl.glTranslatef(1, 0, 0);
		c.draw(gl);
		gl.glTranslatef(0, 1, 0);
		c.draw(gl);
		gl.glTranslatef(-1, 0, 0);
		c.draw(gl);		
		gl.glPopMatrix();*/
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

