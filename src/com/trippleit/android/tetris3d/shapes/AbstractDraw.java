package com.trippleit.android.tetris3d.shapes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public abstract class AbstractDraw {
	
	public void drawObject(GL10 gl, boolean objectMatrix[][][], String color){
		gl.glPushMatrix();
		Cube c = new Cube(color);
		for (int i = 0; i < objectMatrix.length; i++)
			for (int j = 0; j < objectMatrix.length; j++)
				for (int k = 0; k < objectMatrix.length; k++)
					if(objectMatrix[i][j][k] == true){
						gl.glPushMatrix();
						gl.glTranslatef(i, j, k);
						c.draw(gl);
						gl.glPopMatrix();
					}
		gl.glPopMatrix();
	}
	
	public boolean[][][] rotateX(boolean objectMatrix[][][]){
		boolean rotatedMatrix[][][] = createFalsMatrix(objectMatrix.length);
		for (int i = 0; i < objectMatrix.length; i++)
			for (int j = 0; j < objectMatrix.length; j++)
				for (int k = 0; k < objectMatrix.length; k++){
					//TODO modifikacija indeksa (fiksni indeks: j)
					rotatedMatrix[i][j][k] = objectMatrix[i][j][k];				
				}
		return rotatedMatrix;
	}
	
	public boolean[][][] rotateY(boolean objectMatrix[][][]){
		boolean rotatedMatrix[][][] = createFalsMatrix(objectMatrix.length);
		for (int i = 0; i < objectMatrix.length; i++)
			for (int j = 0; j < objectMatrix.length; j++)
				for (int k = 0; k < objectMatrix.length; k++){
					//TODO modifikacija indeksa (fiksni indeks: i)
					rotatedMatrix[i][j][k] = objectMatrix[i][j][k];				
				}
		return rotatedMatrix;
	}
	
	public boolean[][][] rotateZ(boolean objectMatrix[][][]){
		boolean rotatedMatrix[][][] = createFalsMatrix(objectMatrix.length);
		for (int i = 0; i < objectMatrix.length; i++)
			for (int j = 0; j < objectMatrix.length; j++)
				for (int k = 0; k < objectMatrix.length; k++){
					//TODO modifikacija indeksa (fiksni indeks: k)
					rotatedMatrix[i][j][k] = objectMatrix[i][j][k];				
				}
		return rotatedMatrix;
	}
	
	public void draw(GL10 gl, FloatBuffer vertexBuffer,
			ShortBuffer indexBuffer, short[] indices) {
		// Counter-clockwise winding.
		gl.glFrontFace(GL10.GL_CCW);
		// Enable face culling.
		gl.glEnable(GL10.GL_CULL_FACE);
		// What faces to remove with the face culling.
		gl.glCullFace(GL10.GL_BACK);

		// Enabled the vertices buffer for writing and to be used during
		// rendering.
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// Specifies the location and data format of an array of vertex
		// coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
				GL10.GL_UNSIGNED_SHORT, indexBuffer);

		// Disable the vertices buffer.
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		// Disable face culling.
		gl.glDisable(GL10.GL_CULL_FACE);
	}

	public void draw(GL10 gl, FloatBuffer vertexBuffer,
			ShortBuffer indexBuffer, short[] indices, FloatBuffer colorBuffer) {

		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
				GL10.GL_UNSIGNED_SHORT, indexBuffer);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);

	}

	public void drawLines(GL10 gl, FloatBuffer vertexBuffer,
			ShortBuffer indexBuffer, short[] indices, FloatBuffer colorBuffer) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

		gl.glLineWidth(1);
		gl.glDrawElements(GL10.GL_LINES, indices.length,
				GL10.GL_UNSIGNED_SHORT, indexBuffer);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}

	public FloatBuffer floatToFloatBuffer(float[] vertices) {
		FloatBuffer vertexBuffer;
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		return vertexBuffer;
	}

	public ShortBuffer shortToShortBuffer(short[] indices) {
		ShortBuffer indexBuffer;
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer = ibb.asShortBuffer();
		indexBuffer.put(indices);
		indexBuffer.position(0);
		return indexBuffer;
	}

	public float[] convertColor(String color, int colorArrayLength) {
		float colors[] = new float[colorArrayLength];
		float cR = Integer.valueOf(color.substring(1, 3), 16) / 255.0f;
		float cG = Integer.valueOf(color.substring(3, 5), 16) / 255.0f;
		float cB = Integer.valueOf(color.substring(5, 7), 16) / 255.0f;

		for (int i = 0; i < colorArrayLength; i += 4) {
			colors[i] = cR;
			colors[i + 1] = cG;
			colors[i + 2] = cB;
			colors[i + 3] = 1.0f;
		}
		return colors;
	}

	public boolean[][][] createFalsMatrix(int n) {
		boolean objectMatrix[][][] = new boolean[n][n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++)
					objectMatrix[i][j][k] = false;
		return objectMatrix;

	}

	public int getXsize(boolean[][][] matrix) {
		int object_x_size = 0;
		for (int i = 0; i < matrix.length; i++) {
			boolean ok = false;
			for (int j = 0; j < matrix.length; j++) {
				for (int k = 0; k < matrix.length; k++) {
					if (matrix[i][j][k] == true)
						ok = true;
				}
			}
			if (ok) {
				object_x_size++;
			}
		}
		return object_x_size;
	}

	public int getYsize(boolean[][][] matrix) {
		int object_y_size = 0;
		for (int j = 0; j < matrix.length; j++) {
			boolean ok = false;
			for (int i = 0; i < matrix.length; i++) {
				for (int k = 0; k < matrix.length; k++) {
					if (matrix[i][j][k] == true)
						ok = true;
				}
			}
			if (ok) {
				object_y_size++;
			}
		}
		return object_y_size;
	}

}
