package com.trippleit.android.tetris3d.controls;

import com.trippleit.android.tetris3d.GameStatus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SwipeControls extends SimpleOnGestureListener implements
		OnTouchListener {

	private Context c;
	private final GestureDetector gestureDetector;

	public SwipeControls(Context _c) {
		this.c = _c;
		gestureDetector = new GestureDetector(c, this);
	}
	
	public void onSwipeRight() {
		GameStatus.setCurrentXPositionPos();
    }

    public void onSwipeLeft() {
    	GameStatus.setCurrentXPositionNeg();
    }

    public void onSwipeTop() {
    	GameStatus.setCurrentYPositionPos();
    }

    public void onSwipeBottom() {
    	GameStatus.setCurrentYPositionNeg();    	
    }

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {		
		return gestureDetector.onTouchEvent(event);
	}

	private static final int SWIPE_THRESHOLD = 100;
	private static final int SWIPE_VELOCITY_THRESHOLD = 100;

	@Override
	public boolean onDown(MotionEvent e) {
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		boolean result = false;
		try {
			float diffY = e2.getY() - e1.getY();
			float diffX = e2.getX() - e1.getX();
			if (Math.abs(diffX) > Math.abs(diffY)) {
				if (Math.abs(diffX) > SWIPE_THRESHOLD
						&& Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
					if (diffX > 0) {
						onSwipeRight();
					} else {
						onSwipeLeft();
					}
				}
				result = true;
			} else if (Math.abs(diffY) > SWIPE_THRESHOLD
					&& Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
				if (diffY > 0) {
					onSwipeBottom();
				} else {
					onSwipeTop();
				}
			}
			result = true;

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}
}
