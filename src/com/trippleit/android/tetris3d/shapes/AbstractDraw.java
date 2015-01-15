package com.trippleit.android.tetris3d.shapes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.trippleit.android.tetris3d.GameStatus;

public abstract class AbstractDraw {

	public void drawObject(GL10 gl, boolean objectMatrix[][][], String color) {
		gl.glPushMatrix();
		Cube c = new Cube(color);
		for (int i = 0; i < objectMatrix.length; i++)
			for (int j = 0; j < objectMatrix.length; j++)
				for (int k = 0; k < objectMatrix.length; k++)
					if (objectMatrix[i][j][k] == true) {
						gl.glPushMatrix();
						gl.glTranslatef(i, j, k);
						c.draw(gl);
						gl.glPopMatrix();
					}
		gl.glPopMatrix();
	}

	public boolean[][] rot(boolean[][] a) {
		boolean rotatedMatrix[][] = new boolean[a.length][a.length];
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a.length; j++)
				rotatedMatrix[a.length - (j + 1)][i] = a[i][j];
		return rotatedMatrix;
	}

	public boolean[][][] rotateX(boolean objectMatrix[][][]) {
		if(GameStatus.getCurrentObjectX()+GameStatus.getCurrentObject().getZsize()>=GameStatus.getGridSize()){
			return objectMatrix;
		}
		boolean rotatedMatrix[][][] = createFalsMatrix(objectMatrix.length);
		for (int j = 0; j < objectMatrix.length; j++) {
			boolean temp[][] = new boolean[objectMatrix.length][objectMatrix.length];
			for (int i = 0; i < objectMatrix.length; i++)
				for (int k = 0; k < objectMatrix.length; k++)
					temp[i][k] = objectMatrix[i][j][k];
			temp = rot(temp);
			for (int i = 0; i < objectMatrix.length; i++)
				for (int k = 0; k < objectMatrix.length; k++)
					rotatedMatrix[i][j][k] = temp[i][k];
		}
		return align(rotatedMatrix);
	}

	public boolean[][][] rotateY(boolean objectMatrix[][][]) {
		if(GameStatus.getCurrentObjectY()+GameStatus.getCurrentObject().getZsize()>=GameStatus.getGridSize()){
			return objectMatrix;
		}
		boolean rotatedMatrix[][][] = createFalsMatrix(objectMatrix.length);
		for (int i = 0; i < objectMatrix.length; i++) {
			boolean temp[][] = new boolean[objectMatrix.length][objectMatrix.length];
			for (int j = 0; j < objectMatrix.length; j++)
				for (int k = 0; k < objectMatrix.length; k++)
					temp[j][k] = objectMatrix[i][j][k];
			temp = rot(temp);
			for (int j = 0; j < objectMatrix.length; j++)
				for (int k = 0; k < objectMatrix.length; k++)
					rotatedMatrix[i][j][k] = temp[j][k];
		}
		return align(rotatedMatrix);
	}

	public boolean[][][] rotateZ(boolean objectMatrix[][][]) {
		if(GameStatus.getCurrentObjectY()+GameStatus.getCurrentObject().getXsize()>GameStatus.getGridSize()){
			return objectMatrix;
		}
		if(GameStatus.getCurrentObjectX()+GameStatus.getCurrentObject().getYsize()>GameStatus.getGridSize()){
			return objectMatrix;
		}				
		boolean rotatedMatrix[][][] = createFalsMatrix(objectMatrix.length);
		for (int k = 0; k < objectMatrix.length; k++) {
			boolean temp[][] = new boolean[objectMatrix.length][objectMatrix.length];
			for (int i = 0; i < objectMatrix.length; i++)
				for (int j = 0; j < objectMatrix.length; j++)
					temp[i][j] = objectMatrix[i][j][k];
			temp = rot(temp);
			for (int i = 0; i < objectMatrix.length; i++)
				for (int j = 0; j < objectMatrix.length; j++)
					rotatedMatrix[i][j][k] = temp[i][j];
		}
		return align(rotatedMatrix);
	}

	public boolean[][][] align(boolean rotatedMatrix[][][]) {
		rotatedMatrix = alignI(rotatedMatrix);
		rotatedMatrix = alignJ(rotatedMatrix);
		rotatedMatrix = alignK(rotatedMatrix);		
		return rotatedMatrix;
	}
	
	public boolean[][][] alignI(boolean rotatedMatrix[][][]) {
		int rNum = 0;
		for (int i = 0; i < rotatedMatrix.length; i++) {
			boolean remove = true;
			for (int k = 0; k < rotatedMatrix.length; k++){
				for (int j = 0; j < rotatedMatrix.length; j++)
					if (rotatedMatrix[i][j][k] == true){
						remove = false;
						break;
					}
				if(remove==false) break;
			}
			if (remove) rNum++;
			else break;
		}
		for (int i = rNum; i < rotatedMatrix.length; i++)
			for (int k = 0; k < rotatedMatrix.length; k++)
				for (int j = 0; j < rotatedMatrix.length; j++)
					rotatedMatrix[i-rNum][j][k] = rotatedMatrix[i][j][k];
		
		for (int i = rotatedMatrix.length - 1; i > rotatedMatrix.length - 1 - rNum; i--)
			for (int k = 0; k < rotatedMatrix.length; k++)
				for (int j = 0; j < rotatedMatrix.length; j++)
					rotatedMatrix[i][j][k] = false;
		return rotatedMatrix;
	}
	
	public boolean[][][] alignJ(boolean rotatedMatrix[][][]) {
		int rNum = 0;
		for (int j = 0; j < rotatedMatrix.length; j++) {
			boolean remove = true;
			for (int i = 0; i < rotatedMatrix.length; i++)
				for (int k = 0; k < rotatedMatrix.length; k++){				
					if (rotatedMatrix[i][j][k] == true){
						remove = false;
						break;
					}
				if(remove==false) break;
			}
			if (remove) rNum++;
			else break;
		}
		for (int j = rNum; j < rotatedMatrix.length; j++)
			for (int i = 0; i < rotatedMatrix.length; i++)
				for (int k = 0; k < rotatedMatrix.length; k++)
					rotatedMatrix[i][j-rNum][k] = rotatedMatrix[i][j][k];
		
		for (int j = rotatedMatrix.length - 1; j > rotatedMatrix.length - 1 - rNum; j--)
			for (int i = 0; i < rotatedMatrix.length; i++)
				for (int k = 0; k < rotatedMatrix.length; k++)
					rotatedMatrix[i][j][k] = false;
		return rotatedMatrix;
	}
	
	public boolean[][][] alignK(boolean rotatedMatrix[][][]) {					
		int rNum = 0;
		for (int k = 0; k < rotatedMatrix.length; k++) {
			boolean remove = true;
			for (int i = 0; i < rotatedMatrix.length; i++){
				for (int j = 0; j < rotatedMatrix.length; j++)
					if (rotatedMatrix[i][j][k] == true){
						remove = false;
						break;
					}
				if(remove==false) break;
			}
			if (remove) rNum++;
			else break;
		}
		for (int k = rNum; k < rotatedMatrix.length; k++)
			for (int i = 0; i < rotatedMatrix.length; i++)
				for (int j = 0; j < rotatedMatrix.length; j++)
					rotatedMatrix[i][j][k-rNum] = rotatedMatrix[i][j][k];
		
		for (int k = rotatedMatrix.length - 1; k > rotatedMatrix.length - 1 - rNum; k--)
			for (int i = 0; i < rotatedMatrix.length; i++)
				for (int j = 0; j < rotatedMatrix.length; j++)
					rotatedMatrix[i][j][k] = false;

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
	
	public int getZsize(boolean[][][] matrix) {
		int object_z_size = 0;
		for (int k = 0; k < matrix.length; k++) {
			boolean ok = false;
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					if (matrix[i][j][k] == true)
						ok = true;
				}
			}
			if (ok) {
				object_z_size++;
			}
		}
		return object_z_size;
	}

}
