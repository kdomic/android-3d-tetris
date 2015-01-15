package com.trippleit.android.tetris3d.shapes;

import javax.microedition.khronos.opengles.GL10;

public interface IShape {
	
	public void draw(GL10 gl);
	public boolean[][][] getObjectMatrix();
	public String getColor();
	public int getXsize();
	public int getYsize();
	public int getZsize();
	public void rotate(int axis);
}
