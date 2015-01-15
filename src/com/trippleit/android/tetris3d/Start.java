package com.trippleit.android.tetris3d;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by root on 22.11.14..
 */
public class Start extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start);
        
        Button b = (Button) findViewById(R.id.btnStart);
        b.setOnClickListener(this);

    }

	@Override
	public void onClick(View v) {
		Intent i = new Intent("com.trippleit.android.tetris3d.INSTRUCTIONS");
        startActivity(i);		
	}

}
