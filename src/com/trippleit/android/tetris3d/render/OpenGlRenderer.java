package com.trippleit.android.tetris3d.render;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import com.trippleit.android.tetris3d.GameStatus;
import com.trippleit.android.tetris3d.shapes.Coords;
import com.trippleit.android.tetris3d.shapes.Cube;
import com.trippleit.android.tetris3d.shapes.Grid;
import com.trippleit.android.tetris3d.shapes.IShape;
import com.trippleit.android.tetris3d.shapes.ObjectC;
import com.trippleit.android.tetris3d.shapes.ObjectI;
import com.trippleit.android.tetris3d.shapes.ObjectL;
import com.trippleit.android.tetris3d.shapes.ObjectS;
import com.trippleit.android.tetris3d.shapes.ObjectT;
import com.trippleit.android.tetris3d.shapes.ObjectZ;

import android.opengl.GLU;
import android.util.Log;

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
			if(GameStatus.isEnd()==false){
				Log.d("Kruno","dropDown()");
				dropDown();
			}
		}

		printAll(gl);

		gl.glPushMatrix();
		gl.glTranslatef(GameStatus.getCurrentObjectX(),
				GameStatus.getCurrentObjectY(), GameStatus.getCurrentObjectZ());
		GameStatus.getCurrentObject().draw(gl);
		gl.glPopMatrix();

	}

	private void printAll(GL10 gl) {
		for (int i = 0; i < GameStatus.getGridSize(); i++)
			for (int j = 0; j < GameStatus.getGridSize(); j++)
				for (int k = 0; k < GameStatus.getGameHeight(); k++)
					if (GameStatus.getGameBoolMatrix()[i][j][k]) {
						gl.glPushMatrix();
						Cube ccc = new Cube(
								GameStatus.getGameColorMatrix()[i][j][k]);
						gl.glTranslatef(i, j, k);
						ccc.draw(gl);
						gl.glPopMatrix();
					}
	}

	private void newShpe() {
		GameStatus.setCurrentObject(chooseObject(randInt(1, 1)));
		GameStatus.setCurrentPosition(GameStatus.getStartX(), GameStatus.getStartY(), GameStatus.getGameHeight());
	}

	private IShape chooseObject(int shapeId) {
		switch (shapeId) {
		case 0:
		case 'C':
		case 'c':
			return new ObjectC();
		case 1:
		case 'I':
		case 'i':
			return new ObjectI();
		case 2:
		case 'L':
		case 'l':
			return new ObjectL();
		case 3:
		case 'S':
		case 's':
			return new ObjectS();
		case 4:
		case 'T':
		case 't':
			return new ObjectT();
		case 5:
		case 'Z':
		case 'z':
			return new ObjectZ();
		}

		return new Cube();
	}

	private void dropDown() {
		boolean ret = GameStatus.setCurrentObjectPositionDown();
		if (!ret) {
			GameStatus.savePositionToBoolMatrix();
			if (GameStatus.checkEnd() == false) {
				Log.d("Kruno","newShpe()");
				newShpe();
			}
		}
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

}
