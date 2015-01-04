package com.trippleit.android.tetris3d;

import com.trippleit.android.tetris3d.render.OpenGlRenderer;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		
		GLSurfaceView glView = (GLSurfaceView) findViewById(R.id.glSurface);
		OpenGlRenderer renderer = new OpenGlRenderer();
		glView.setRenderer(renderer);
		glView.requestFocus();
		glView.setFocusableInTouchMode(true);
		
		GameStatus.init(this);
		
	}

}
