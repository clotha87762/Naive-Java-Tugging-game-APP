package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BombItem {
	
	int x,y,cur;
	GameView gv;
	Bitmap bitmap;
	public BombItem(int x, int y,GameView gv,boolean a){
		this.x = x;
		this.y = y;
		this.gv=gv;
		cur = 0;
		if(a){
		bitmap = BitmapFactory.decodeResource(gv.getResources(),R.drawable.crystal);
		}
		else{
			bitmap = BitmapFactory.decodeResource(gv.getResources(),R.drawable.crystalb);
		}	
		
	}
	
	public  void draw(Canvas canvas){
		Paint p = new Paint();
		canvas.drawBitmap(bitmap, x, y, p);
	}
	public void drawB(Canvas canvas){
		
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
