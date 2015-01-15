package com.trippleit.android.tetris3d.shapes;

import javax.microedition.khronos.opengles.GL10;

public class ObjectS extends AbstractDraw implements IShape {

	String color = "#00DEDE";
	
	boolean objectMatrix[][][];

	public ObjectS() {
		objectMatrix = createFalsMatrix(3);
		objectMatrix[0][0][0] = true;
		objectMatrix[1][0][0] = true;
		objectMatrix[1][0][1] = true;
		objectMatrix[2][0][1] = true;
	}

	@Override
	public void draw(GL10 gl) {
		drawObject(gl, objectMatrix, color);
		/*gl.glPushMatrix();		
		Cube c = new Cube(color);		
		c.draw(gl);
		gl.glTranslatef(1, 0, 0);
		c.draw(gl);
		gl.glTranslatef(0, 0, 1);
		c.draw(gl);
		gl.glTranslatef(1, 0, 0);
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
	
	@Override
	public int getZsize() {
		return getZsize(objectMatrix);
	}
	
	@Override
	public void rotate(int axis) {
		switch(axis){
			case 1:
			case 'x':
				objectMatrix = rotateX(objectMatrix); break;
			case 2:
			case 'y':
				objectMatrix = rotateY(objectMatrix); break;
			case 3:
			case 'z':
				objectMatrix = rotateZ(objectMatrix); break;				
		}
	}

}
