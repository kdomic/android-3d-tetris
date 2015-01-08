package com.trippleit.android.tetris3d.shapes;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cube extends AbstractDraw implements IShape {

	private float vertices[] = { 0.0f, 0.0f, 0.0f, // 0
			1.0f, 0.0f, 0.0f, // 1
			1.0f, 1.0f, 0.0f, // 2
			0.0f, 1.0f, 0.0f, // 3
			0.0f, 0.0f, 1.0f, // 4
			1.0f, 0.0f, 1.0f, // 5
			1.0f, 1.0f, 1.0f, // 6
			0.0f, 1.0f, 1.0f }; // 7

	private short[] indices = { 0, 1, 3, 3, 1, 2, 4, 5, 7, 7, 5, 6, 0, 1, 5, 5,
			4, 0, 6, 2, 3, 7, 6, 3, 1, 2, 5, 6, 5, 2, 4, 3, 0, 3, 4, 7 };
	private short[] lineIndices = { 0, 1, 1, 2, 2, 3, 3, 0, 4, 5, 5, 6, 6, 7,
			7, 4, 0, 4, 1, 5, 2, 6, 3, 7 };

	private FloatBuffer vertexBuffer;

	private ShortBuffer indexBuffer;
	private ShortBuffer lineIndexBuffer;

	String lineColor = "#000000";
	private FloatBuffer colorBuffer;
	private FloatBuffer colorLineBuffer;

	public Cube() {
		init();
		colorBuffer = null;
	}

	public Cube(String color) {
		init();
		colorBuffer = floatToFloatBuffer(convertColor(color, vertices.length
				+ vertices.length / 3));
	}

	private void init() {
		vertexBuffer = floatToFloatBuffer(vertices);
		indexBuffer = shortToShortBuffer(indices);
		lineIndexBuffer = shortToShortBuffer(lineIndices);
		colorLineBuffer = floatToFloatBuffer(convertColor(lineColor,
				vertices.length + vertices.length / 3));
	}

	@Override
	public void draw(GL10 gl) {
		if (colorBuffer == null)
			draw(gl, vertexBuffer, indexBuffer, indices);
		else
			draw(gl, vertexBuffer, indexBuffer, indices, colorBuffer);
		drawLines(gl, vertexBuffer, lineIndexBuffer, lineIndices,
				colorLineBuffer);
	}

}
