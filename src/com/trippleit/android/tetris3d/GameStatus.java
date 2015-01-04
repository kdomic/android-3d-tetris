package com.trippleit.android.tetris3d;

import com.trippleit.android.tetris3d.shapes.IShape;

import android.content.Context;

public class GameStatus {

	private static float cameraR, cameraH;
	private static float cameraX, cameraY, cameraZ;

	private static int gameHeight;
	private static int gridSize;

	private static IShape currentObject;
	private static int currentObjectX, currentObjectY, currentObjectZ;

	private static boolean gameBoolMatrix[][][];

	public static void init(Context c) {
		gameHeight = 10;
		gridSize = 5;
		restartGameBoolMatrix();
		setCamera(-65, 10);
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
	
	private static void calculateCamera(){
		GameStatus.cameraX = 15 * (float) Math.cos(Math.toRadians(GameStatus.cameraR));
		GameStatus.cameraY = 15 * (float) Math.sin(Math.toRadians(GameStatus.cameraR));
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
		gameBoolMatrix = new boolean[gridSize][gridSize][gameHeight];
		for (int i = 0; i < gridSize; i++)
			for (int j = 0; j < gridSize; j++)
				for (int k = 0; k < gameHeight; k++)
					gameBoolMatrix[i][j][k] = false;
	}

	public static boolean[][][] getGameBoolMatrix() {
		return gameBoolMatrix;
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
		if (currentObjectX + 1 < gridSize) {
			currentObjectX++;
			return true;
		}
		return false;
	}

	public static boolean setCurrentXPositionNeg() {
		if (currentObjectX - 1 >= 0) {
			currentObjectX--;
			return true;
		}
		return false;
	}

	public static boolean setCurrentYPositionPos() {
		if (currentObjectY + 1 < gridSize) {
			currentObjectY++;
			return true;
		}
		return false;
	}

	public static boolean setCurrentYPositionNeg() {
		if (currentObjectY - 1 >= 0) {
			currentObjectY--;
			return true;
		}
		return false;
	}

	public static boolean setCurrentObjectPositionDown() {
		if (currentObjectZ <= 0) {
			return false;
		}
		if (gameBoolMatrix[currentObjectX][currentObjectY][currentObjectZ - 1] == true) {
			return false;
		}
		currentObjectZ--;
		return true;
	}

	public static void savePositionToBoolMatrix() {
		gameBoolMatrix[currentObjectX][currentObjectY][currentObjectZ] = true;
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

}
