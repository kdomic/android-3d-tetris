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
		GLU.gluLookAt(gl, GameStatus.getCameraX(), GameStatus.getCameraY(),
				GameStatus.getCameraZ(), 0, 0, 0, 0, 0, 1);

		IShape coords = new Coords();
		coords.draw(gl);

		IShape grid = new Grid(GameStatus.getGridSize());
		grid.draw(gl);

		if (firstDraw) {
			newShpe();
		}

		if (getOneSec() == 0) {
			dropDown();
		}

		printAll(gl);

		gl.glPushMatrix();
		gl.glTranslatef(GameStatus.getCurrentObjectX(),
				GameStatus.getCurrentObjectY(), GameStatus.getCurrentObjectZ());
		GameStatus.getCurrentObject().draw(gl);
		gl.glPopMatrix();

	}

	private void printAll(GL10 gl) {
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				for (int k = 0; k < 5; k++)
					if (GameStatus.getGameBoolMatrix()[i][j][k]) {
						gl.glPushMatrix();
						Cube ccc = new Cube();
						gl.glTranslatef(i, j, k);
						ccc.draw(gl);
						gl.glPopMatrix();
					}
	}

	private void newShpe() {
		GameStatus.setCurrentObject(new Cube());
		GameStatus.setCurrentObjectPosition(0, 0, GameStatus.getGameHeight());
	}

	private void dropDown() {
		boolean ret = GameStatus.setCurrentObjectPositionDown();
		if (!ret) {
			GameStatus.savePositionToBoolMatrix();
			newShpe();
		}
	}

}
