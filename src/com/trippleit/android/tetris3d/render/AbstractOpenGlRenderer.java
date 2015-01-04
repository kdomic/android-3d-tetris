package com.trippleit.android.tetris3d.render;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public abstract class AbstractOpenGlRenderer implements Renderer {


	private boolean DEBUG = true;
	private String DEBUG_TAG = "3DTetris";

	private boolean mFirstDraw;
	private boolean mSurfaceCreated;
	private int mWidth;
	private int mHeight;
	private long mLastTime;
	private int mFPS;

	public AbstractOpenGlRenderer() {
		mFirstDraw = true;
		mSurfaceCreated = false;
		mWidth = -1;
		mHeight = -1;
		mLastTime = System.currentTimeMillis();
		mFPS = 0;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {

		mSurfaceCreated = true;
		mWidth = -1;
		mHeight = -1;

		// Set the background color to black ( rgba ).
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		// Enable Smooth Shading, default not really needed.
		gl.glShadeModel(GL10.GL_SMOOTH);
		// Depth buffer setup.
		gl.glClearDepthf(1.0f);
		// Enables depth testing.
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// The type of depth testing to do.
		gl.glDepthFunc(GL10.GL_LEQUAL);
		// Really nice perspective calculations.
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

		if (DEBUG) {
			Log.i(DEBUG_TAG, "Surface created.");
		}

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {

		if (!mSurfaceCreated && width == mWidth && height == mHeight) {
			if (DEBUG) {
				Log.i(DEBUG_TAG, "Surface changed but already handled.");
			}
			return;
		}
		if (DEBUG) {
			// Android honeycomb has an option to keep the context.
			String msg = "Surface changed width:" + width + " height:" + height;
			if (mSurfaceCreated) {
				msg += " context lost.";
			} else {
				msg += ".";
			}
			Log.i(DEBUG_TAG, msg);
		}

		mWidth = width;
		mHeight = height;
		mSurfaceCreated = false;

		// Sets the current view port to the new size.
		gl.glViewport(0, 0, width, height);
		// Select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// Reset the projection matrix
		gl.glLoadIdentity();
		// Calculate the aspect ratio of the window		
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
		//GLU.gluLookAt(gl, 25, -6, 8, 0, 0, 0, 0, 0, 1);
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// Reset the modelview matrix
		gl.glLoadIdentity();

	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		gl.glLoadIdentity();
		gl.glTranslatef(-2.5f, -4f, 0f);
		onDrawFrame(gl, mFirstDraw);

		if (DEBUG) {
			mFPS++;
			long currentTime = System.currentTimeMillis();
			if (currentTime - mLastTime >= 1000) {
				// Log.i("FPS", "FPS: "+Integer.toString(mFPS));
				mFPS = 0;
				mLastTime = currentTime;
			}
		}

		if (mFirstDraw) {
			mFirstDraw = false;
		}

	}

	public int getFPS() {
		return mFPS;
	}

	public abstract void onDrawFrame(GL10 gl, boolean firstDraw);

}