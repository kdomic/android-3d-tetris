package com.trippleit.android.tetris3d;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Instructions extends Activity implements OnClickListener {

	private Intent i = new Intent("com.trippleit.android.tetris3d.MAIN");
	private boolean started = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.instructions);

		ImageView iv = (ImageView) findViewById(R.id.ivInstructions);
		iv.setOnClickListener(this);

		Thread timer = new Thread() {
			@Override
			public void run() {
				super.run();
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					startIntent();
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	@Override
	public void onClick(View v) {
		startIntent();
	}

	private void startIntent() {
		if (!started) {
			started = true;
			startActivity(i);
		}
	}

}
