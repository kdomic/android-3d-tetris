package com.trippleit.android.tetris3d.shapes;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Coords extends AbstractDraw implements IShape {

	private FloatBuffer vertexBuffer;
	private float vertices[] = { 0.0f, 0.0f, 0.0f, 5.0f, 0.0f, 0.0f, 0.0f,
			5.0f, 0.0f, 0.0f, 0.0f, 5.0f };

	private ShortBuffer indexBuffer;
	private short[] indices = { 0, 1, 0, 2, 0, 3 };

	private FloatBuffer colorBuffer;
	float colors[] = { 0.5f, 0.5f, 0.5f, 0.5f, 1f, 0f, 0f, 1f, 0f, 1f, 0f, 1f,
			0f, 0f, 1f, 1f };

	public Coords() {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getXsize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getYsize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
