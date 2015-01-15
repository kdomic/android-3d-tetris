package com.trippleit.android.tetris3d.controls;

import com.trippleit.android.tetris3d.GameStatus;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SwipeControls implements OnTouchListener {

	private Context c;

	public SwipeControls(Context _c) {
		this.c = _c;
	}

	public void onSwipeRight() {
		Log.d("Kruno", "" + isMultiTouch);
		GameStatus.setCurrentXPositionPos();
	}

	public void onSwipeLeft() {
		Log.d("Kruno", "" + isMultiTouch);
		GameStatus.setCurrentXPositionNeg();
	}

	public void onSwipeTop() {
		Log.d("Kruno", "" + isMultiTouch);
		GameStatus.setCurrentYPositionPos();
	}

	public void onSwipeBottom() {
		Log.d("Kruno", "" + isMultiTouch);
		GameStatus.setCurrentYPositionNeg();
	}

	private boolean isMultiTouch = false;
	private Integer fingersCount = 0;
	private float x1 = 0, y1 = 0;
	private float x2 = 0, y2 = 0;

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {

		int action = event.getAction() & MotionEvent.ACTION_MASK;

		switch (action) {
			case MotionEvent.ACTION_DOWN: {
				//Log.d("Kruno", "Action Down1");
				x1 = event.getX();
				y1 = event.getY();
				fingersCount = event.getPointerCount();
				break;
			}
			case MotionEvent.ACTION_MOVE: {
				//Log.d("Kruno", "Action Move");
				break;
			}

			case MotionEvent.ACTION_POINTER_DOWN: {
				//Log.d("Kruno", "Pointer Down");
				isMultiTouch = true;
				fingersCount = event.getPointerCount();
				break;
			}
			case MotionEvent.ACTION_POINTER_UP: {
				//Log.d("Kruno", "Pointer up");
				fingersCount = event.getPointerCount();
				break;
			}
			case MotionEvent.ACTION_UP: {
				//Log.d("Kruno", "Action up1");
				x2 = event.getX();
				y2 = event.getY();
				/*Log.d("Kruno", "a: " + isMultiTouch);
				Log.d("Kruno", "a: " + fingersCount);
				Log.d("Kruno", "[" + x1 + "," + y1 + "],[" + x2 + "," + y2
						+ "]");*/
				move(x1, y1, x2, y2, fingersCount);
				isMultiTouch = false;
				fingersCount = 0;
				break;
			}
		}

		return true;
	}

	private void move(float xFirst, float yFirst, float xSecond, float ySecond, int fCount) {
		switch (fCount) {
			case 1:
				switch (detectDirection(xFirst, yFirst, xSecond, ySecond)) {
					case 1:
						onSwipeRight();
						break;
					case 2:
						onSwipeLeft();
						break;
					case 3:
						onSwipeBottom();
						break;
					case 4:
						onSwipeTop();
						break;
				}
				break;
			case 2:
				switch (detectDirection(xFirst, yFirst, xSecond, ySecond)) {
					case 1:
						Log.d("Kruno", "dd: onSwipeRight");
						break;
					case 2:
						Log.d("Kruno", "dd: onSwipeLeft");
						break;
					case 3:
						Log.d("Kruno", "dd: onSwipeBottom");
						break;
					case 4:
						Log.d("Kruno", "dd: onSwipeTop");
						break;
				}
				break;
		}
	}

	private static final int SWIPE_THRESHOLD = 100;
	private static final int SWIPE_VELOCITY_THRESHOLD = 100;

	public int detectDirection(float xFirst, float yFirst, float xSecond,
			float ySecond) {
		int rez = 0;
		try {

			float diffY = ySecond - yFirst;
			float diffX = xSecond - xFirst;
			if (Math.abs(diffX) > Math.abs(diffY)) {
				if (diffX > 0) {
					return 1;
				} else {
					return 2;
				}
			} else {
				if (diffY > 0) {
					return 3;
				} else {
					return 4;
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return rez;
	}

}