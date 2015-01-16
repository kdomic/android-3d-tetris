package com.trippleit.android.tetris3d;

import java.util.ArrayList;

import com.trippleit.android.tetris3d.shapes.IShape;

import android.content.Context;
import android.util.Log;

public class GameStatus {

	private static float cameraR, cameraH;
	private static float cameraX, cameraY, cameraZ;

	private static int gameHeight;
	private static int gridSize;
	private static int startX, startY;

	private static IShape currentObject;
	private static int currentObjectX, currentObjectY, currentObjectZ;

	private static boolean gameBoolMatrix[][][];
	private static String gameColorMatrix[][][];

	private static boolean dropFast;
	private static boolean end;	
	
	public static void init(Context _c) {
		gameHeight = 10;
		gridSize = 5;
		restartGameBoolMatrix();
		setCamera(-65, 10);
		end = false;
		startX = 2;
		startY = 2;
		dropFast = false;
	}

	/**
	 * Postavi kameru
	 * 
	 * @param r
	 *            Radijus kružnice na kojoj je kamera
	 * @param h
	 *            Visina kammere
	 */
	public static void setCamera(float r, float h) {
		GameStatus.cameraR = r;
		GameStatus.cameraH = h;
		calculateCamera();
	}

	private static void calculateCamera() {
		GameStatus.cameraX = 15 * (float) Math.cos(Math
				.toRadians(GameStatus.cameraR));
		GameStatus.cameraY = 15 * (float) Math.sin(Math
				.toRadians(GameStatus.cameraR));
		GameStatus.cameraZ = GameStatus.cameraH;
	}

	public static float getCameraX() {
		calculateCamera();
		return cameraX;
	}

	public static float getCameraY() {
		calculateCamera();
		return cameraY;
	}

	public static float getCameraZ() {
		calculateCamera();
		return cameraZ;
	}

	public static float getCameraR() {
		return cameraR;
	}

	public static void setCameraR(float cameraR) {
		GameStatus.cameraR = cameraR;
		calculateCamera();
	}

	public static float getCameraH() {
		return cameraH;
	}

	public static void setCameraH(float cameraH) {
		GameStatus.cameraH = cameraH;
		calculateCamera();
	}

