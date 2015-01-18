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
	
	public int getPosition(){
		return this.position;
	}
	
	public void drawself(Canvas canvas){
		Paint p = new Paint();
		p.setAntiAlias(true);
		canvas.drawBitmap(rope,0,position,p);
	
	}
	
	void setPosition(int value){
		this.position += value ;
		if(position<=-1790){
			gv.drawResault(1);
		}
		else if(position>=10){
			gv.drawResault(0);
		}
	}
	
}
