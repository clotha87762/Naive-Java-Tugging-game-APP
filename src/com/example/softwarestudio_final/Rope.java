package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Rope {

	Bitmap rope;
	private int position;
	final int distance = 1;
	private int currentPosition;
	private int imageStatus;
	
	RopeThread ropeThread;
	GameView gv;

	public Rope(GameView gv, Bitmap bm) {
		this.gv = gv;
		this.rope = bm;
		imageStatus = 0;
		currentPosition = position = -890;// (int)(-Constant.HEIGHT/4*Constant.RATIO);
		ropeThread = new RopeThread( this );
		ropeThread.start();
	}

	public int getPosition() {
		return this.position;
	}

	public int getCurrentPosition() {
		return this.currentPosition;
	}

	public void drawself(Canvas canvas) {
		Paint p = new Paint();
		p.setAntiAlias(true);
		canvas.drawBitmap(rope, 0, currentPosition, p);
	}
	
	public void setBitmap(Bitmap bitmap){
		this.rope = bitmap;
	}
	
	void setPosition(int value) {
		this.position += value;
	}

	void moveToPosition() {
		
		if (this.currentPosition < this.position){
			this.currentPosition += distance;
			if( imageStatus != 1 ){
				Bitmap bitmap =  BitmapFactory.decodeResource( gv.getResources(),
					R.drawable.ropewin);
				setBitmap(bitmap);
				imageStatus = 1;
			}
		
		}
		else if (this.currentPosition > this.position){
			this.currentPosition -= distance;
			if( imageStatus != 2 ){
				Bitmap bitmap =  BitmapFactory.decodeResource( gv.getResources(),
					R.drawable.ropelose);
				setBitmap(bitmap);
				imageStatus = 2;
			}
		
		}
	
		if (currentPosition <= -1790) {		
			gv.drawResault(1);
			if( ropeThread.isRunning() )
				ropeThread.stopThread();
		} else if (currentPosition >= 10) {		
			gv.drawResault(0);
			if( ropeThread.isRunning() )
				ropeThread.stopThread();
		}
	}

}
