package com.trippleit.android.tetris3d.shapes;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class ObjectI extends AbstractDraw implements IShape {

	private float vertices[] = { 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
			1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 3.0f, 1.0f, 0.0f, 3.0f,
			1.0f, 1.0f, 3.0f, 0.0f, 1.0f, 3.0f };

	private short[] indices = { 0, 1, 3, 3, 1, 2, 4, 5, 7, 7, 5, 6, 0, 1, 5, 5,
			4, 0, 6, 2, 3, 7, 6, 3, 1, 2, 5, 6, 5, 2, 4, 3, 0, 3, 4, 7 };

	private FloatBuffer vertexBuffer;

	private ShortBuffer indexBuffer;

	public ObjectI() {
		vertexBuffer = floatToFloatBuffer(vertices);
		indexBuffer = shortToShortBuffer(indices);
	}

	@Override
	public void draw(GL10 gl) {
		draw(gl, vertexBuffer, indexBuffer, indices);
	}

}