	/**
	 * Postavljanje matrice zauzeća polja, ukoliko nije inicijalizirana
	 * inicijalizira se sa veličinom mreže i visinom igre
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void setGameBoolMatrix(int x, int y, int z) {
		if (gameBoolMatrix == null) {
			restartGameBoolMatrix();
		}
		gameBoolMatrix[x][y][z] = true;
	}

	private static void restartGameBoolMatrix() {
		int objectBuffer = 5;
		gameBoolMatrix = new boolean[gridSize][gridSize][gameHeight
				+ objectBuffer];
		gameColorMatrix = new String[gridSize][gridSize][gameHeight
				+ objectBuffer];
		for (int i = 0; i < gridSize; i++)
			for (int j = 0; j < gridSize; j++)
				for (int k = 0; k < gameHeight + objectBuffer; k++) {
					gameBoolMatrix[i][j][k] = false;
					gameColorMatrix[i][j][k] = "#000000";
				}
	}

	public static boolean[][][] getGameBoolMatrix() {
		return gameBoolMatrix;
	}

	public static String[][][] getGameColorMatrix() {
		return gameColorMatrix;
	}

	public static void setGameColorMatrix(String[][][] gameColorMatrix) {
		GameStatus.gameColorMatrix = gameColorMatrix;
	}

	public static int getGridSize() {
		return gridSize;
	}

	public static void setGridSize(int gridSize) {
		GameStatus.gridSize = gridSize;
	}

	public static IShape getCurrentObject() {
		return currentObject;
	}

	public static void setCurrentObject(IShape currentObject) {
		GameStatus.currentObject = currentObject;
	}

	public static void setCurrentPosition(int x, int y, int z) {
		currentObjectX = x;
		currentObjectY = y;
		currentObjectZ = z;
	}

	public static boolean setCurrentXPositionPos() {
		if (GameStatus.isEnd())
			return false;
		// +1 zato što se želi pomaknuti objekt;
		// -1 zato što je nulto indeksiranje pa objekt treba smanjizi za 1
		if (currentObjectX + currentObject.getXsize() - 1 + 1 < gridSize) {
			if (isAvailable(1))
				currentObjectX++;
			return true;
		}
		return false;
	}

	public static boolean setCurrentXPositionNeg() {
		if (GameStatus.isEnd())
			return false;
		if (currentObjectX - 1 >= 0) {
			if (isAvailable(2))
				currentObjectX--;
			return true;
		}
		return false;
	}

	public static boolean setCurrentYPositionPos() {
		if (GameStatus.isEnd())
			return false;
		// +1 zato što se želi pomaknuti objekt;
		// -1 zato što je nulto indeksiranje pa objekt treba smanjizi za 1
		if (currentObjectY + currentObject.getYsize() - 1 + 1 < gridSize) {
			if (isAvailable(3))
				currentObjectY++;
			return true;
		}
		return false;
	}

	public static boolean setCurrentYPositionNeg() {
		if (GameStatus.isEnd())
			return false;
		if (currentObjectY - 1 >= 0) {
			if (isAvailable(4))
				currentObjectY--;
			return true;
		}
		return false;
	}

	public static boolean isAvailable(int direction) {
		for (int i = 0; i < currentObject.getObjectMatrix().length; i++)
			for (int j = 0; j < currentObject.getObjectMatrix().length; j++)
				for (int k = 0; k < currentObject.getObjectMatrix().length; k++)
					if (currentObject.getObjectMatrix()[i][j][k] == true)
						switch (direction) {
						case 1:
							if (gameBoolMatrix[i + currentObjectX + 1][j
									+ currentObjectY][k + currentObjectZ] == true)
								return false;
							break;
						case 2:
							if (gameBoolMatrix[i + currentObjectX - 1][j
									+ currentObjectY][k + currentObjectZ] == true)
								return false;
							break;
						case 3:
							if (gameBoolMatrix[i + currentObjectX][j
									+ currentObjectY + 1][k + currentObjectZ] == true)
								return false;
							break;
						case 4:
							if (gameBoolMatrix[i + currentObjectX][j
									+ currentObjectY - 1][k + currentObjectZ] == true)
								return false;
							break;
						}
		return true;
	}

	public static boolean setCurrentObjectPositionDown() {
		if (currentObjectZ <= 0) {
			return false;
		}

		for (int i = 0; i < currentObject.getObjectMatrix().length; i++)
			for (int j = 0; j < currentObject.getObjectMatrix().length; j++)
				for (int k = 0; k < currentObject.getObjectMatrix().length; k++)
					if (currentObject.getObjectMatrix()[i][j][k] == true)
						if (gameBoolMatrix[i + currentObjectX][j
								+ currentObjectY][currentObjectZ - 1] == true) {
							if (k != 0)
								currentObjectZ--; // ukoliko kolizija nije na
													// prvoj razini treba
													// dopustiti još jedan drop
							return false;
						}

		currentObjectZ--;
		return true;
	}

	public static void savePositionToBoolMatrix() {
		for (int i = 0; i < currentObject.getObjectMatrix().length; i++)
			for (int j = 0; j < currentObject.getObjectMatrix().length; j++)
				for (int k = 0; k < currentObject.getObjectMatrix().length
						&& k < gameHeight; k++)
					if ((k + currentObjectZ) < gameHeight
							&& currentObject.getObjectMatrix()[i][j][k] == true) {
						gameBoolMatrix[i + currentObjectX][j + currentObjectY][k
								+ currentObjectZ] = true;
						gameColorMatrix[i + currentObjectX][j + currentObjectY][k
								+ currentObjectZ] = currentObject.getColor();
					}
	}

	public static int getCurrentObjectX() {
		return currentObjectX;
	}

	public static void setCurrentObjectX(int currentObjectX) {
		GameStatus.currentObjectX = currentObjectX;
	}

	public static int getCurrentObjectY() {
		return currentObjectY;
	}

	public static void setCurrentObjectY(int currentObjectY) {
		GameStatus.currentObjectY = currentObjectY;
	}

	public static int getCurrentObjectZ() {
		return currentObjectZ;
	}

	public static void setCurrentObjectZ(int currentObjectZ) {
		GameStatus.currentObjectZ = currentObjectZ;
	}

	public static int getGameHeight() {
		return gameHeight;
	}

	public static void setGameHeight(int gameHeight) {
		GameStatus.gameHeight = gameHeight;
	}

	public static boolean isEnd() {
		return end;
	}

	public static boolean checkEnd() {
		end = gameBoolMatrix[startX][startY][gameHeight - 1];
		if (end){
			Log.d("Kruno", "---END---");
		}
		return end;
	}

	public static int getStartX() {
		return startX;
	}

	public static void setStartX(int startX) {
		GameStatus.startX = startX;
	}

	public static int getStartY() {
		return startY;
	}

	public static void setStartY(int startY) {
		GameStatus.startY = startY;
	}	

	public static boolean isDropFast() {
		boolean temp = dropFast;
		dropFast = false;
		return temp;
	}

	public static void setDropFast() {
		GameStatus.dropFast = true;
	}

	public static boolean removeFullRows() {
		ArrayList<Integer> rowsToRemove = new ArrayList<Integer>();
		for (int k = gameHeight; k >= 0; k--) {
			boolean remove = true;
			for (int i = 0; i < gridSize; i++)
				for (int j = 0; j < gridSize; j++)
					if (gameBoolMatrix[i][j][k] == false)
						remove = false;
			if (remove)
				rowsToRemove.add(k);
		}
		if (!rowsToRemove.isEmpty()) {
			removeRows(rowsToRemove);
			return true;
		}
		return false;
	}

	private static void removeRows(ArrayList<Integer> rowsToRemove) {
		for (Integer x : rowsToRemove) {
			for (int k = x; k < gameHeight; k++)
				for (int i = 0; i < gridSize; i++)
					for (int j = 0; j < gridSize; j++)
						if (x == gameHeight - 1){
							gameBoolMatrix[i][j][k] = false;
							gameColorMatrix[i][j][k] = "";
						}else{
							gameBoolMatrix[i][j][k] = gameBoolMatrix[i][j][k + 1];
							gameColorMatrix[i][j][k] = gameColorMatrix[i][j][k + 1];
						}
		}
	}
}
