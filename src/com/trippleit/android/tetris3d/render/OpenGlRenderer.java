package com.trippleit.android.tetris3d.render;

import javax.microedition.khronos.opengles.GL10;

import com.trippleit.android.tetris3d.GameStatus;
import com.trippleit.android.tetris3d.shapes.Coords;
import com.trippleit.android.tetris3d.shapes.Cube;
import com.trippleit.android.tetris3d.shapes.Grid;
import com.trippleit.android.tetris3d.shapes.IShape;

import android.opengl.GLU;

public class OpenGlRenderer extends AbstractOpenGlRenderer {


	@Override
	public void onDrawFrame(GL10 gl, boolean firstDraw) {
		GLU.gluLookAt(gl, GameStatus.getCameraX(), GameStatus.getCameraY(), GameStatus.getCameraZ(), 0, 0, 0, 0, 0, 1);
		
		IShape coords = new Coords();
		coords.draw(gl);
		
		IShape grid = new Grid(GameStatus.getGridSize());
		grid.draw(gl);
		
		IShape cube = new Cube();
		cube.draw(gl);
	}

}
