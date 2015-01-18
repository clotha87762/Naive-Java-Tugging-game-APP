package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Rope {

	Bitmap rope;
	private int position;
	private int currentPosition;
	
	RopeThread ropeThread;
	GameView gv;

	public Rope(GameView gv, Bitmap bm) {
		this.gv = gv;
		this.rope = bm;
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

	void setPosition(int value) {
		this.position += value;
	}

	void moveToPosition() {
		if (this.currentPosition < this.position){
			this.currentPosition++;
		}
		else if (this.currentPosition > this.position){
			this.currentPosition--;
		}
		if (currentPosition <= -1790) {
			gv.drawResault(1);
		} else if (currentPosition >= 10) {
			gv.drawResault(0);
		}
	}

}
