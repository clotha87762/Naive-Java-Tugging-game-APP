package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Rope {
	
	Bitmap rope;
	int position;
	GameView gv;
	public Rope(GameView gv,Bitmap bm){
		this.gv=gv;
		this.rope = bm;
		position =-890;//(int)(-Constant.HEIGHT/4*Constant.RATIO);
	}
	
	public void drawself(Canvas canvas){
		Paint p = new Paint();
		p.setAntiAlias(true);
		canvas.drawBitmap(rope,0,position,p);
	
	}
	
	void setPosition(int value){
		this.position += value ;
	}
	
}
