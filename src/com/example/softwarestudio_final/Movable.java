package com.example.softwarestudio_final;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Movable {
	float x,y;
	Bitmap bitmap;
	public Movable(Bitmap b,float x,float y){
		this.bitmap= b;
		this.x=x;
		this.y=y;
	}
	public void draw(Canvas canvas){
		Paint p = new Paint();
		
		canvas.drawBitmap(bitmap, x, y,p);
	}
	public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
	
}
