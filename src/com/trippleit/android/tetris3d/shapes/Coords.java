package com.trippleit.android.tetris3d.shapes;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Coords extends AbstractDraw implements IShape {
	
	private FloatBuffer vertexBuffer;	
	private float vertices[] = { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f };
	

	private ShortBuffer indexBuffer;
	private short[] indices = { 0, 1, 0, 2, 0, 3 };

	private FloatBuffer colorBuffer;
	float colors[] = { 0.5f, 0.5f, 0.5f, 0.5f, 1f, 0f, 0f, 1f, 0f, 1f, 0f, 1f,
			0f, 0f, 1f, 1f };

	public Coords() {		
		vertices[3] = 5.0f;
		vertices[7] = 5.0f;
		vertices[11] = 5.0f;
		init();
	}
	
	public Coords(int n, int h){		
		vertices[3] = n;
		vertices[7] = n;
		vertices[11] = h;
		init();
	}
	
	private void init(){
		vertexBuffer = floatToFloatBuffer(vertices);
		indexBuffer = shortToShortBuffer(indices);
		colorBuffer = floatToFloatBuffer(colors);
	}

	@Override
	public void draw(GL10 gl) {
		gl.glPushMatrix();
		gl.glTranslatef(-0.05f, -0.05f, 0f);
		drawLines(gl, vertexBuffer, indexBuffer, indices, colorBuffer);
		gl.glPopMatrix();
	}

	@Override
	public boolean[][][] getObjectMatrix() {
		return null;
	}

	@Override
	public String getColor() {
		return null;
	}

	@Override
	public int getXsize() {
		return 0;
	}

	@Override
	public int getYsize() {
		return 0;
	}
	
	@Override
	public int getZsize() {
		return 0;
	}
	
	@Override
	public void rotate(int axis) {

	}

}
