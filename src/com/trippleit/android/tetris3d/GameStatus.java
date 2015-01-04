package com.trippleit.android.tetris3d;

import com.trippleit.android.tetris3d.shapes.IShape;

import android.content.Context;

public class GameStatus {

	private static float cameraX, cameraY, cameraZ;

	private static int gameHeight;
	private static int gridSize;

	private static IShape currentObject;
	private static float currentObjectX, currentObjectY, currentObjectZ;

	private static boolean gameStatus[][][];

	public static void init(Context c) {
		gameHeight = 10;
		gridSize = 5;
		restartGameStatus();
		setCamera(-6, 8);
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
		GameStatus.cameraX = 20 * (float) Math.cos(Math.toRadians(r));
		GameStatus.cameraY = 20 * (float) Math.sin(Math.toRadians(r));
		GameStatus.cameraZ = h;
	}

	/**
	 * Postavljanje matrice zauzeća polja, ukoliko nije inicijalizirana
	 * inicijalizira se sa veličinom mreže i visinom igre
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void setGameStatus(int x, int y, int z) {
		if (gameStatus == null) {
			restartGameStatus();
		}
		gameStatus[x][y][z] = true;
	}

	private static void restartGameStatus() {
		gameStatus = new boolean[gridSize][gridSize][gameHeight];
		for (int i = 0; i < gridSize; i++)
			for (int j = 0; j < gridSize; j++)
				for (int k = 0; k < gameHeight; k++)
					gameStatus[i][j][k] = false;
	}

	public static float getCameraX() {
		return cameraX;
	}

	public static float getCameraY() {
		return cameraY;
	}

	public static float getCameraZ() {
		return cameraZ;
	}

	public static boolean[][][] getGameStatus() {
		return gameStatus;
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

	public static float getCurrentObjectX() {
		return currentObjectX;
	}

	public static void setCurrentObjectX(float currentObjectX) {
		GameStatus.currentObjectX = currentObjectX;
	}

	public static float getCurrentObjectY() {
		return currentObjectY;
	}

	public static void setCurrentObjectY(float currentObjectY) {
		GameStatus.currentObjectY = currentObjectY;
	}

	public static float getCurrentObjectZ() {
		return currentObjectZ;
	}

	public static void setCurrentObjectZ(float currentObjectZ) {
		GameStatus.currentObjectZ = currentObjectZ;
	}

	public static int getGameHeight() {
		return gameHeight;
	}

	public static void setGameHeight(int gameHeight) {
		GameStatus.gameHeight = gameHeight;
	}

}
