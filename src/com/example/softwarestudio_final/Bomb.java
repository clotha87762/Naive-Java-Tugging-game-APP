package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Bomb {
	
	int x,y,cur;
	GameView gv;
	Bitmap[] bitmaps = new Bitmap[3];
	
	public Bomb(int x, int y,GameView gv){
		this.x = x;
		this.y = y;
		this.gv=gv;
		cur = 0;
		if(Constant.StlyeCute){
		bitmaps[0] = BitmapFactory.decodeResource(gv.getResources(), R.drawable.cute1);
		bitmaps[1] = BitmapFactory.decodeResource(gv.getResources(), R.drawable.cute2);
		bitmaps[2] = BitmapFactory.decodeResource(gv.getResources(), R.drawable.cute3);
		}
		else{
			bitmaps[0] = BitmapFactory.decodeResource(gv.getResources(), R.drawable.rock3);
			bitmaps[1] = BitmapFactory.decodeResource(gv.getResources(), R.drawable.rock2);
			bitmaps[2] = BitmapFactory.decodeResource(gv.getResources(), R.drawable.rock1);
			
		}
	}
	
	public void next(){
		if(cur<3)
		cur ++; 	
	}
	public  void draw(Canvas canvas){
		Paint p = new Paint();
		if(cur<3)
		canvas.drawBitmap(bitmaps[cur], x, y, p);
	}
	public void drawR(Canvas canvas){
		Paint p = new Paint();
		canvas.save();
		canvas.rotate(180, x+175, y+175);
		if(cur<3)
		canvas.drawBitmap(bitmaps[cur], x, y, p);
		canvas.restore();
	}
	
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	
	
	
	
}
